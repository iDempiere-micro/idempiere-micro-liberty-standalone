package company.bigger.idempiere.it

import org.compiere.orm.DefaultModelFactory
import org.junit.Test
import org.slf4j.impl.SimpleLogger
import software.hsharp.core.util.DB
import software.hsharp.core.util.HikariCPI
import software.hsharp.core.util.queryOf

internal val sessionUrl =
    System.getenv("SESSION_URL") ?: "jdbc:postgresql://localhost:5433/idempiere?autosave=conservative"

class SimpleTest {
    init {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN")
        HikariCPI.default(sessionUrl, "adempiere", "adempiere")
    }

    @Test
    fun `instantiate every single class from ad_table dynamically`() {
        DB.run {
            val sql = "select tablename from ad_table where isactive = 'Y' and tablename <> 'AD_WF_Activity'"
            val factory = DefaultModelFactory()
            val query = queryOf(sql, listOf()).map { factory.getPO(it.string(1), 0) }.asList
            DB.current.run(query)
        }
    }
}