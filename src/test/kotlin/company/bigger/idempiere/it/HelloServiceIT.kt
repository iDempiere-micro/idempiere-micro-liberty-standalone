/*******************************************************************************
 * (c) Copyright IBM Corporation 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package company.bigger.idempiere.it

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import company.bigger.idempiere.it.graphql.CurrentUserGraphQLResponse
import company.bigger.idempiere.it.graphql.EchoGraphQLResponse
import company.bigger.idempiere.it.graphql.GetUsersGraphQLResponse
import company.bigger.idempiere.it.graphql.VersionRequest
import company.bigger.idempiere.it.rest.CurrentUserResponse
import company.bigger.idempiere.it.rest.UserLoginModelResponse
import company.bigger.idempiere.resolver.QueryResolver
import io.aexp.nodes.graphql.GraphQLRequestEntity
import io.aexp.nodes.graphql.GraphQLResponseEntity
import io.aexp.nodes.graphql.GraphQLTemplate
import khttp.post
import org.junit.Ignore
import org.junit.Test
import javax.ws.rs.client.ClientBuilder
import kotlin.test.assertEquals

private const val USER = "GardenUser"
private const val TEST = "test"
private const val USER_ID = 102

class HelloServiceIT {

    // Set up the path to the rest
    private val port = System.getProperty("liberty.test.port")
    private val contextName = System.getProperty("app.context.root")
    private val path = "graphql"
    private val graphQLUrl = "http://localhost:$port/$contextName/$path"

    private fun getGardenUserToken(): String {
        val result: UserLoginModelResponse = getRest("/session/$USER/login/$USER")

        return result.token
    }

    private inline fun <reified T : Any> getRest(urlPart: String): T {
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

    fun <T> getGraphQL(t: Class<T>): GraphQLResponseEntity<T> {
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

    private inline fun <reified T : Any> getPoorMansGraphQL(query: String): T {
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

    @Test
    fun `can login and ask for me`() {
        val token = getGardenUserToken()
        val me: CurrentUserResponse = getRest("/session/me?access_token=$token")
        assertEquals(USER_ID, me.id)
        assertEquals(USER, me.name)
    }

    @Test
    fun `Can ask the GraphQL for version`() {
        // Make the request
        val responseEntity = getGraphQL(VersionRequest::class.java)
        assertEquals(QueryResolver.VER, responseEntity.response.v)
    }

    @Test
    fun `Can ask the GraphQL for me`() {
        val query = """query {
    me {
    	id
    	description
    }
}"""
        val response: CurrentUserGraphQLResponse = getPoorMansGraphQL(query)
        val me = response.data.me
        assertEquals(USER_ID.toString(), me.id)
        assertEquals(USER, me.description)
    }

    @Test
    fun `Can ask the GraphQL for users`() {
        val query = """query {
    users {
    	id
    	description
    }
}"""
        val response: GetUsersGraphQLResponse = getPoorMansGraphQL(query)
        val users = response.data.users
        assertEquals(2, users.size)
        assertEquals(USER, users.first { it.id == USER_ID.toString() }.description)
    }

    @Test
    fun `Can ask the GraphQL to echo`() {
        val query = """mutation {
  echo(what: "$TEST")
}"""
        val response: EchoGraphQLResponse = getPoorMansGraphQL(query)
        val result = response.data.echo
        assertEquals(TEST, result)
    }

    // TODO: run CRM migrations to add a category
    @Ignore
    fun `Can ask the GraphQL to create a category`() {
        val query = """mutation {
  createCategory(name: "$TEST", value: "$TEST")
}"""
        val response: EchoGraphQLResponse = getPoorMansGraphQL(query)
        val result = response.data.echo
        assertEquals(TEST, result)
    }
}