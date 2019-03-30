package company.bigger.idempiere.it.graphql

data class Product(
    val StorageOnHand: List<Any>,
    val UOM: UOM,
    val id: String,
    val name: String
)