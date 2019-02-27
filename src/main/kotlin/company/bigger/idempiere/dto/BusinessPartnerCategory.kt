package company.bigger.idempiere.dto

import org.compiere.crm.MCrmCategory

data class BusinessPartnerCategory(val id: Int, val name: String) {
    constructor(category: MCrmCategory) : this(category.id, category.name)
}