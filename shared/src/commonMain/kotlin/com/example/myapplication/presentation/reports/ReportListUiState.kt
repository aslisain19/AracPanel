package com.example.myapplication.presentation.reports

import com.example.myapplication.domain.model.Report

data class ReportListUiState(
    val isLoading: Boolean = false,
    val query: String = "",
    val allItems: List<Report> = emptyList(),
    val page: Int = 1,
    val pageSize: Int = 10,
    val error: String? = null
) {
    val filtered: List<Report>
        get() = if (query.isBlank()) allItems else allItems.filter { it.name.contains(query, ignoreCase = true) }

    val totalCount: Int get() = filtered.size
    val totalPages: Int get() = maxOf(1, (totalCount + pageSize - 1) / pageSize)
    val pagedItems: List<Report> get() = filtered.drop((page - 1) * pageSize).take(pageSize)
    val canGoPrevious: Boolean get() = page > 1
    val canGoNext: Boolean get() = page < totalPages
}