package br.com.checkwindowssession.dto

data class TableDTO(
        var titles: List<String>,
        var rows: List<RowDTO>
)

data class RowDTO(var columns: List<String> = listOf())