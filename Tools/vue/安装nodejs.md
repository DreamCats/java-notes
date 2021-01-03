# 安装nodesjs

- windows：
  1. [nodejs下载地址](<https://nodejs.org/en/download/>)
  2. 选择windwos instller 对应位数
  3. 选择目录进行安装、一路next即可
  4. 终端测试`node -v`
  5. 若显示版本号，则windows nodejs环境搭建成功

- linux 
  1. [nodejs下载地址](<https://nodejs.org/en/download/>)
  2. 选择linux x86/x64
  3. 选择合适的地方存放
  4. `tar -xvf   node-vx.xx.x-linux-x64.tar.xz `
  5. `mv node-vx.xx.x-linux-x64  nodejs `
  6. 建立软连接
  7. `ln -s /user_local/nodejs/bin/npm /usr/local/bin/ `
  8. `ln -s /user_local/nodejs/bin/node /usr/local/bin/`
  9. 终端测试`node -v`
- mac
  1. 以上官网下载
  2. 选择mac对应的installer
  3. 下载安装即可
  4. 同样测试终端`node -v`