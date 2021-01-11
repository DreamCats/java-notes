# Yoink介绍

Yoink是Mac上一款文件临时暂存的工具软件，它就像一个**中间站**，或者可以叫做**中转站**一样，让你更能轻松的将一个文件从一个窗口移动到另外一个窗口。有时候，我们有很多这样移动文件的需求，那么Mac上当然也有几款软件类似于有这种功能，比如Dropshelf和Unclutter。但相比之下，

![Yoink-0mS6Co](https://gitee.com/dreamcater/blog-img/raw/master/uPic/Yoink-0mS6Co.png)


## 移动

你们有木有过这样的需求，有时候，我并不想打开两个窗口，比如
![两个finder窗口-1Aj98N](https://gitee.com/dreamcater/blog-img/raw/master/uPic/两个finder窗口-1Aj98N.png)

我可以暂时先放中间站，等我有有空了，有时间了，就移动过另外的文件夹。或者，我可以暂时放在这个中转站中，可以查看文档，查看图片等。

![yoink移动-GElggy](https://gitee.com/dreamcater/blog-img/raw/master/uPic/yoink移动-GElggy.gif)

## 复制
两种方式：
1. 可以在中转站按住`option`加左键移动
2. 也可以`option`加左键移动到中转站

![yoink复制-dteCap](https://cdn.jsdelivr.net/gh/DreamCats/imgs@main/uPic/yoink复制-dteCap.gif)

## 全选

在yoink中`command + A`可以全选中，移动到另外一个文件夹。

![yoink全选-YRCTmI](https://cdn.jsdelivr.net/gh/DreamCats/imgs@main/uPic/yoink全选-YRCTmI.gif)

## 快捷键

这里可能要配合系统中的服务设置快捷键

![yoink快捷键-Vcuops](https://cdn.jsdelivr.net/gh/DreamCats/imgs@main/uPic/yoink快捷键-Vcuops.png)

![yoink快捷键-2-cM5zNI](https://cdn.jsdelivr.net/gh/DreamCats/imgs@main/uPic/yoink快捷键-2-cM5zNI.png)

## 在Finder显示
![yoink菜单栏-TGva6L](https://cdn.jsdelivr.net/gh/DreamCats/imgs@main/uPic/yoink菜单栏-TGva6L.png)

## 终端

```sh
open -a Yoink /path/to/the/file
```

![yoink终端-zh9ioi](https://cdn.jsdelivr.net/gh/DreamCats/imgs@main/uPic/yoink终端-zh9ioi.gif)

如果觉得命令太长，可以通过设置alias别名来简化输入
```sh
alias yoink=“open -a Yoink”
```

这样就可以用别名了
```sh
yoink /path/to/the/file
```

## 总结

- mac系统太需要这个类似于中转站的工具了
- 推荐一个网站下载[链接](https://macwk.com/)