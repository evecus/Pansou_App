package com.pansou.app.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.pansou.app.data.ApiClient
import com.pansou.app.data.SettingsRepository
import com.pansou.app.model.SearchRequest
import com.pansou.app.model.SearchState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsRepo = SettingsRepository(application)

    val baseUrlFlow = settingsRepo.baseUrlFlow

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Idle)
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    private val _keyword = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword.asStateFlow()

    fun onKeywordChange(value: String) {
        _keyword.value = value
    }

    fun search(forceRefresh: Boolean = false) {
        val kw = _keyword.value.trim()
        if (kw.isEmpty()) return
        viewModelScope.launch {
            _searchState.value = SearchState.Loading
            try {
                val baseUrl = settingsRepo.baseUrlFlow.first()
                if (baseUrl.isEmpty()) {
                    _searchState.value = SearchState.Error("请先在设置中填写服务器地址")
                    return@launch
                }
                val api = ApiClient.getApi(baseUrl)
                val response = api.search(
                    SearchRequest(
                        keyword = kw,
                        forceRefresh = forceRefresh
                    )
                )
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null && body.code == 0 && body.data != null) {
                        _searchState.value = SearchState.Success(body.data)
                    } else {
                        _searchState.value = SearchState.Error(body?.message ?: "搜索失败")
                    }
                } else {
                    _searchState.value = SearchState.Error("请求失败: ${response.code()}")
                }
            } catch (e: Exception) {
                _searchState.value = SearchState.Error("网络错误: ${e.message}")
            }
        }
    }

    fun saveBaseUrl(url: String) {
        viewModelScope.launch {
            settingsRepo.saveBaseUrl(url)
            // Reset retrofit instance on URL change
            _searchState.value = SearchState.Idle
        }
    }

    fun clearSearch() {
        _keyword.value = ""
        _searchState.value = SearchState.Idle
    }
}
