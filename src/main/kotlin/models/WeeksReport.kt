package models

import io.eyram.reportly.sqldelight.report.Report

data class WeeksReport(
    val id: Int,
    val dailyReports: List<Report>
)