> 一般新手都会使用一个用户，要么是github，要么是gitee

## 本地配置

上个文档已经讲过[本地配置](git配置文件.md)，在这里就不赘述了

## 连接github或者gitee

### 生成ssh公钥

```sh
ssh-keygen -t rsa -C "email@example.com"  
```

三次会车即可生成ssh key

### 找id_rsa.pub文件

- mac或者linux：在`~/.ssh`找到`id_rsa.pub`文件
- win：在`c:\Users\Administrator\.ssh`找到`id_rsa.pub`文件
- 打开之后，将其中的一串字符串拷贝

### github

- 去github上，打开settings中找SSH and GPG keys
- 如下图
![github添加密钥-uTjgO7](https://gitee.com/dreamcater/blog-img/raw/master/uPic/github添加密钥-uTjgO7.png)

- 然后在终端输入`ssh -T git@github.com`
- 终端则会输出`Hi DreamCats! You've successfully authenticated, but GITHUB.COM does not provide shell access.`

## 总结

- 多用户配置多加一些配置，后续会讲。


