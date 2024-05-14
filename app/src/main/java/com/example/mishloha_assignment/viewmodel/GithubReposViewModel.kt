package com.example.mishloha_assignment.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mishloha_assignment.data.model.Repository
import com.example.mishloha_assignment.data.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class GithubReposViewModel @Inject constructor(private val repository: GithubRepository) :
    ViewModel() {

    private val map: HashMap<String, String> = hashMapOf()

    data class UIState(
        val repos: Repository? = Repository()
    )

    var uiState by mutableStateOf(UIState())
        private set

    init {
        initMap()
    }

    fun loadRepos() {
        viewModelScope.launch {
            val date = getFormattedDateOneMonthAgo()
            map["q"] = "created:>${date}"
            uiState = uiState.copy(repos = repository.getRepos(map).getOrNull())
        }
    }

    private fun initMap() {
        //map["q"] = "created:>"
        map["sort"] = "stars"
        map["order"] = "desc"
    }

    private val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    private fun getFormattedDateOneMonthAgo(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val currentDate = calendar.time
        return dateFormat.format(currentDate).toString()
    }
}