package com.example.mishloha_assignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mishloha_assignment.data.model.Item
import com.example.mishloha_assignment.data.repository.RepoPagingDaySource
import com.example.mishloha_assignment.data.repository.RepoPagingMonthSource
import com.example.mishloha_assignment.data.repository.RepoPagingWeekSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubReposViewModel @Inject constructor(
    private val repoPagingDaySource: RepoPagingDaySource,
    private val repoPagingWeekSource: RepoPagingWeekSource,
    private val repoPagingMonthSource: RepoPagingMonthSource
) : ViewModel() {

    private val _repoResponse: MutableStateFlow<PagingData<Item>> =
        MutableStateFlow(PagingData.empty())
    var repoResponse = _repoResponse.asStateFlow()
        private set

    fun loadReposDay() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                repoPagingDaySource
            }.flow.cachedIn(viewModelScope).collect {
                _repoResponse.value = it
            }
        }
    }

    fun loadReposWeek() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                repoPagingWeekSource
            }.flow.cachedIn(viewModelScope).collect {
                _repoResponse.value = it
            }
        }
    }

    fun loadReposMonth() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = true
                )
            ) {
                repoPagingMonthSource
            }.flow.cachedIn(viewModelScope).collect {
                _repoResponse.value = it
            }
        }
    }
}