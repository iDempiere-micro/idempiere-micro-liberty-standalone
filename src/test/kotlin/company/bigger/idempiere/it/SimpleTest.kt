package company.bigger.idempiere.it

import org.compiere.orm.DefaultModelFactory
import org.flywaydb.core.Flyway
import org.idempiere.common.util.EnvironmentServiceImpl
import org.idempiere.icommon.model.PersistentObject
import org.junit.Ignore
import org.junit.Test
import org.slf4j.impl.SimpleLogger
import software.hsharp.core.modules.BaseModuleImpl
import software.hsharp.core.util.DB
import software.hsharp.core.util.Environment
import software.hsharp.core.util.HikariCPI
import software.hsharp.core.util.queryOf

internal val sessionUrl =
    System.getenv("SESSION_URL") ?: "jdbc:postgresql://localhost:5433/idempiere?autosave=conservative"

val environmentService = EnvironmentServiceImpl(11, 0, 0)
internal val baseModule = BaseModuleImpl(environmentService = environmentService, modelFactory = DefaultModelFactory())

/**
 * Simple tests
 */
class SimpleTest {
    init {
        System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "WARN")
        HikariCPI.default(sessionUrl, "adempiere", "adempiere")

        // TODO: This should not be here. The migration should be exposed from the crm-core module
        // Create the Flyway instance and point it to the database
        val flyway =
            Flyway
                .configure()
                .dataSource(sessionUrl, "adempiere", "adempiere")
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .load()

