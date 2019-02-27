package company.bigger.idempiere.di

import company.bigger.idempiere.config.Database
import software.hsharp.core.util.HikariCPI
import space.traversal.kapsule.transitive

private val mainLogicModule = MainLogicModule()

open class Context {
    open val module =
        Module(
            environment = MainEnvironmentModule(),
            logic = mainLogicModule,
            data = MainDataModule(mainLogicModule)
        ).transitive()
}

private fun start(): Context {
    HikariCPI.default(Database.Url, Database.Username, Database.Password)
    return Context()
}

object GlobalContext {
    val globalContext = start()
}

internal val globalContext get() = GlobalContext.globalContext
