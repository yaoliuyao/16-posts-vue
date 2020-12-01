# myblog vue


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

## 创建服务端模块

勾选 Web Application

## 创建客户端模块

```sh
vue create server

vue add element
vue add axios

编辑 package.json
编辑 vue.config.js
```

然后，添加这个为 idea 的一个模块:
```
在 idea 中选择 create module with exists source，然后选择该文件夹
```