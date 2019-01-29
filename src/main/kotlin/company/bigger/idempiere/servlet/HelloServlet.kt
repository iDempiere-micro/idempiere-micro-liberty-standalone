package company.bigger.idempiere.servlet

import com.coxautodev.graphql.tools.SchemaParser
import company.bigger.idempiere.resolver.QueryResolver
import graphql.schema.GraphQLSchema
import graphql.servlet.GraphQLConfiguration
import graphql.servlet.GraphQLHttpServlet
import javax.servlet.annotation.WebServlet

@WebServlet(name = "HelloServlet", urlPatterns = arrayOf("graphql"), loadOnStartup = 1)
class HelloServlet : GraphQLHttpServlet() {

    override fun getConfiguration(): GraphQLConfiguration {
        return GraphQLConfiguration.with(createSchema()).build()
    }

    private fun createSchema(): GraphQLSchema {
        return SchemaParser.newParser()
            .file("graphql/app.graphqls")
            .resolvers(QueryResolver())
            .build().makeExecutableSchema()
    }

}