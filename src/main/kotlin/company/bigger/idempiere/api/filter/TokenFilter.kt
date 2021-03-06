package company.bigger.idempiere.api.filter

import company.bigger.idempiere.di.ModuleImpl
import company.bigger.idempiere.di.globalContext
import company.bigger.service.UserService
import org.idempiere.common.util.AdempiereSystemError
import software.hsharp.core.services.EnvironmentService
import space.traversal.kapsule.Injects
import space.traversal.kapsule.inject
import space.traversal.kapsule.required
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.FilterConfig
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

private const val AUTH_HEADER_KEY = "Authorization"
private const val AUTH_HEADER_VALUE_PREFIX = "Bearer " // with trailing space to separate token
private const val AUTH_HEADER_VALUE_PREFIX_ALT = "Token " // with trailing space to separate token
private const val STATUS_CODE_UNAUTHORIZED = 401

@WebFilter("/*")
class TokenFilter : Filter, Injects<ModuleImpl> {

    private val userService: UserService by required { userService }
    private val environmentService: EnvironmentService by required { environmentService }

    private fun getToken(request: HttpServletRequest): String? {

        val authHeader = request.getHeader(AUTH_HEADER_KEY)
        return if (authHeader != null && (authHeader.startsWith(AUTH_HEADER_VALUE_PREFIX) || authHeader.startsWith(AUTH_HEADER_VALUE_PREFIX_ALT))) {
            authHeader.replace(AUTH_HEADER_VALUE_PREFIX, "").replace(AUTH_HEADER_VALUE_PREFIX_ALT, "")
        } else request.parameterMap["access_token"]?.firstOrNull()
    }

    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        inject(globalContext.module)
        request as HttpServletRequest
        val authToken = getToken(request)
        if (authToken != null) {
            val user = userService.findByToken(authToken)
            if (user != null) {
                environmentService.login(
                    user.clientId ?: throw AdempiereSystemError("User does not have client"), 0,
                    user.userId ?: throw AdempiereSystemError("User does not have id")
                )
            }
        }

        chain.doFilter(request, response)
    }

    override fun init(filterConfig: FilterConfig?) {
    }
}