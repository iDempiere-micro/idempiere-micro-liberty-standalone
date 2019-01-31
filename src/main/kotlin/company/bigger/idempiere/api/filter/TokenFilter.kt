package company.bigger.idempiere.api.filter

import javax.servlet.*
import javax.servlet.annotation.WebFilter

@WebFilter( "/*" )
class TokenFilter : Filter {

    override fun destroy() {
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        chain?.doFilter( request, response );
    }

    override fun init(filterConfig: FilterConfig?) {
    }
}