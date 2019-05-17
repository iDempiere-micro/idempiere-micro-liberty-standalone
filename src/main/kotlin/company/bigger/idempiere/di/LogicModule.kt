package company.bigger.idempiere.di

import company.bigger.idempiere.service.AuthenticationService
import company.bigger.service.LoginService
import org.compiere.orm.ModelFactory

/**
 * Logic module - all non-DB services and [ModelFactory]
 */
interface LogicModule {
    val loginService: LoginService
    val authenticationService: AuthenticationService
    val modelFactory: ModelFactory
}