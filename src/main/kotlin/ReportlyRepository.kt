import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import io.eyram.reportly.db.ReportlyDatabase
import io.eyram.reportly.sqldelight.report.Report
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


class ReportlyRepository(val database: ReportlyDatabase) {

    private val reportQueries = database.workDayReportQueries

    fun getAllReports() = reportQueries
        .selectAll()
        .asFlow()
        .mapToList()
        .map { it.chunked(5) }
        .catch { cause ->
            println(
                """message : ${cause.message} 
                |cause : ${cause.cause}
            """.trimMargin()
            )
        }

    fun commitReport(reports: List<Report>) = reportQueries.transaction {
        reports.forEach {
            reportQueries.commitReport(it)
        }
    }

    fun getLastWeekNumber() = reportQueries
        .lastWeekNumber()
        .asFlow()
        .map { it.executeAsOne() }
        .catch { cause ->
            println(
                """message : ${cause.message} 
                  |cause : ${cause.cause}
            """.trimMargin()
            )
        }
}