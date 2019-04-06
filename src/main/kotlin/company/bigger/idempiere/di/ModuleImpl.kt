package company.bigger.idempiere.di

import software.hsharp.modules.EnvironmentModule
import software.hsharp.modules.Module
import space.traversal.kapsule.HasModules

class ModuleImpl(
    environment: EnvironmentModule,
    logic: LogicModule,
    data: MainDataModule
) :
    EnvironmentModule by environment,
    LogicModule by logic,
    DataModuleWithUserService by data,
    Module,
    HasModules {

    override val modules = setOf(data, logic)
}
