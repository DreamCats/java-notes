
## 前提
- 安装node[->链接](../vue/README.md)
- 安装git[->链接](../git/README.md)

## Hexo

- 安装hexo-cli`npm install -g hexo-cli`

- 初始化项目`hexo init Blog`

- 简单的三条命令

- ```shell
  hexo new test_my_site #创建一篇新文章
  hexo g #生成文件
  hexo s #本地 server 部署
  
  打开浏览器输入地址：
  localhost:4000
  hexo n "我的博客" == hexo new "我的博客" #新建文章
  hexo g == hexo generate #生成
  hexo s == hexo server #启动服务预览
  hexo d == hexo deploy #部署
  
  hexo clean #清除缓存，若是网页正常情况下可以忽略这条命令
  ```



## 部署到github

- 在Blog文件夹中打开_config.yml

- 部署配置

  ```yaml
  deploy:
  type: git
  repo: 这里填入你之前在GitHub上创建仓库的完整路径
  branch: master
  ```

- 注意上面:之后有空格

- Blog根目录下安装部署插件才能部署`npm install hexo-deployer-git --save`

- 执行三条命令

  ```shell
  hexo clean
  hexo g
  hexo d
  
  or 
  hexo clean
  hexo g -d
  ```

- 浏览器上输入`xxx.github.io`


## 部署到阿里云上

实际上就是创建个钩子

- `git init --bare blog.git`
- 有一个自动生成的 hooks 文件夹。我们需要在里边新建一个新的钩子文件 post-receive
- `git --work-tree=/www/wwwroot/dreamcat.ink/blog --git-dir=/root/blog.git checkout -f`

## hexo主题
- 推荐[pure](https://github.com/cofess/hexo-theme-pure)
