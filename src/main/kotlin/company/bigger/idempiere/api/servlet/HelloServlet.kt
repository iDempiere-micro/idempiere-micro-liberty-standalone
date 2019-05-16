package company.bigger.idempiere.api.servlet

import com.coxautodev.graphql.tools.SchemaParser
import company.bigger.idempiere.di.ModuleImpl
import company.bigger.idempiere.di.globalContext
import company.bigger.idempiere.resolver.MutationResolver
import company.bigger.idempiere.resolver.QueryResolver
import graphql.schema.GraphQLSchema
import graphql.servlet.GraphQLConfiguration
import graphql.servlet.GraphQLHttpServlet
import software.hsharp.core.services.EnvironmentService
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment
import space.traversal.kapsule.Injects
import space.traversal.kapsule.inject
import space.traversal.kapsule.required
import javax.servlet.annotation.WebServlet
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletRequest

@WebServlet(name = "HelloServlet", urlPatterns = ["graphql"], loadOnStartup = 1)
class HelloServlet : GraphQLHttpServlet(), Injects<ModuleImpl> {
    private val environmentService: EnvironmentService by required { environmentService }

    init {
        inject(globalContext.module)
    }

    override fun getConfiguration(): GraphQLConfiguration {
        return GraphQLConfiguration.with(createSchema()).build()
    }

    private fun createSchema(): GraphQLSchema {
        return SchemaParser.newParser()
            .file("graphql/app.graphqls")
            .resolvers(QueryResolver(), MutationResolver())
            .build().makeExecutableSchema()
    }

    @Throws(ServletException::class, IOException::class)
    public override fun doPost(request: HttpServletRequest, response: HttpServletResponse) {
        setAccessControlHeaders(response)
        // Environment new instance creation must stay here
        Environment(globalContext.module).run {
            DB.run { super.doPost(request, response) }
        }
    }

    // for Preflight
    @Throws(ServletException::class, IOException::class)
    override fun doOptions(req: HttpServletRequest?, resp: HttpServletResponse) {
        setAccessControlHeaders(resp)
        resp.status = HttpServletResponse.SC_OK
    }

    private fun setAccessControlHeaders(resp: HttpServletResponse) {
        resp.setHeader("Access-Control-Allow-Origin", "*")
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        resp.setHeader("Access-Control-Allow-Credentials", "true")
        resp.setHeader(
            "Access-Control-Allow-Headers",
            "origin, content-type, accept, authorization"
        )
    }
}