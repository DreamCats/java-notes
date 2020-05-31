# Redis绑定Token
> 先介绍一下概念

## cookie
HTTP 协议是**无状态的**，主要是为了让 HTTP 协议尽可能简单，使得它能够处理大量事务。HTTP/1.1 引入 Cookie 来保存状态信息。

Cookie 是服务器发送到用户浏览器并保存在**本地的一小块数据**，它会在浏览器之后向同一服务器**再次发起请求时被携带上**，用于告知服务端两个请求是否来自同一浏览器。由于之后每次请求都会需要携带 Cookie 数据，因此会带来额外的性能开销（尤其是在移动环境下）。

### 用途
- 会话状态管理（如用户登录状态、购物车、游戏分数或其它需要记录的信息）
- 个性化设置（如用户自定义设置、主题等）
- 浏览器行为跟踪（如跟踪分析用户行为等）

## session
除了可以将用户信息通过 Cookie 存储在用户浏览器中，也可以利用 Session 存储在服务器端，存储在服务器端的信息更加安全。

Session 可以存储在服务器上的文件、数据库或者内存中。也可以将 Session 存储在 Redis 这种内存型数据库中，效率会更高。

使用 Session 维护用户登录状态的过程如下：
1. 用户进行登录时，用户提交包含用户名和密码的表单，放入 HTTP 请求报文中；
2. 服务器验证该用户名和密码，如果正确则把用户信息存储到 Redis 中，它在 Redis 中的 Key 称为 Session ID；
3. 服务器返回的响应报文的 Set-Cookie 首部字段包含了这个 Session ID，客户端收到响应报文之后将该 Cookie 值存入浏览器中；
4. 客户端之后对同一个服务器进行请求时会包含该 Cookie 值，服务器收到之后提取出 Session ID，从 Redis 中取出用户信息，继续之前的业务操作。

> 注意：Session ID 的安全性问题，不能让它被恶意攻击者轻易获取，那么就不能产生一个容易被猜到的 Session ID 值。此外，还需要经常重新生成 Session ID。在对安全性要求极高的场景下，例如转账等操作，除了使用 Session 管理用户状态之外，还需要对用户进行重新验证，比如重新输入密码，或者使用短信验证码等方式。

### session和cookie选择
- Cookie 只能存储 ASCII 码字符串，而 Session 则可以存储任何类型的数据，因此在考虑数据复杂性时首选 Session；
- Cookie 存储在浏览器中，容易被恶意查看。如果非要将一些隐私数据存在 Cookie 中，可以将 Cookie 值进行加密，然后在服务器进行解密；
- 对于大型网站，如果用户所有的信息都存储在 Session 中，那么开销是非常大的，因此不建议将所有的用户信息都存储到 Session 中。

## Token
实际上，Token是在服务端将用户信息经过Base64Url编码过后传给在客户端。每次用户请求的时候都会带上这一段信息，因此服务端拿到此信息进行解密后就知道此用户是谁了，这个方法叫做JWT(Json Web Token)。

## JWT
> JWT(json web token)是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准。

cookie+session这种模式通常是保存在内存中，而且服务从**单服务到多服务会面临的session共享问题**，随着用户量的增多，开销就会越大。而JWT不是这样的，只需要服务端生成token，客户端保存这个token，每次请求携带这个token，服务端认证解析就可。

