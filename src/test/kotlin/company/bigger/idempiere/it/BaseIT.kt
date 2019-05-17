package company.bigger.idempiere.it

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import company.bigger.idempiere.it.rest.UserLoginModelResponse
import io.aexp.nodes.graphql.GraphQLRequestEntity
import io.aexp.nodes.graphql.GraphQLResponseEntity
import io.aexp.nodes.graphql.GraphQLTemplate
import khttp.post
import javax.ws.rs.client.ClientBuilder
import kotlin.test.assertEquals

internal const val USER = "GardenUser"
internal val TEST = "test-" + randomString(5)
internal const val USER_ID = 102

/**
 * Integration tests base
 */
abstract class BaseIT {
    // Set up the path to the rest
    protected val port = System.getProperty("liberty.test.port")
    protected val contextName = System.getProperty("app.context.root")
    protected val path = "graphql"
    protected val graphQLUrl = "http://localhost:$port/$contextName/$path"

    protected fun getGardenUserToken(): String {
        val result: UserLoginModelResponse = getRest("/session/$USER/login/$USER")

        return result.token
    }

    protected inline fun <reified T : Any> getRest(urlPart: String): T {
        val url = "http://localhost:$port/$contextName/$urlPart"

        val client = ClientBuilder.newClient()
        val target = client.target(url)
        val response = target.request().get()
        assertEquals(200, response.status)
        val json = response.readEntity(String::class.java)
        println("json response received: '$json'")

        response.close()
        client.close()

        val mapper = ObjectMapper().registerModule(KotlinModule())
        val result: T = mapper.readValue(json)

        return result
    }

    protected fun <T> getGraphQL(t: Class<T>): GraphQLResponseEntity<T> {
        val token = getGardenUserToken()
        val graphQLTemplate = GraphQLTemplate()
        val requestEntity = GraphQLRequestEntity.Builder()
            .url(graphQLUrl)
            // .headers(mapOf("Content-Type" to "application/graphql"))
            .headers(mapOf("Authorization" to "Bearer $token"))
            .request(t)
            .build()
        return graphQLTemplate.query(requestEntity, t)
    }

    protected inline fun <reified T : Any> getPoorMansGraphQL(query: String): T {
        val token = getGardenUserToken()
        val payload = mapOf("query" to query)
        val r =
            post(
                graphQLUrl,
                json = payload,
                headers = mapOf(
                    "Authorization" to "Bearer $token"
                )
            )
        val json = r.text
        println("json response received: '$json'")

        val mapper = ObjectMapper().registerModule(KotlinModule())
        val result: T = mapper.readValue(json)

        return result
    }
}