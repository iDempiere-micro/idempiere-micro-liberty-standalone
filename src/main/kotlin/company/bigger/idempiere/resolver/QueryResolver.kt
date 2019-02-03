package company.bigger.idempiere.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import company.bigger.idempiere.config.Jwt
import company.bigger.idempiere.dto.Version
import company.bigger.service.LoginService
import company.bigger.service.UserService

class QueryResolver : GraphQLQueryResolver {
    companion object {
        const val VER = "1.0.0"
        val loginService = LoginService()
        val userService = UserService(loginService, Jwt.Secret, Jwt.Issuer)
    }

    val version = Version(VER)
    //val me get() = userService.currentUser()
}
