package company.bigger.idempiere.it

import company.bigger.idempiere.it.graphql.GetProductsResponse
import org.junit.Test

/**
 * Product related integration tests
 */
class ProductIT : BaseIT() {
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


}