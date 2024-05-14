package com.example.mishloha_assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mishloha_assignment.data.model.Item
import com.example.mishloha_assignment.data.repository.RepoPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubReposViewModel @Inject constructor(
    private val repoPagingSource: RepoPagingSource
) : ViewModel() {

    private val _repoResponse: MutableStateFlow<PagingData<Item>> =
        MutableStateFlow(PagingData.empty())
    var repoResponse = _repoResponse.asStateFlow()
        private set

    init {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                repoPagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _repoResponse.value = it
            }
        }
    }
}