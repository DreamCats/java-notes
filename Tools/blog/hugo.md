> hugo搭建静态博客，这里演示mac

## 工具
- go
- hugo

## 安装

- mac自带go，因此win平台需要安装go
- mac采用brew的方式安装hugo: `brew install hugo`

## 使用

### 建立站点

```shell
hugo new site blogs
```

### 看一下目录

```shell
.
├── archetypes
│   └── default.md // 这里是模版文件
├── config.toml // 这里是配置文件
├── content // 这里是文件内容
├── data
├── layouts
├── static
└── themes

6 directories, 2 files
```

### 安装一个主题，比较简单

- 推荐PaperMod：[网站demo](https://adityatelange.github.io/hugo-PaperMod/)

```sh
// 这个主题比较简洁一点
git clone https://github.com/adityatelange/hugo-PaperMod themes/hugo-PaperMod --depth=1
```

- 替换配置文件`config.yom`为`config.yml`

```sh
baseURL: "http://dreamcat.ink/blog"
title: Dreamcat
paginate: 8
theme: hugo-PaperMod
enableInlineShortcodes: true
enableRobotsTXT: true

# googleAnalytics: UA-123-45

minify:
    disableXML: true
    # minifyOutput: true

languages:
    en:
        languageName: "English"
        weight: 1
        menu:
            main:
                - name: Archive
                  url: archives
                  weight: 5
                - name: Search
                  url: search/
                  weight: 10
                - name: Tags
                  url: tags/
                  weight: 10

    l2:
        languageName: "Lang2"
        weight: 2
        title: PaperModL2
        profileMode:
            enabled: true
            title: PaperMod
            # imageUrl: "#"
            # imageTitle: my image
            # imageWidth: 120
            # imageHeight: 120
            subtitle: "☄️ Fast | ☁️ Fluent | 🌙 Smooth | 📱 Responsive"
            # buttons:
            #     - name: Blog
            #       url: posts
            #     - name: Tags
            #       url: tags
        menu:
            main:
                - name: Archive
                  url: archives/
                  weight: 5
                - name: Tags
                  url: tags
                  weight: 10
                - name: Categories
                  url: categories
                  weight: 10
                - name: Series
                  url: series
                  weight: 10
                - name: NullLink
                  url: "#"
                - name: NullLink2
                  url: "#"
                - name: NullLink3
                  url: "#"

    l3:
        languageName: "Langrtl"
        languagedirection: rtl
        weight: 3
        title: PaperMod RTL
        homeInfoParams:
            Title: "Hi there \U0001F44B"
            Content: Welcome to RTL layout
        menu:
            main:
                - name: Tags
                  url: tags
                  weight: 10

outputs:
    home:
        - HTML
        - RSS
        - JSON

params:
    env: production
    description: "Theme PaperMod - https://github.com/adityatelange/hugo-PaperMod"
    author: 买老师
    # author: ["Me", "You"] # multiple authors
    ShowReadingTime: true
    defaultTheme: auto
    ShowShareButtons: true
    displayFullLangName: true
    # images: ""
    # assets:
    #   favicon: '<path / external url>'

    profileMode:
        enabled: false
        title: PaperMod
        imageUrl: "#"
        imageTitle: my image
        # imageWidth: 120
        # imageHeight: 120
        buttons:
            - name: Archives
              url: archives
            - name: Tags
              url: tags

    homeInfoParams:
        Title: "Hi there \U0001F44B"
        Content: >
            欢迎来到买老师的博客.
            - **追梦** 分享自己的成长经历.
    socialIcons:
        # - name: twitter
        #   url: "#"
        # - name: stackoverflow
        #   url: "#"
        # - name: codepen
        #   url: "#"
        # - name: linkedin
        #   url: "#"
        - name: github
          url: "https://github.com/DreamCats"

taxonomies:
    category: categories
    tag: tags
    series: series

privacy:
    vimeo:
        disabled: false
        simple: true

    twitter:
        disabled: false
        enableDNT: true
        simple: true

    instagram:
        disabled: false
        simple: true

    youtube:
        disabled: false
        privacyEnhanced: true

services:
    instagram:
        disableInlineCSS: true
    twitter:
        disableInlineCSS: true
```

### 创建文件

- new一个`first.md`文件

```shell
hugo new post/first.md
```

- 打开`first.md`文件编辑

```markdown
title: "first"
date: 2020-01-03T11:30:03+00:00
ShowToc: true
weight: 1
tags: ["first"]
```

### 演示

- 本地演示

```sh
hugo server -D
```

### 部署

```sh
hugo
```
会生成public， public的内容直接部署到服务器上即可，服务器省略

