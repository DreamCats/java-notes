> 本文主要介绍 Markdown 的扩展语法

## 总览

在[基础语法](https://ld246.com/article/1583129520165)中我们介绍了 Markdown 最常用的排版用法，但有些时候基础语法不足以满足复杂一些的排版需求，这时候就需要使用扩展语法了。

一些个人和组织对基础语法进行了扩展，比如引入表格、围栏代码块、代码高亮、自动链接、脚注和目录等，这些扩展语法需要特定的 Markdown 引擎来支持。

## 可用性

不是所有的 Markdown 引擎都支持扩展语法，所以在使用扩展语法之前，你可能需要对具体的 Markdown 引擎支持情况进行确认，通过浏览它们的使用文档来了解具体哪些元素可以得到支持。

### 主流的 Markdown 语法规范

Markdown 语法规范目前尚未形成标准，如下列出的几种语法规范较为主流。

* [CommonMark](https://commonmark.org)
* [GitHub Flavored Markdown (GFM)](https://github.github.com/gfm)
* [Markdown Extra](https://michelf.ca/projects/php-markdown/extra)
* [MultiMarkdown](https://fletcherpenney.net/multimarkdown)
* [R Markdown](https://rmarkdown.rstudio.com)

其中 CommonMark 和 GFM 是目前最有可能进行标准化的。GFM 是 GitHub 基于 CommonMark 进行的扩展，它几乎已经是事实标准了，关于 CommonMark 规范要点解读可参考[这里](https://ld246.com/article/1566893557720)。

### Markdown 引擎

[这里](https://github.com/markdown/markdown.github.com/wiki/Implementations)列出了很多 Markdown 引擎实现，它们中的大多数都支持通过设置开关来都支持扩展语法，具体细节请参考它们的文档。

## 表格

使用短横线 `---` 来分隔表头和表身，使用竖线 `|` 来分隔列，每行开头和结尾的竖线是可选的。

```markdown
| Syntax      | Description |
| ----------- | ----------- |
| Header      | Title       |
| Paragraph   | Text        |
```

渲染结果：

| Syntax    | Description |
| --------- | ----------- |
| Header    | Title       |
| Paragraph | Text        |

每个列不一定要对齐，如下示例同样能够渲染：

```markdown
| Syntax | Description |
| --- | ----------- |
| Header | Title |
| Paragraph | Text |
```

### 表格对齐

如果需要左对齐、居中对齐或者右对齐表格内容，可以通过在 `---` 中添加冒号 `:` 实现。冒号仅出现在左边表示左对齐，出现在两边表示居中对齐，仅出现在右边表示右对齐。

```markdown
| Syntax      | Description | Test Text     |
| :---        |    :----:   |          ---: |
| Header      | Title       | Here's this   |
| Paragraph   | Text        | And more      |
```

渲染结果：

| Syntax    | Description |   Test Text |
| :-------- | :---------: | ----------: |
| Header    |    Title    | Here's this |
| Paragraph |    Text    |    And more |

### 表格内容排版

表格中的内容也可以进行排版，比如加粗、强调文本，插入超链接等。但仅限于使用“行级元素”进行排版，不能使用“块级元素”，比如不能使用标题、块引用、列表、分隔线等。

### 表格内容转义竖线

如果需要在表格内容中使用竖线 `|`，那就需要对其进行转义。可以使用 `\|` 转义，但更稳妥的做法是写竖线的 HTML 实体表示 `&#124;`，因为有的 Markdown 引擎不能正确处理表格内容中的 `\|`。

## 围栏代码块

在[基础语法](https://ld246.com/article/1583129520165)中我们介绍过可以通过四个空格或者制表符 `\t` 缩进来排版代码块。但我们更推荐的是使用围栏代码块语法，即使用大于等于三个反引号 span</code> 或者大于等于三个波浪线 `~~~` 来包裹代码块内容：

````markdown
```
{
  "firstName": "John",
  "lastName": "Smith",
  "age": 25
}
```
````

渲染结果：

```text
{
  "firstName": "John",
  "lastName": "Smith",
  "age": 25
}
```

如果需要展示代码块原文 Markdown（就像上面展示的例子那样），可以在最外层使用更多数量的反引号开始，闭合的反引号数量等于开始的数量即可。

``````markdown
````
```
{
  "firstName": "John",
  "lastName": "Smith",
  "age": 25
}
```
````
``````

### 代码块语法高亮

代码块语法高亮需要 Markdown 引擎支持，通过在开始反引号后添加编程语言名称来排版。

````markdown
```json
{
  "firstName": "John",
  "lastName": "Smith",
  "age": 25
}
```
````

渲染结果：

```json
{
  "firstName": "John",
  "lastName": "Smith",
  "age": 25
}
```

## 脚注

脚注用于在文末添加细节说明或者参考，这样文章的正文部分看上去会更加简洁清晰。创建脚注后，正文中引用脚注的地方会出现一个上标数字链接，读者点击后跳转到文末脚注定义的对应位置。

脚注引用通过 `[^标识符]` 创建，标识符部分可以是数字或者文本，但不能包含空格或者制表符。标识符仅用于关联引用和定义，在渲染时会自动根据脚注定义顺序进行数字递增渲染。不过这也不是绝对的，某些 Markdown 引擎也会将标识符部分用于渲染。

脚注定义使用 `[^标识符]:` 来创建，冒号后面就是需要添加的细节说明或者参考。脚注定义不一定非要放在整个 Markdown 文本的末尾，夹在段落、列表或者块引用之间也是可以工作的。

```markdown
这里是一个脚注引用[^1]，这里是另一个脚注引用[^bignote]。

[^1]: 第一个脚注定义。
[^bignote]: 脚注定义可使用多段内容。

    缩进对齐的段落包含在这个脚注定义内。

    ```
    可以使用代码块。
    ```

    还有其他行级排版语法，比如**加粗**和[链接](https://b3log.org)。
```

## 标题指定 ID {#heading-ids}

一些 Markdown 引擎支持为标题指定 ID，另一些 Markdown 引擎是自动添加 ID 的。标题 ID 的作用是允许其他地方通过锚点直接跳转到该标题。标题指定 ID 的语法是在标题后面通过花括号包裹 ID。

```markdown
### 这是一个标题 {#custom-id}
```

渲染的 HTML：

```html
<h3 id="custom-id">这是一个标题</h3>
```

### 链接到指定的标题

可以通过超链接语法链接到文中的标题。

| Markdown                          | HTML                                         | 渲染结果                                                           |
| --------------------------------- | -------------------------------------------- | ---------------------------------------------------------------------- |
| `[标题指定 ID](#heading-ids)` | `<a href="#heading-ids">标题指定 ID</a>` | [标题指定 ID](https://ld246.com/article/1583305480675#heading-ids) |

如果需要链接到其他页面的标题，需要写全链接路径，比如 `[标题指定 ID](https://ld246.com/article/1583305480675#heading-ids)`。

## 删除线

可以通过删除线划掉文本，排版结果~就像这样~。创建删除线可以通过一个 `~` 或两个波浪线 `~~` 包裹待删除的文本。

```
~~地球是平的。~~ 现在我们知道地球是圆的了。
```

渲染结果：

~~地球是平的。~~ 现在我们知道地球是圆的了。

## 任务列表

通过在普通列表项中添加 `[ ]` 或者 `[x]` 来渲染任务列表项。

```markdown
- [x] 待办事项一
- [ ] 待办事项二
- [ ] 待办事项三
```

渲染结果：

- [X] 待办事项一
- [ ] 待办事项二
- [ ] 待办事项三

## Emoji 表情

有两种方法使用 Emoji 表情：直接输入 Emoji 字符或者使用别名 `:smile:`。直接输入的话需要确认当前编辑器使用 UTF-8 编码。

```markdown
今天天气真好 :sunny: 
花儿 🌻 对我笑
```

渲染结果：

今天天气真好 ☀️
花儿 🌻 对我笑

## 禁止自动链接

大部分 Markdown 引擎都是默认开启自动链接的，所以当我们想把一个链接渲染为纯文本时，需要把它变成代码：

```markdown
`https://b3log.org`
```

渲染结果：

`https://b3log.org`

## 总结

尽量仅使用 GFM 定义的扩展语法，这样能够最大限度得到众多 Markdown 引擎的支持，从而得到一致的渲染结果。

## 参考

* [Markdown Guide](https://www.markdownguide.org)
* [CommonMark Spec](https://spec.commonmark.org)