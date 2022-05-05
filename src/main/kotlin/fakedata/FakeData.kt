package fakedata

import io.eyram.reportly.sqldelight.report.Report
import models.WeeksReport
import models.WorkDay


val mondayReport = Report(
    workWeek = 1,
    workDay = WorkDay.Monday,
    timeOn = "5:30 AM",
    timeOff = null,
    report = "Hello, Monday!"
)

val tuesdayReport = Report(
    workWeek = 1,
    workDay = WorkDay.Tuesday,
    timeOn = "06:30",
    timeOff = "09:40",
    report = "Hello, Tuesday!"
)

val wednesdayReport = Report(
    workWeek = 1,
    workDay = WorkDay.Wednesday,
    timeOn = "06:30",
    timeOff = "09:40",
    report = "Hello, Wednesday!"
)

val thursdayReport = Report(
    workWeek = 1,
    workDay = WorkDay.Thursday,
    timeOn = "06:30",
    timeOff = "09:40",
    report = "Hello, Thursday!"
)

val fridayReport = Report(
    workWeek = 1,
    workDay = WorkDay.Friday,
    timeOn = "06:30",
    timeOff = "09:40",
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


