import io.eyram.reportly.sqldelight.report.Report
import models.WorkDay

class ReportlyViewmodel(private val repository: ReportlyRepository) {

    private val workWeek = listOf(
        WorkDay.Monday,
        WorkDay.Tuesday,
        WorkDay.Wednesday,
        WorkDay.Thursday,
        WorkDay.Friday
    )

    fun getAllReports() = repository.getAllReports()

    fun commitWeeksReport() = workWeek.forEach {
        repository.commitReport(
            Report(
                workWeek = 1,
                workDay = it,
                report = "Some Report on ${it.name}",
                timeOn = "5:30 am",
                timeOff = null
            )
        )
    }
}