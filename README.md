# 盘搜 Android App

基于 [evecus/pansou](https://github.com/evecus/pansou) API 的安卓客户端，支持聚合搜索百度、阿里、夸克等主流网盘资源。

## 功能
- 关键词搜索网盘资源
- 按网盘类型分 Tab 展示结果（百度/阿里/夸克/迅雷...）
- 一键复制链接和密码
- 浏览器直接打开
- 自定义服务器地址（适合自部署）

## 架构说明
- Kotlin + Jetpack Compose
- Material Design 3
- Retrofit2 + OkHttp 网络层
- DataStore 持久化设置
- 仅编译 arm64-v8a 架构
