package company.bigger.idempiere.it

import company.bigger.idempiere.it.graphql.BusinessPartnersWithCategoriesResponse
import company.bigger.idempiere.it.graphql.CreateBusinessPartnerGraphQLResponse
import company.bigger.idempiere.it.graphql.CreateCategoryGraphQLResponse
import org.junit.Test
import kotlin.test.assertEquals

/**
 * CRM related integration tests
 */
class CRMIT : BaseIT() {
    /**
     * Can ask the GraphQL to create a category
     */
    @Test
    fun `Can ask the GraphQL to create a category`() {
        val query = """mutation {
  createCategory(name: "$TEST", value: "$TEST") { id name }
}"""
        val response: CreateCategoryGraphQLResponse = getPoorMansGraphQL(query)
        val result = response.data.createCategory.name
        assertEquals(TEST, result)
    }

    /**
     * Can create a business partner with GraphQL
     */
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

    /**
     * Can ask the GraphQL for business partners with categories
     */
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
}