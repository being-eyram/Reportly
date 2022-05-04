import io.eyram.reportly.db.ReportlyDatabase
import io.eyram.reportly.sqldelight.report.Report


class ReportlyRepository(val database: ReportlyDatabase) {

    private val reportQueries = database.workDayReportQueries

    fun getAllReports() = reportQueries
        .selectAll()
        .executeAsList()

    fun commitReport(report : Report) = reportQueries.commitReport(report)

}