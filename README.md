
<!-- markdown-toc start - Don't edit this section. Run M-x markdown-toc-refresh-toc -->
**Table of Contents**

- [My Blog (Vue)](#my-blog-vue)
    - [首先，创建项目](#首先创建项目)
    - [其次，创建服务端模块](#其次创建服务端模块)
    - [然后，创建客户端模块](#然后创建客户端模块)
    - [[可选] 上传到 Github，方便团队协作](#可选-上传到-github方便团队协作)

<!-- markdown-toc end -->

# My Blog (Vue)
## 首先，创建项目

使用 Idea，创建一个 Empty 项目

预期目录结构是:
```
- myblog
  + .git/
  + server
    - src/
    - web/
  + client
    - node_modules/
    - public/
    - src/
    - package.json
    - vue.config.json
```

## 其次，创建服务端模块

注意: 勾选 Web Application!!!!!!!!!!

## 然后，创建客户端模块

使用脚手架创建一个 vue 项目:
```shell script
vue create server

vue add element
vue add axios

编辑 package.json
编辑 vue.config.js

npm run serve
```

然后，添加这个为 idea 的一个模块:
```
在 idea 中选择 create module with exists source，然后选择该文件夹
```

## [可选] 上传到 Github，方便团队协作

首先，在 Github 上注册一个账号，并 New Repositoy (创建一个新仓库)，比如名字为 myblog。

其次，将本地的项目转换为一个 git 项目:
```shell script
cd myblog
git init .      # 转换
编辑 .gitignore  # 排除某些文件

# 删除 client 下面的 .git 文件夹。因为 vue 脚手架默认为新建的项目初始化了 git，我们不需要
```

保存第一次修改:
```shell script
git add .
git commit -m 'first'
```

推送到服务器:
```shell script
git remote add origin https://github.com/yaollllllsan/myblog
git push -u origin master
```

之后进行了若干修改后，就可以随时记录并上传了:
```shell script
# 记录
git add .
git commit -m "记录"

# 上传
git push
```

另外，可以自己思考下，如何下载并更新。
