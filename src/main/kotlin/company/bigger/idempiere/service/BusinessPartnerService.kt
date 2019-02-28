package company.bigger.idempiere.service

import mu.KotlinLogging
import org.compiere.crm.MBPartner
import org.compiere.crm.MBPartnerLocation
import org.compiere.crm.MCountry
import org.compiere.crm.MRegion
import org.compiere.crm.MLocation
import org.compiere.model.I_C_BPartner
import org.compiere.orm.Query
import org.slf4j.impl.SimpleLogger
import software.hsharp.core.models.EnvironmentService

private val logger = {
    System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE")
    KotlinLogging.logger {}
}.invoke()

class BusinessPartnerService(
    private val environmentService: EnvironmentService
) {
    fun getBusinessPartners(): List<I_C_BPartner> {
        logger.trace { "getBusinessPartners, AD_Client_ID=${environmentService.clientId}" }

        return Query(environmentService.context, I_C_BPartner.Table_Name, "AD_Client_ID=?")
            .setParameters(environmentService.clientId)
            .list()
    }

    fun createBusinessPartner(name: String, searchKey: String): I_C_BPartner {
        val ctx = environmentService.context
        val newPartner = MBPartner.getTemplate(ctx, environmentService.clientId)
        newPartner.setName(name)
        newPartner.setSearchKey(searchKey)
        newPartner.save()

        val defaultCountry = MCountry.getDefault(ctx)
        val defaultRegion = MRegion.getDefault(ctx)
        val location = MLocation(defaultCountry, defaultRegion)
        location.save()
        val partnerLocation = MBPartnerLocation(newPartner)
        partnerLocation.c_Location_ID = location.c_Location_ID
        partnerLocation.save()

        return newPartner
    }
}