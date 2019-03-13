package company.bigger.idempiere.dto

import org.compiere.model.CreateBusinessPartnerInput

data class BusinessPartnerInput(
    override val searchKey: String,
    override val legalName: String,
    override val categoryName: String?,
    override val dunsNumber: String?,
    override val vatNumber: String?,
    override val email: String?,
    override val isCustomer: Boolean?,
    override val note: String?,
    override val flatDiscount: Int?,
    override val branchName: String?,
    override val branchPhone: String?,
    override val branchStreet: String?,
    override val branchCity: String?,
    override val branchZip: String?,
    override val branchCountry: String?,
    override val ownerPhone: String?,
    override val legalStreet: String?,
    override val legalCity: String?,
    override val legalZip: String?,
    override val legalCountry: String?,
    override val orderContactName: String?,
    override val orderContactPhone: String?,
    override val orderContactEmail: String?,
    override val decisionMakerContactName: String?,
    override val decisionMakerContactPhone: String?,
    override val decisionMakerContactEmail: String?,
    override val invoicingContactName: String?,
    override val invoicingContactPhone: String?,
    override val invoicingContactEmail: String?
) : CreateBusinessPartnerInput