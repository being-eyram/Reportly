import androidx.compose.runtime.mutableStateMapOf
import io.eyram.reportly.sqldelight.report.Report
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import models.WeeksReport
import models.WorkDay
import org.jetbrains.skiko.MainUIDispatcher

class ReportlyViewmodel(private val repository: ReportlyRepository) {

    private var lastWeekNumber = 1

    private val mainScope = MainScope()
    val weeksReport: StateFlow<List<WeeksReport>>
        get() = _weeksReport

    private var _weeksReport = MutableStateFlow(listOf(WeeksReport(0, listOf())))

    val selectedReportStateHolder = mutableStateMapOf<Report, Boolean>()

    // mapofExpandedWeeklyReport.putAll(treeViewData.associate { it.id to false })

    init {
        mainScope.launch(Dispatchers.IO) {
            repository
                .getAllReports()
                .collect {
                    _weeksReport.value = it.mapIndexed { index, reports ->
                        withContext(MainUIDispatcher) {
                            selectedReportStateHolder.putAll(reports.associateWith { false })
                        }
                        WeeksReport(index, reports)
                    }
                }
        }

        mainScope.launch(Dispatchers.IO) {
            repository
                .getLastWeekNumber()
                .collect { num ->
                    withContext(MainUIDispatcher) {
                        lastWeekNumber = num
                    }
                }
        }
    }

    fun commitWeeksReport() {
        repository.commitReport(
            WorkDay.values().map {
                Report(
                    workWeek = lastWeekNumber + 1,
                    workDay = it,
                    report = "Some Report on ${it.name}",
                    timeOn = "5:30 am",
                    timeOff = null
                )
            }
        )
    }

    fun onSelect(report: Report) {
            selectedReportStateHolder[report] = !selectedReportStateHolder[report]!!
    }
}