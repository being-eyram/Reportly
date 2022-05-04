import io.eyram.reportly.sqldelight.report.Report

class ReportlyViewmodel(private val repository: ReportlyRepository) {

    fun getAllReports() = repository.getAllReports()
    fun commitWeeksReport() = workWeek.forEach {
        repository.commitReport(
            Report(
                work_week = 1,
                work_day = it.code.toLong(),
                report = "Some Report on ${it.name}",
                time_on = "5:30 am",
                time_off = null
            )
        )
    }

}

enum class WorkDay(val code: Int) {
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5)
}

val workWeek = listOf<WorkDay>(
    WorkDay.Monday,
    WorkDay.Tuesday,
    WorkDay.Wednesday,
    WorkDay.Thursday,
    WorkDay.Friday
)