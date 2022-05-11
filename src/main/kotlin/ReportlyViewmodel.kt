import androidx.compose.runtime.collectAsState
import io.eyram.reportly.sqldelight.report.Report
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import models.WeeksReport
import models.WorkDay
import org.jetbrains.skiko.MainUIDispatcher
import org.koin.core.context.GlobalContext

class ReportlyViewmodel(private val repository: ReportlyRepository) {

    private var lastWeekNumber = 1

    private val mainScope = MainScope()
    val weeksReport: StateFlow<List<WeeksReport>>
        get() = _weeksReport

    private var _weeksReport = MutableStateFlow(listOf(WeeksReport(0, listOf())))

    init {
        mainScope.launch(Dispatchers.IO) {
            try {
                repository
                    .getAllReports()
                    .collect {
                        _weeksReport.value = it.mapIndexed{index, reports ->
                            WeeksReport(index, reports)
                        }
                    }
            } catch (e: Throwable) {
                println(e.message)
            }
        }

        mainScope.launch(Dispatchers.IO) {
            try {
                repository
                    .getLastWeekNumber()
                    .collect { num ->
                        withContext(MainUIDispatcher) {
                            lastWeekNumber = num
                        }
                    }
            } catch (e: Throwable) {
                println("Error Message from LWN : ${e.message}")
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
}