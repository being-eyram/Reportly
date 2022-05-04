import io.eyram.reportly.db.ReportlyDatabase


class ReportlyRepository(val database : ReportlyDatabase) {

val reportQueries = database.workDayReportQueries


}