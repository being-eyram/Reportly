package models

import java.time.LocalTime

data class WeeksReport(
    val id: Int,
    val dailyReports: List<DailyReport>
)

data class DailyReport(
    val id: Int,
    val day: Day,
    val timeOn: LocalTime,
    val timeOff: LocalTime,
    val report: String
)


sealed class Day {
    object Monday : Day()
    object Tuesday : Day()
    object Wednesday : Day()
    object Thursday : Day()
    object Friday : Day()
}