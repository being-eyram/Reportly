import com.squareup.sqldelight.EnumColumnAdapter
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import io.eyram.reportly.db.ReportlyDatabase
import io.eyram.reportly.sqldelight.report.Report
import org.koin.dsl.module

val databaseModule = module {
    single {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        ReportlyDatabase.Schema.create(driver)
        val adapter = Report.Adapter(workDayAdapter = EnumColumnAdapter())

        ReportlyDatabase(driver, adapter)
    }
}


val repositoryModule = module {
    single {
        ReportlyRepository(get())
    }
}

val viewmodelModule = module {
    single {
        ReportlyViewmodel(get())
    }
}
