package company.bigger.idempiere.it

import company.bigger.idempiere.service.SimpleModelFactory
import org.flywaydb.core.Flyway
import org.idempiere.common.util.EnvironmentServiceImpl
import org.junit.Test
import org.slf4j.impl.SimpleLogger
import software.hsharp.core.modules.BaseModuleImpl
import software.hsharp.core.util.HikariCPI
import kotlin.test.assertEquals

internal val sessionUrl =
    System.getenv("SESSION_URL") ?: "jdbc:postgresql://localhost:5433/idempiere?autosave=conservative"

val environmentService = EnvironmentServiceImpl(11, 0, 0)
internal val baseModule = BaseModuleImpl(environmentService = environmentService, modelFactory = SimpleModelFactory())

/**
 * Simple tests
 */
class SimpleTest {
    init {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN")
        HikariCPI.default(sessionUrl, "adempiere", "adempiere")

        // TODO: This should not be here. The migration should be exposed from the crm-core module
        // Create the Flyway instance and point it to the database
        val flyway =
            Flyway
                .configure()
                .dataSource(sessionUrl, "adempiere", "adempiere")
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .load()

        // Start the migration
        flyway.migrate()
    }

    /**
     * Simple test to make sure the test framework works AND to run the migrations. Do not remove!
     */
    @Test
    fun works() {
        assertEquals(1, 1)
    }
}