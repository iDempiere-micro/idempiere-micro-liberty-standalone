package company.bigger.idempiere.di

import company.bigger.idempiere.service.AuthenticationService
import company.bigger.service.LoginService
import company.bigger.service.UserService
import software.hsharp.core.models.EnvironmentService
import space.traversal.kapsule.HasModules

class Module(
    environment: EnvironmentModule,
    logic: LogicModule,
    data: DataModule
) :
    EnvironmentModule by environment,
    LogicModule by logic,
    DataModule by data,
    HasModules {

    override val modules = setOf(data, logic)
}

interface DataModule {
    val userService: UserService
}

interface LogicModule {
    val loginService: LoginService
    val authenticationService: AuthenticationService
}

interface EnvironmentModule {
    val environmentService: EnvironmentService
}