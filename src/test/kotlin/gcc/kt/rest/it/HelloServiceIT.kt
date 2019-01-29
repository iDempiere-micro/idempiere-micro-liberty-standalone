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
package gcc.kt.rest.it

import org.junit.Assert.assertEquals
import org.junit.Test

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Invocation
import javax.ws.rs.client.WebTarget
import javax.ws.rs.core.Response

class HelloServiceIT {

    @Test
    fun testApplication() {

        // Set up the path to the service
        val port = System.getProperty("liberty.test.port")
        val contextName = System.getProperty("app.context.root")
        val path = "graphql"
        val url = "http://localhost:$port/$contextName/$path"

        // Make the request
        val client = ClientBuilder.newClient()
        val target = client.target(url)
        val response = target.request().header("Content-Type", "application/graphql").post(null)

        // Test we got an OK response
        // TODO : write correct test
        // assertEquals("Incorrect response code from " + url, 200, response.getStatus())

        response.close()
    }
}