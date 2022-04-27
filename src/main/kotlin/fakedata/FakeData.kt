package fakedata

import androidx.compose.runtime.mutableStateMapOf
import models.DailyReport
import models.Day
import models.WeeksReport
import java.time.LocalTime


val mondayReport = DailyReport(
    id = 1,
    day = Day.Monday,
    timeOn = LocalTime.parse("06:30"),
    timeOff = LocalTime.parse("09:40"),
    report = "Hello, Monday!"
)

val tuesdayReport = DailyReport(
    id = 2,
    day = Day.Tuesday,
    timeOn = LocalTime.parse("06:30"),
    timeOff = LocalTime.parse("09:40"),
    report = "Hello, Tuesday!"
)

val wednesdayReport = DailyReport(
    id = 3,
    day = Day.Wednesday,
    timeOn = LocalTime.parse("06:30"),
    timeOff = LocalTime.parse("09:40"),
    report = "Hello, Wednesday!"
)

val thursdayReport = DailyReport(
    id = 4,
    day = Day.Thursday,
    timeOn = LocalTime.parse("06:30"),
    timeOff = LocalTime.parse("09:40"),
    report = "Hello, Thursday!"
)

val fridayReport = DailyReport(
    id = 5,
    day = Day.Friday,
    timeOn = LocalTime.parse("06:30"),
    timeOff = LocalTime.parse("09:40"),
    report = "Hello, Friday!"
)
//////////////////////////////////////////////


val weeks1Report = WeeksReport(
    id = 0,
    dailyReports = listOf(
        mondayReport,
        tuesdayReport,
        wednesdayReport,
        thursdayReport,
        fridayReport
    )
)

val weeks2Report = WeeksReport(
    id = 1,
    dailyReports = listOf(
        mondayReport,
        tuesdayReport,
        wednesdayReport,
        thursdayReport,
        fridayReport
    )
)

val weeks3Report = WeeksReport(
    id = 2,
    dailyReports = listOf(
        mondayReport,
        tuesdayReport,
        wednesdayReport,
        thursdayReport,
        fridayReport
    )
)

val weeks4Report = WeeksReport(
    id = 3,
    dailyReports = listOf(
        mondayReport,
        tuesdayReport,
        wednesdayReport,
        thursdayReport,
        fridayReport
    )
)

val weeks5Report = WeeksReport(
    id = 4,
    dailyReports = listOf(
        mondayReport,
        tuesdayReport,
        wednesdayReport,
        thursdayReport,
        fridayReport
    )
)
val treeViewData = listOf(
    weeks1Report,
    weeks2Report,
    weeks3Report,
    weeks4Report,
    weeks5Report,
)


