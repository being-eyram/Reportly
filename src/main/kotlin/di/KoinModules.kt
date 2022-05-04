import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import io.eyram.reportly.db.ReportlyDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        ReportlyDatabase.Schema.create(driver)
        ReportlyDatabase(driver)
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
