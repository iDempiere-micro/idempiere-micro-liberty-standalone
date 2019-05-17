package company.bigger.idempiere.it

import company.bigger.idempiere.it.graphql.CurrentUserGraphQLResponse
import company.bigger.idempiere.it.graphql.EchoGraphQLResponse
import company.bigger.idempiere.it.graphql.GetUsersGraphQLResponse
import company.bigger.idempiere.it.graphql.VersionRequest
import company.bigger.idempiere.it.graphql.ComplexEchoGraphQLResponse
import company.bigger.idempiere.it.graphql.GetServiceOrderGraphQLResponse
import company.bigger.idempiere.it.rest.CurrentUserResponse
import company.bigger.idempiere.resolver.QueryResolver
import org.junit.Test
import java.util.Random
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

/**
 * User related integration tests
 */
class UserServiceIT : BaseIT() {

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
    fun `Can ask the GraphQL to complex echo with input`() {
        val query = """mutation {
  echoComplex(what: { content: "$TEST" } )
}"""
        val response: ComplexEchoGraphQLResponse = getPoorMansGraphQL(query)
        val result = response.data.echoComplex
        assertEquals(TEST, result)
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