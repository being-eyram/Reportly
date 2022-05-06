import io.eyram.reportly.sqldelight.report.Report
import models.WorkDay

class ReportlyViewmodel(private val repository: ReportlyRepository) {

    fun getAllReports() = repository.getAllReports()

    private var counter = 1

    fun commitWeeksReport() {
        WorkDay.values().forEach {
            repository.commitReport(
                Report(
                    workWeek = counter,
                    workDay = it,
                    report = "Some Report on ${it.name}",
                    timeOn = "5:30 am",
                    timeOff = null
                )
            )
        }
        counter++
    }
}