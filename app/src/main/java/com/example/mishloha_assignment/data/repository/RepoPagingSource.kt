package com.example.mishloha_assignment.data.repository

import android.net.http.HttpException
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mishloha_assignment.data.model.Item
import com.example.mishloha_assignment.data.model.Repository
import java.io.IOException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

abstract class RepoPagingSource : PagingSource<Int, Item>() {

    val map: HashMap<String, String> = hashMapOf()

    init {
        initMap()
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? = state.anchorPosition
    private fun initMap() {
        map["sort"] = "stars"
        map["order"] = "desc"
    }

    companion object {
        private val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        fun getFormattedDateOneDayAgo(): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            val currentDate = calendar.time
            return dateFormat.format(currentDate).toString()
        }

        fun getFormattedDateOneWeekAgo(): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.WEEK_OF_YEAR, -1)
            val currentDate = calendar.time
            return dateFormat.format(currentDate).toString()
        }

        fun getFormattedDateOneMonthAgo(): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MONTH, -1)
            val currentDate = calendar.time
            return dateFormat.format(currentDate).toString()
        }
    }
}

class RepoPagingDaySource @Inject constructor(
    private val repository: GithubRepository
) : RepoPagingSource() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 1
        val date = getFormattedDateOneDayAgo()
        map["q"] = "created:>${date}"
        val response = repository.getRepos(page, params.loadSize, map).getOrDefault(Repository())
        val items = response.items
        return try {
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (items.isEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}

class RepoPagingWeekSource @Inject constructor(
    private val repository: GithubRepository
) : RepoPagingSource() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 1
        val date = getFormattedDateOneWeekAgo()
        map["q"] = "created:>${date}"
        val response = repository.getRepos(page, params.loadSize, map).getOrDefault(Repository())
        val items = response.items
        return try {
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (items.isEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}

class RepoPagingMonthSource @Inject constructor(
    private val repository: GithubRepository
) : RepoPagingSource() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        val page = params.key ?: 1
        val date = getFormattedDateOneMonthAgo()
        map["q"] = "created:>${date}"
        val response = repository.getRepos(page, params.loadSize, map).getOrDefault(Repository())
        val items = response.items
        return try {
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (items.isEmpty()) null else page.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}

