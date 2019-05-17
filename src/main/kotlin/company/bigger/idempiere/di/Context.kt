package company.bigger.idempiere.di

import company.bigger.idempiere.config.Database
import software.hsharp.core.util.HikariCPI
import space.traversal.kapsule.transitive

private val mainLogicModule = MainLogicModule()
private val mainEnvironmentModule = MainEnvironmentModule()

/**
 * Context containing the main module
 */
open class Context {
    open val module: ModuleImpl =
        ModuleImpl(
            environment = mainEnvironmentModule,
            logic = mainLogicModule,
            data = MainDataModule(mainLogicModule, mainEnvironmentModule)
        ).transitive()
}

private fun start(): Context {
    HikariCPI.default(Database.Url, Database.Username, Database.Password)
    return Context()
}

/**
 * The started  [Context]
 */
object GlobalContext {
    val globalContext = start()
}

internal val globalContext get() = GlobalContext.globalContext
