package company.bigger.idempiere.di

import company.bigger.idempiere.service.AuthenticationService
import company.bigger.service.LoginService
import org.compiere.orm.IModelFactory

interface LogicModule {
    val loginService: LoginService
    val authenticationService: AuthenticationService
    val modelFactory: IModelFactory
}