### 构成
第一部分我们称它为头部（header),第二部分我们称其为载荷（payload)，第三部分是签证（signature)。详情请见[官网](https://jwt.io/introduction/)

简单说一下

#### header
是一个Json对象，描述JWT的元数据，通常是下面这样子的：
```json
{
    "alg": "HS256",
    "typ": "JWT"
}
```
上面代码中，alg属性表示签名的算法（algorithm），默认是 HMAC SHA256（写成 HS256）；typ属性表示这个令牌（token）的类型（type），JWT 令牌统一写为JWT。最后，将上面的 JSON 对象使用 Base64URL 算法转成字符串。


#### payload
Payload部分也是一个Json对象，用来存放实际需要传输的数据，JWT官方规定了下面几个官方的字段供选用。

- iss (issuer)：签发人
- exp (expiration time)：过期时间
- sub (subject)：主题
- aud (audience)：受众
- nbf (Not Before)：生效时间
- iat (Issued At)：签发时间
- jti (JWT ID)：编号

也可以定义私有属性

#### signature
Signature部分是对前面的两部分的数据进行签名，防止数据篡改。

首先需要定义一个秘钥，这个秘钥只有服务器才知道，不能泄露给用户，然后使用Header中指定的签名算法(默认情况是HMAC SHA256)，算出签名以后将Header、Payload、Signature三部分拼成一个字符串，每个部分用.分割开来，就可以返给用户了。

## 在项目中如何写

### JwtTokenUtil
这里是使用guns搭建的项目，自带的工具类，我就不详细赘述了，主要是一下我们的业务逻辑，以及如何使用。


### AuthController
> sb-gateway-auth-AuthController

能请求到AuthContoroller，说明两种情况：
1. 你没有携带token
2. 你token过期了

而这个业务处理，是为了生成token

```java
    @ApiOperation(value = "获取token接口", notes = "每调用一次，就会随机生成一串token", response = ResponseData.class)
    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseData createAuthenticationToken(@Validated AuthRequest authRequest, BindingResult bindingResult) {
    //Validated 的作用是抵消了使用很多if判断
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error("参数：{}校验失败，原因：{}", fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseUtil<>().setErrorMsg("用户参数设置错误:" + CommonBindingResult.getErrors(bindingResult));
        }
        // 获取用户的账号和密码
        UserLoginRequst req = new UserLoginRequst();
        req.setUsername(authRequest.getUserName());
        req.setPassword(authRequest.getPassword());
        // 调用userAPI.login业务获取用户id
        UserLoginResponse res = userAPI.login(req);
        String userId = "" + res.getUserId();
        if (res.getUserId() != 0) {
            // 如果id不等于0，说明账户存
            // 从redis看一下userId在不在
            if (redisUtils.hasKey(userId)) {
                // 如果存在，两种情况：1.你在其他登陆，2.别人登陆
                // 删除redis中的键为userId的
                redisUtils.del(userId);
            }
            // 以下两步，针对于userId生成token
            res.setRandomKey(jwtTokenUtil.getRandomKey());
            String token = jwtTokenUtil.generateToken(userId, res.getRandomKey());
            res.setToken(token);
            // 写进redis
            redisUtils.set(userId, token, RedisConstants.TOKEN_EXPIRE.getTime());
            // 返回给用户
            return new ResponseUtil<>().setData(res);
        } else {
            // 如果id等于0，那么说明两种情况：1.账号密码错误，2.账户不存在
            return new ResponseUtil<>().setErrorMsg("账号密码错误");
        }
    }
```

### AuthFilter
> sb-gateway->auth->filter->AuthFileter

#### 忽略列表
```java
// 配置忽略列表
String ignoreUrl = jwtProperties.getIgnoreUrl(); // 获取忽略的url
String[] ignoreUrls = ignoreUrl.split(",");
for(int i=0;i<ignoreUrls.length;i++){
    if(request.getServletPath().startsWith(ignoreUrls[i])){
        chain.doFilter(request, response);
        return;// 不需要认证token
    }
}
```

#### 认证token
```java
        // 获取携带的header中的authentication字段
        final String requestHeader = request.getHeader(jwtProperties.getHeader()); 
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {        
            // 解析token
            authToken = requestHeader.substring(7)

            // 先获取id
            try {
                // 获取userId，上一块不是针对于userId生成的token嘛？
                String userId = jwtTokenUtil.getUsernameFromToken(authToken);
                // 判断userId是否在redis存在
                if (!redisUtils.hasKey(userId)) {
                    // 如果userId不存在，说明token在Redis过期了，需要重新获取
                    CommonResponse response1 = new CommonResponse();
                    response1.setCode(SbCode.TOKEN_VALID_FAILED.getCode());
                    response1.setMsg(SbCode.TOKEN_VALID_FAILED.getMessage());
                    RenderUtil.renderJson(response, new ResponseUtil<>().setData(response1));
                    return;
                } else {
                    // 如果存在，去取token
                    String token = (String) redisUtils.get(userId);
                    // 判断redis的token和当前的浏览器的token是否一致
                    if(!token.equals(authToken)) {
                        // 如果不相等，说明别人已经登录了，你当前的token无效，需要重新登录，将它替换掉。
                        // 比如，两个手机不能同时登录同一个账户的QQ
                        CommonResponse response1 = new CommonResponse();
                        response1.setCode(SbCode.TOKEN_VALID_FAILED.getCode());
                        response1.setMsg(SbCode.TOKEN_VALID_FAILED.getMessage());
                        RenderUtil.renderJson(response, new ResponseUtil<>().setData(response1));
                        return;
                    }
                }
            } catch (Exception e) {
                // 如果发生异常，所携带的token有问题，防止爬虫造token
                CommonResponse response1 = new CommonResponse();
                response1.setCode(SbCode.TOKEN_VALID_FAILED.getCode());
                response1.setMsg(SbCode.TOKEN_VALID_FAILED.getMessage());
                RenderUtil.renderJson(response, new ResponseUtil<>().setData(response1));
                return;
            }
        } else {
            //header没有带Bearer字段
            CommonResponse response1 = new CommonResponse();
            response1.setCode(SbCode.TOKEN_VALID_FAILED.getCode());
            response1.setMsg(SbCode.TOKEN_VALID_FAILED.getMessage());
            RenderUtil.renderJson(response, new ResponseUtil<>().setData(response1));
            return;
        }
        chain.doFilter(request, response);
    }
```

**以上流程图，日后补充**