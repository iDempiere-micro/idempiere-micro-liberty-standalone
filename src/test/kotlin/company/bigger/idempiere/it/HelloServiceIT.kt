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

import company.bigger.idempiere.it.graphql.VersionRequest
import company.bigger.idempiere.resolver.QueryResolver
import io.aexp.nodes.graphql.GraphQLRequestEntity
import io.aexp.nodes.graphql.GraphQLResponseEntity
import io.aexp.nodes.graphql.GraphQLTemplate
import org.junit.Test
import kotlin.test.assertEquals

class HelloServiceIT {

    // Set up the path to the rest
    private val port = System.getProperty("liberty.test.port")
    private val contextName = System.getProperty("app.context.root")
    private val path = "graphql"
    private val graphQLUrl = "http://localhost:$port/$contextName/$path"

    /*private fun getGardenUserToken(): String {
        val client = ClientBuilder.newClient()
        val url = "http://localhost:$port/$contextName/session/GardenUser/login/GardenUser"
        val target = client.target(url)
        val response = target.request().get()
        assertEquals(200, response.status)
        return ""
    }*/

    fun <T> getGraphQL(t: Class<T>): GraphQLResponseEntity<T> {
        //val token = getGardenUserToken()
        val graphQLTemplate = GraphQLTemplate()
        val requestEntity = GraphQLRequestEntity.Builder()
            .url(graphQLUrl)
            //.headers(mapOf("Content-Type" to "application/graphql"))
            //.headers(mapOf("Authorization" to "Bearer $token"))
            .request(t)
            .build()
        return graphQLTemplate.query(requestEntity, t)
    }


    @Test
    fun `Can ask the GraphQL for version`() {
        // Make the request
        val responseEntity = getGraphQL(VersionRequest::class.java)
        assertEquals(QueryResolver.VER, responseEntity.response.v)
    }
}