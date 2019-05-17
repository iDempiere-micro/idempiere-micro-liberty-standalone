package company.bigger.idempiere.it

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import company.bigger.idempiere.it.graphql.BusinessPartnersWithCategoriesResponse
import company.bigger.idempiere.it.graphql.CurrentUserGraphQLResponse
import company.bigger.idempiere.it.graphql.EchoGraphQLResponse
import company.bigger.idempiere.it.graphql.GetUsersGraphQLResponse
import company.bigger.idempiere.it.graphql.VersionRequest
import company.bigger.idempiere.it.graphql.ComplexEchoGraphQLResponse
import company.bigger.idempiere.it.graphql.CreateBusinessPartnerGraphQLResponse
import company.bigger.idempiere.it.graphql.CreateCategoryGraphQLResponse
import company.bigger.idempiere.it.graphql.GetProductsResponse
import company.bigger.idempiere.it.graphql.GetServiceOrderGraphQLResponse
import company.bigger.idempiere.it.rest.CurrentUserResponse
import company.bigger.idempiere.it.rest.UserLoginModelResponse
import company.bigger.idempiere.resolver.QueryResolver
import io.aexp.nodes.graphql.GraphQLRequestEntity
import io.aexp.nodes.graphql.GraphQLResponseEntity
import io.aexp.nodes.graphql.GraphQLTemplate
import khttp.post
import org.junit.Test
import java.util.Random
import javax.ws.rs.client.ClientBuilder
import kotlin.test.assertEquals

/**
 * Generate a random string (small letters)
 */
fun randomString(length: Int): String {
    fun ClosedRange<Char>.randomString(length: Int) =
        (1..length)
            .map { (Random().nextInt(endInclusive.toInt() - start.toInt()) + start.toInt()).toChar() }
            .joinToString("")
    return ('a'..'z').randomString(length)
}

private const val USER = "GardenUser"
private val TEST = "test-" + randomString(5)
private const val USER_ID = 102

/**
 * Integration tests
 */
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

    @Test
    fun `Can ask the GraphQL to create a category`() {
        val query = """mutation {
  createCategory(name: "$TEST", value: "$TEST") { id name }
}"""
        val response: CreateCategoryGraphQLResponse = getPoorMansGraphQL(query)
        val result = response.data.createCategory.name
        assertEquals(TEST, result)
    }

    @Test
    fun `Can ask the GraphQL to complex echo with input`() {
        val query = """mutation {
  echoComplex(what: { content: "$TEST" } )
}"""
        val response: ComplexEchoGraphQLResponse = getPoorMansGraphQL(query)
        val result = response.data.echoComplex
        assertEquals(TEST, result)
    }

    @Test
    fun `Can create a business partner with GraphQL`() {
        val query = """mutation {
  createBusinessPartner(businessPartner: { legalName: "$TEST", searchKey: "$TEST" } ) {
    id
    name
  }
}"""
        val response: CreateBusinessPartnerGraphQLResponse = getPoorMansGraphQL(query)
        val result = response.data.createBusinessPartner.name
        assertEquals(TEST, result)
    }

    @Test
    fun `Can ask the GraphQL for business partners with categories`() {
        val query = """query {
    businessPartners {
    	id
    	name
    	searchKey
    	IsCustomer
    	URL
    	categories {
    	    id
    	    name
    	}
    }
}"""
        val response: BusinessPartnersWithCategoriesResponse = getPoorMansGraphQL(query)
        val businessPartners = response.data
    }

    @Test
    fun `can ask the GrapQL for products with hands on quantities`() {
        val query = """query {
    products {
	    id
		name
		UOM {
			Name
		}
    	StorageOnHand {
    		QtyOnHand
    	}
    }
}"""
        val response: GetProductsResponse = getPoorMansGraphQL(query)
        val products = response.data
    }

    @Test
    fun `Can ask the GraphQL for a sales order`() {
        val query = """query {
    salesOrder(id: 105) {
	    DocumentNo
		Description
    	DateOrderedISOFormat
    	GrandTotal
    	Customer {
    	  id
    	  name
    	}
    	Lines {
    		Product {
    		    id
    		    name
    		}
    		QtyOrdered
    		UOM {
    		    Name
    		    UOMSymbol
    		}
    	}
    }
}"""
        val response: GetServiceOrderGraphQLResponse = getPoorMansGraphQL(query)
        val serviceOrder = response.data
        assertEquals("800001", serviceOrder.salesOrder.DocumentNo)
    }
}