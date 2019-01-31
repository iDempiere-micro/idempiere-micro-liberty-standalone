package company.bigger.idempiere.db

import com.zaxxer.hikari.HikariDataSource
import company.bigger.idempiere.config.Database
import kotliquery.HikariCP

private fun start(): HikariDataSource {
    return HikariCP.default(Database.Url, Database.Username, Database.Password)
}

internal val intentionallyAssignedToCallTheInitFn = start()