        // Start the migration
        flyway.migrate()
    }

    /**
     * Helper test to try to find the table corresponding class name
     */
    @Ignore
    fun `instantiate every single class from ad_table dynamically`() {
        Environment.run(baseModule) {
            DB.run {
                val sql = "select tablename from ad_table where isactive = 'Y' and tablename not in ('AD_WF_Activity','AD_TreeNode','AD_Attachment','C_ValidCombination') and tablename not like 'RV_%'"
                val factory = DefaultModelFactory()
                val query = queryOf(sql, listOf()).map {
                    val tableName = it.string(1)
                    Pair(factory.getPO(tableName, 0), tableName)
                }.asList
                val result = DB.current.run(query)
                val found = result.filter { it.first != null }
                val not_found = result.filter { it.first == null }
                // found.forEach { println("if (tableName == \"${it.second}\") ${it.first::class}(recordId)") }
                // "AD_Private_Access" to {id: Int -> org.compiere.orm.MPrivateAccess(id)}
                found.forEach { println("\"${it.second}\" to {id: Int -> ${it.first::class}(id)},".replace(" class", "")) }
                println("-----------")
                not_found.forEach { println(it.second) }
            }
        }
    }

    /**
     * Helper test to make sure we are able to create a new instance of a class
     */
    @Test
    fun `instantiate statically`() {
        var instance: PersistentObject
        Environment.run(baseModule) {
            DB.run {
                instance = org.compiere.product.MAttributeInstance(0)
                instance = org.compiere.invoicing.MInvoiceTax(0)
                instance = org.compiere.accounting.MCost(0)
                instance = org.compiere.order.MOrderTax(0)
                instance = org.compiere.accounting.MAcctSchemaDefault(0)
                instance = org.compiere.orm.MUserRoles(0)
                instance = org.compiere.accounting.MAcctSchemaGL(0)
                instance = org.compiere.accounting.MFactAcct(0)
                instance = org.compiere.invoicing.MInOutLineMA(0)
                instance = org.compiere.production.MProductionLineMA(0)
                instance = org.compiere.product.MProductPrice(0)
                instance = org.compiere.process.MPInstancePara(0)
                instance = org.compiere.accounting.MProductPO(0)
                instance = org.compiere.crm.MContactInterest(0)
                instance = org.compiere.orm.X_AD_Role_Included(0)
                instance = org.compiere.invoicing.MInventoryLineMA(0)
                instance = org.compiere.accounting.MProductCategoryAcct(0)
                instance = org.compiere.orm.X_AD_PInstance_Log(0)
                instance = org.compiere.accounting.MCostDetail(0)
                instance = org.compiere.accounting.X_C_POSPayment(0)
                instance = org.compiere.production.MProductionLine(0)
                instance = org.compiere.accounting.MStorageReservation(0)
                instance = org.compiere.order.MRMATax(0)
                instance = org.compiere.accounting.MStorageOnHand(0)
                instance = org.compiere.product.MAttributeSet(0)
                instance = org.compiere.invoicing.MInOut(0)
                instance = org.compiere.accounting.MCash(0)
                instance = org.compiere.accounting.MCashLine(0)
                instance = org.compiere.process.MProcessPara(0)
                instance = org.compiere.accounting.X_C_Activity(0)
                instance = org.compiere.production.X_R_Category(0)
                instance = org.compiere.production.MStatus(0)
                instance = org.compiere.orm.MTree_Base(0)
                instance = org.compiere.orm.M_Element(0)
                instance = org.compiere.accounting.MPayment(0)
                instance = org.compiere.accounting.MRequisitionLine(0)
                instance = org.compiere.production.MProjectLine(0)
                instance = org.compiere.production.MAchievement(0)
                instance = org.compiere.production.MRequestUpdate(0)
                instance = org.compiere.product.X_M_SerNoCtlExclude(0)
                instance = org.compiere.orm.MTable(0)
                instance = org.compiere.orm.MColumn(0)
                instance = org.compiere.invoicing.MInOutConfirm(0)
                instance = org.compiere.invoicing.MInOutLineConfirm(0)
                instance = org.compiere.production.MStatusCategory(0)
                instance = org.compiere.crm.MRegion(0)
                instance = org.compiere.invoicing.MInventoryLine(0)
                instance = org.compiere.production.MRequestType(0)
                instance = org.compiere.bank.MBank(0)
                instance = org.compiere.bank.MBankAccount(0)
                instance = org.compiere.invoicing.MPaymentTerm(0)
                instance = org.compiere.accounting.MDistributionLine(0)
                instance = org.compiere.conversionrate.MConversionType(0)
                instance = org.compiere.accounting.MPeriod(0)
                instance = org.compiere.product.MProductCategory(0)
                instance = org.compiere.orm.MDocType(0)
                instance = org.compiere.accounting.MDistribution(0)
                instance = org.compiere.accounting.MOrderLine(0)
                instance = org.compiere.accounting.MAcctSchema(0)
                instance = org.compiere.order.MShipper(0)
                instance = org.compiere.product.MResource(0)
                instance = org.compiere.product.MResourceType(0)
                instance = org.compiere.tax.MTax(0)
                instance = org.compiere.crm.MBPartner(0)
                instance = org.compiere.invoicing.MBPBankAccount(0)
                instance = org.compiere.production.MPPProductBOM(0)
                instance = org.compiere.production.X_PP_Product_Planning(0)
                instance = org.compiere.validation.X_AD_ModelValidator(0)
                instance = org.compiere.invoicing.MRMA(0)
                instance = org.compiere.invoicing.MInOutLine(0)
                instance = org.compiere.production.MProductionPlan(0)
                instance = org.compiere.crm.MBPGroup(0)
                instance = org.compiere.invoicing.MInvoice(0)
                instance = org.compiere.accounting.MPaySelection(0)
                instance = org.compiere.product.X_M_AttributeSetExclude(0)
                instance = org.compiere.orm.MRefList(0)
                instance = org.compiere.accounting.MCharge(0)
                instance = org.compiere.accounting.MClient(0)
                instance = org.compiere.accounting.MCalendar(0)
                instance = org.compiere.orm.MRole(0)
                instance = org.compiere.accounting.MWarehouse(0)
                instance = org.compiere.accounting.X_M_Locator(0)
                instance = org.compiere.product.MPriceList(0)
                instance = org.compiere.accounting.MAcctSchemaElement(0)
                instance = org.compiere.tax.MTaxCategory(0)
                instance = org.compiere.invoicing.MInvoiceLine(0)
                instance = org.compiere.production.X_PP_Product_BOMLine(0)
                instance = org.compiere.product.MAssetGroup(0)
                instance = org.compiere.invoicing.MInvoicePaySchedule(0)
                instance = org.compiere.invoicing.MAsset(0)
                instance = org.compiere.order.MShipperPickupTypes(0)
                instance = org.compiere.order.MShipperPackaging(0)
                instance = org.compiere.invoicing.MAssetAcct(0)
                instance = org.compiere.invoicing.MAssetGroupAcct(0)
                instance = org.compiere.production.MProjectIssue(0)
                instance = org.compiere.product.MLot(0)
                instance = org.compiere.invoicing.MInventory(0)
                instance = org.compiere.accounting.MCashBook(0)
                instance = org.compiere.production.MMeasure(0)
                instance = org.compiere.invoicing.MDocTypeCounter(0)
                instance = org.compiere.accounting.MAllocationHdr(0)
                instance = org.compiere.production.MGroup(0)
                instance = org.compiere.production.MResolution(0)
                instance = org.compiere.accounting.MBankStatementLine(0)
                instance = org.compiere.product.MAttributeSetInstance(0)
                instance = org.compiere.product.MProductBOM(0)
                instance = org.compiere.accounting.MPaySelectionLine(0)
                instance = org.compiere.product.X_M_LotCtlExclude(0)
                instance = org.compiere.orm.MEntityType(0)
                instance = org.compiere.orm.X_AD_Reference(0)
                instance = org.compiere.crm.MCountry(0)
                instance = org.compiere.accounting.MCostElement(0)
                instance = org.compiere.bo.MCurrency(0)
                instance = org.compiere.product.MDiscountSchema(0)
                instance = org.compiere.production.MProjectType(0)
                instance = org.compiere.production.MProjectPhase(0)
                instance = org.compiere.crm.MLanguage(0)
                instance = org.compiere.accounting.MElementValue(0)
                instance = org.compiere.accounting.MMatchInv(0)
                instance = org.compiere.accounting.MMatchPO(0)
                instance = org.compiere.accounting.MElement(0)
                instance = org.compiere.product.MUOM(0)
                instance = org.compiere.crm.MLocation(0)
                instance = org.compiere.accounting.MUOMConversion(0)
                instance = org.compiere.accounting.MYear(0)
                instance = org.compiere.order.MRMALine(0)
                instance = org.compiere.production.MProject(0)
                instance = org.compiere.invoicing.MInvoiceSchedule(0)
                instance = org.compiere.accounting.MOrder(0)
                instance = org.compiere.accounting.X_C_Campaign(0)
                instance = org.compiere.process.MProcess(0)
                instance = org.compiere.orm.MSysConfig(0)
                instance = org.compiere.accounting.MProduct(0)
                instance = org.compiere.tax.MTaxPostal(0)
                instance = org.compiere.crm.MBPartnerLocation(0)
                instance = org.compiere.orm.MViewColumn(0)
                instance = org.compiere.production.MMeasureCalc(0)
                instance = org.compiere.order.MOrderPaySchedule(0)
                instance = org.compiere.accounting.MBankStatement(0)
                instance = org.compiere.production.MColorSchema(0)
                instance = org.compiere.accounting.MHierarchy(0)
                instance = org.compiere.product.MPriceListVersion(0)
                instance = org.compiere.validation.MTableScriptValidator(0)
                instance = org.compiere.invoicing.MDepreciation(0)
                instance = org.compiere.crm.MUser(0)
                instance = org.compiere.accounting.MJournalLine(0)
                instance = org.compiere.invoicing.MDepreciationWorkfile(0)
                instance = org.compiere.invoicing.MDepreciationExp(0)
                instance = org.compiere.invoicing.MAssetType(0)
                instance = org.compiere.invoicing.MPaymentTransaction(0)
                instance = org.compiere.production.MQualityTest(0)
                instance = org.compiere.order.MShipperLabels(0)
                instance = org.compiere.production.MProduction(0)
                instance = org.compiere.orm.MIndexColumn(0)
                instance = org.compiere.orm.MViewComponent(0)
                instance = org.compiere.accounting.MOrderLandedCost(0)
                instance = org.compiere.accounting.MOrderLandedCostAllocation(0)
                instance = org.compiere.production.MTransaction(0)
                instance = org.compiere.invoicing.MLocatorType(0)
                instance = org.compiere.bo.MOpportunity(0)
                instance = org.compiere.bo.X_C_SalesStage(0)
                instance = org.compiere.crm.MCountryGroup(0)
                instance = org.compiere.crm.MCrmCategory(0)
                instance = org.compiere.crm.MCrmCustomerCategory(0)
            }
        }
    }

    /**
     * Helper test to make sure we are able to load an instance of a class
     */
    @Test
    fun `instantiate statically from row`() {
        var instance: PersistentObject

        Environment.run(baseModule) {
            DB.run {
                val query = queryOf("select 1 where 0=1", listOf()).map { row ->
                    instance = org.compiere.product.MAttributeInstance(row)
                    instance = org.compiere.invoicing.MInvoiceTax(row)
                    instance = org.compiere.accounting.MCost(row)
                    instance = org.compiere.order.MOrderTax(row)
                    instance = org.compiere.accounting.MAcctSchemaDefault(row)
                    instance = org.compiere.orm.MUserRoles(row)
                    instance = org.compiere.accounting.MAcctSchemaGL(row)
                    instance = org.compiere.accounting.MFactAcct(row)
                    instance = org.compiere.invoicing.MInOutLineMA(row)
                    instance = org.compiere.production.MProductionLineMA(row)
                    instance = org.compiere.product.MProductPrice(row)
                    instance = org.compiere.process.MPInstancePara(row)
                    instance = org.compiere.accounting.MProductPO(row)
                    instance = org.compiere.crm.MContactInterest(row)
                    instance = org.compiere.orm.X_AD_Role_Included(row)
                    instance = org.compiere.invoicing.MInventoryLineMA(row)
                    instance = org.compiere.accounting.MProductCategoryAcct(row)
                    instance = org.compiere.orm.X_AD_PInstance_Log(row)
                    instance = org.compiere.accounting.MCostDetail(row)
                    instance = org.compiere.accounting.X_C_POSPayment(row)
                    instance = org.compiere.production.MProductionLine(row)
                    instance = org.compiere.accounting.MStorageReservation(row)
                    instance = org.compiere.order.MRMATax(row)
                    instance = org.compiere.accounting.MStorageOnHand(row)
                    instance = org.compiere.product.MAttributeSet(row)
                    instance = org.compiere.invoicing.MInOut(row)
                    instance = org.compiere.accounting.MCash(row)
                    instance = org.compiere.accounting.MCashLine(row)
                    instance = org.compiere.process.MProcessPara(row)
                    instance = org.compiere.accounting.X_C_Activity(row)
                    instance = org.compiere.production.X_R_Category(row)
                    instance = org.compiere.production.MStatus(row)
                    instance = org.compiere.orm.MTree_Base(row)
                    instance = org.compiere.orm.M_Element(row)
                    instance = org.compiere.accounting.MPayment(row)
                    instance = org.compiere.accounting.MRequisitionLine(row)
                    instance = org.compiere.production.MProjectLine(row)
                    instance = org.compiere.production.MAchievement(row)
                    instance = org.compiere.production.MRequestUpdate(row)
                    instance = org.compiere.product.X_M_SerNoCtlExclude(row)
                    instance = org.compiere.orm.MTable(row)
                    instance = org.compiere.orm.MColumn(row)
                    instance = org.compiere.invoicing.MInOutConfirm(row)
                    instance = org.compiere.invoicing.MInOutLineConfirm(row)
                    instance = org.compiere.production.MStatusCategory(row)
                    instance = org.compiere.crm.MRegion(row)
                    instance = org.compiere.invoicing.MInventoryLine(row)
                    instance = org.compiere.production.MRequestType(row)
                    instance = org.compiere.bank.MBank(row)
                    instance = org.compiere.bank.MBankAccount(row)
                    instance = org.compiere.invoicing.MPaymentTerm(row)
                    instance = org.compiere.accounting.MDistributionLine(row)
                    instance = org.compiere.conversionrate.MConversionType(row)
                    instance = org.compiere.accounting.MPeriod(row)
                    instance = org.compiere.product.MProductCategory(row)
                    instance = org.compiere.orm.MDocType(row)
                    instance = org.compiere.accounting.MDistribution(row)
                    instance = org.compiere.accounting.MOrderLine(row)
                    instance = org.compiere.accounting.MAcctSchema(row)
                    instance = org.compiere.order.MShipper(row)
                    instance = org.compiere.product.MResource(row)
                    instance = org.compiere.product.MResourceType(row)
                    instance = org.compiere.tax.MTax(row)
                    instance = org.compiere.crm.MBPartner(row)
                    instance = org.compiere.invoicing.MBPBankAccount(row)
                    instance = org.compiere.production.MPPProductBOM(row)
                    instance = org.compiere.production.X_PP_Product_Planning(row)
                    instance = org.compiere.validation.X_AD_ModelValidator(row)
                    instance = org.compiere.invoicing.MRMA(row)
                    instance = org.compiere.invoicing.MInOutLine(row)
                    instance = org.compiere.production.MProductionPlan(row)
                    instance = org.compiere.crm.MBPGroup(row)
                    instance = org.compiere.invoicing.MInvoice(row)
                    instance = org.compiere.accounting.MPaySelection(row)
                    instance = org.compiere.product.X_M_AttributeSetExclude(row)
                    instance = org.compiere.orm.MRefList(row)
                    instance = org.compiere.accounting.MCharge(row)
                    instance = org.compiere.accounting.MClient(row)
                    instance = org.compiere.accounting.MCalendar(row)
                    instance = org.compiere.orm.MRole(row)
                    instance = org.compiere.accounting.MWarehouse(row)
                    instance = org.compiere.accounting.X_M_Locator(row)
                    instance = org.compiere.product.MPriceList(row)
                    instance = org.compiere.accounting.MAcctSchemaElement(row)
                    instance = org.compiere.tax.MTaxCategory(row)
                    instance = org.compiere.invoicing.MInvoiceLine(row)
                    instance = org.compiere.production.X_PP_Product_BOMLine(row)
                    instance = org.compiere.product.MAssetGroup(row)
                    instance = org.compiere.invoicing.MInvoicePaySchedule(row)
                    instance = org.compiere.invoicing.MAsset(row)
                    instance = org.compiere.order.MShipperPickupTypes(row)
                    instance = org.compiere.order.MShipperPackaging(row)
                    instance = org.compiere.invoicing.MAssetAcct(row)
                    instance = org.compiere.invoicing.MAssetGroupAcct(row)
                    instance = org.compiere.production.MProjectIssue(row)
                    instance = org.compiere.product.MLot(row)
                    instance = org.compiere.invoicing.MInventory(row)
                    instance = org.compiere.accounting.MCashBook(row)
                    instance = org.compiere.production.MMeasure(row)
                    instance = org.compiere.invoicing.MDocTypeCounter(row)
                    instance = org.compiere.accounting.MAllocationHdr(row)
                    instance = org.compiere.production.MGroup(row)
                    instance = org.compiere.production.MResolution(row)
                    instance = org.compiere.accounting.MBankStatementLine(row)
                    instance = org.compiere.product.MAttributeSetInstance(row)
                    instance = org.compiere.product.MProductBOM(row)
                    instance = org.compiere.accounting.MPaySelectionLine(row)
                    instance = org.compiere.product.X_M_LotCtlExclude(row)
                    instance = org.compiere.orm.MEntityType(row)
                    instance = org.compiere.orm.X_AD_Reference(row)
                    instance = org.compiere.crm.MCountry(row)
                    instance = org.compiere.accounting.MCostElement(row)
                    instance = org.compiere.orm.MOrg(row)
                    instance = org.compiere.bo.MCurrency(row)
                    instance = org.compiere.product.MDiscountSchema(row)
                    instance = org.compiere.production.MProjectType(row)
                    instance = org.compiere.production.MProjectPhase(row)
                    instance = org.compiere.crm.MLanguage(row)
                    instance = org.compiere.accounting.MElementValue(row)
                    instance = org.compiere.accounting.MMatchInv(row)
                    instance = org.compiere.accounting.MMatchPO(row)
                    instance = org.compiere.accounting.MElement(row)
                    instance = org.compiere.product.MUOM(row)
                    instance = org.compiere.crm.MLocation(row)
                    instance = org.compiere.accounting.MUOMConversion(row)
                    instance = org.compiere.accounting.MYear(row)
                    instance = org.compiere.order.MRMALine(row)
                    instance = org.compiere.production.MProject(row)
                    instance = org.compiere.invoicing.MInvoiceSchedule(row)
                    instance = org.compiere.accounting.MOrder(row)
                    instance = org.compiere.accounting.X_C_Campaign(row)
                    instance = org.compiere.process.MProcess(row)
                    instance = org.compiere.orm.MSysConfig(row)
                    instance = org.compiere.accounting.MProduct(row)
                    instance = org.compiere.tax.MTaxPostal(row)
                    instance = org.compiere.crm.MBPartnerLocation(row)
                    instance = org.compiere.orm.MViewColumn(row)
                    instance = org.compiere.production.MMeasureCalc(row)
                    instance = org.compiere.order.MOrderPaySchedule(row)
                    instance = org.compiere.accounting.MBankStatement(row)
                    instance = org.compiere.production.MColorSchema(row)
                    instance = org.compiere.accounting.MHierarchy(row)
                    instance = org.compiere.product.MPriceListVersion(row)
                    instance = org.compiere.validation.MTableScriptValidator(row)
                    instance = org.compiere.invoicing.MDepreciation(row)
                    instance = org.compiere.crm.MUser(row)
                    instance = org.compiere.accounting.MJournalLine(row)
                    instance = org.compiere.invoicing.MDepreciationWorkfile(row)
                    instance = org.compiere.invoicing.MDepreciationExp(row)
                    instance = org.compiere.invoicing.MAssetType(row)
                    instance = org.compiere.invoicing.MPaymentTransaction(row)
                    instance = org.compiere.production.MQualityTest(row)
                    instance = org.compiere.order.MShipperLabels(row)
                    instance = org.compiere.production.MProduction(row)
                    instance = org.compiere.orm.MIndexColumn(row)
                    instance = org.compiere.orm.MViewComponent(row)
                    instance = org.compiere.accounting.MOrderLandedCost(row)
                    instance = org.compiere.accounting.MOrderLandedCostAllocation(row)
                    instance = org.compiere.production.MTransaction(row)
                    instance = org.compiere.invoicing.MLocatorType(row)
                    instance = org.compiere.bo.MOpportunity(row)
                    instance = org.compiere.bo.X_C_SalesStage(row)
                    instance = org.compiere.crm.MCountryGroup(row)
                    instance = org.compiere.crm.MCrmCategory(row)
                    instance = org.compiere.crm.MCrmCustomerCategory(row)
                    instance
                }.asSingle
            }
        }
    }
}