package com.pansou.app.model

import com.google.gson.annotations.SerializedName

// ── 请求 ──────────────────────────────────────────────

data class SearchRequest(
    @SerializedName("kw") val keyword: String,
    @SerializedName("res") val resultType: String = "merge",
    @SerializedName("src") val sourceType: String = "all",
    @SerializedName("refresh") val forceRefresh: Boolean = false,
    @SerializedName("cloud_types") val cloudTypes: List<String>? = null
)

// ── 响应 ──────────────────────────────────────────────

data class ApiResponse<T>(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: T?
)

data class SearchResponse(
    @SerializedName("total") val total: Int = 0,
    @SerializedName("merged_by_type") val mergedByType: Map<String, List<MergedLink>>? = null
)

data class MergedLink(
    @SerializedName("url") val url: String,
    @SerializedName("password") val password: String = "",
    @SerializedName("note") val note: String = "",
    @SerializedName("datetime") val datetime: String = "",
    @SerializedName("source") val source: String = ""
)

data class HealthResponse(
    @SerializedName("status") val status: String,
    @SerializedName("plugin_count") val pluginCount: Int = 0,
    @SerializedName("auth_enabled") val authEnabled: Boolean = false
)

// ── 本地 UI 状态 ───────────────────────────────────────

sealed class SearchState {
    object Idle : SearchState()
    object Loading : SearchState()
    data class Success(val response: SearchResponse) : SearchState()
    data class Error(val message: String) : SearchState()
}

// 网盘类型中文名映射
val DISK_TYPE_NAMES = mapOf(
    "baidu"   to "百度网盘",
    "aliyun"  to "阿里云盘",
    "quark"   to "夸克网盘",
    "115"     to "115网盘",
    "123"     to "123网盘",
    "xunlei"  to "迅雷网盘",
    "mobile"  to "移动云盘",
    "tianyi"  to "天翼云盘",
    "uc"      to "UC网盘",
    "pikpak"  to "PikPak",
    "ed2k"    to "电驴链接",
    "magnet"  to "磁力链接",
    "other"   to "其他"
)

fun diskTypeName(type: String): String = DISK_TYPE_NAMES[type] ?: type
