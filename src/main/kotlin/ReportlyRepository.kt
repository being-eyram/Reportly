import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import io.eyram.reportly.db.ReportlyDatabase
import io.eyram.reportly.sqldelight.report.Report
import kotlinx.coroutines.flow.map


class ReportlyRepository(val database: ReportlyDatabase) {

    private val reportQueries = database.workDayReportQueries

    fun getAllReports() = reportQueries
        .selectAll()
        .asFlow()
        .mapToList()
        .map { it.chunked(5) }




    suspend fun commitReport(report: Report) = reportQueries.commitReport(report)

    fun getLastWeekNumber() = reportQueries
        .lastWeekNumber()
        .asFlow()
        .map { it.executeAsOne() }
}