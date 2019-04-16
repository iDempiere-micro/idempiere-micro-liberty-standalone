package company.bigger.idempiere.it.graphql

data class SalesOrder(
    val Customer: Customer,
    val DateOrderedISOFormat: String,
    val Description: String,
    val DocumentNo: String,
    val GrandTotal: Double,
    val Lines: List<Line>
)