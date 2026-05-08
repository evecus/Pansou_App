# 盘搜 Android App

基于 [evecus/pansou](https://github.com/evecus/pansou) API 的安卓客户端，支持聚合搜索百度、阿里、夸克等主流网盘资源。

## 功能
- 关键词搜索网盘资源
- 按网盘类型分 Tab 展示结果（百度/阿里/夸克/迅雷...）
- 一键复制链接和密码
- 浏览器直接打开
- 自定义服务器地址（适合自部署）

## 编译发布

### GitHub Actions 自动发布

在仓库 Settings → Secrets and variables → Actions 中添加以下 Secrets：

| Secret 名称 | 值 |
|-------------|-----|
| `KEYSTORE_BASE64` | 见下方说明 |
| `KEYSTORE_PASSWORD` | `pansou123456` |
| `KEY_ALIAS` | `pansou` |
| `KEY_PASSWORD` | `pansou123456` |

> ⚠️ **`KEYSTORE_BASE64` 的值**：在本项目的 `keystore_base64.txt` 文件中（构建时生成），将文件内容完整粘贴进去。

### 手动触发发布

1. 进入 GitHub 仓库 → Actions → **Build & Release APK**
2. 点击 **Run workflow**
3. 填写 `tag`（如 `v1.0.0`）和 Release Notes
4. 点击绿色 **Run workflow** 按钮
5. 构建完成后会自动在 Releases 页发布 APK

## 本地调试

创建 `keystore.properties`（已加入 .gitignore）：
```
storeFile=../release.jks
storePassword=pansou123456
keyAlias=pansou
keyPassword=pansou123456
```

## 架构说明
- Kotlin + Jetpack Compose
- Material Design 3
- Retrofit2 + OkHttp 网络层
- DataStore 持久化设置
- 仅编译 arm64-v8a 架构
