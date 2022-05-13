import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import io.eyram.reportly.sqldelight.report.Report
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import models.WeeksReport
import models.WorkDay
import org.jetbrains.skiko.MainUIDispatcher

class ReportlyViewmodel(private val repository: ReportlyRepository) {

    private var lastWeekNumber = 1

    private val mainScope = MainScope()

    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> get() = _uiState


    init {

        mainScope.launch(Dispatchers.IO) {
            repository
                .getAllReports()
                .collect { allWeeklyReports ->

                    val weeksReport = allWeeklyReports.mapIndexed { index, reports ->
                        WeeksReport(index, reports)
                    }

                    _uiState.update { currentUiState ->
                        currentUiState.copy(weeksReport = weeksReport)
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
        _uiState.update { currentUiState ->
            currentUiState.copy(selectedReport = report)
        }
    }
}

data class MainUiState(
    val selectedReport: Report = Report(0, WorkDay.Unspecified, report = null, timeOn = null, timeOff = null),
    val weeksReport: List<WeeksReport> = listOf()
)

