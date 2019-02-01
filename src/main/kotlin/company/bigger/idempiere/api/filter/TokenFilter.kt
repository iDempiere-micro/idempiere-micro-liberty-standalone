package company.bigger.idempiere.api.filter

import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest

private const val AUTH_HEADER_KEY = "Authorization"
private const val AUTH_HEADER_VALUE_PREFIX = "Bearer " // with trailing space to separate token
private const val AUTH_HEADER_VALUE_PREFIX_ALT = "Token " // with trailing space to separate token
private const val STATUS_CODE_UNAUTHORIZED = 401

@WebFilter( "/*" )
class TokenFilter : Filter {

    private fun getToken(request: HttpServletRequest): String? {

        val authHeader = request.getHeader(AUTH_HEADER_KEY)
        return if (authHeader != null && (authHeader.startsWith(AUTH_HEADER_VALUE_PREFIX) || authHeader.startsWith("Token "))) {
            authHeader.replace(AUTH_HEADER_VALUE_PREFIX, "").replace("Token ", "")
        } else request.parameterMap["access_token"]?.firstOrNull()
    }

    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest

        chain?.doFilter( request, response );
    }

    override fun init(filterConfig: FilterConfig?) {
    }
}
