package gcc.kt.rest

import com.coxautodev.graphql.tools.SchemaParser
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