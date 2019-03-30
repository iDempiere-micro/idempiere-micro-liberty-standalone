package company.bigger.idempiere.it

import kotliquery.Row
import org.compiere.orm.DefaultModelFactory
import org.flywaydb.core.Flyway
import org.idempiere.common.util.Env
import org.idempiere.icommon.model.IPO
import org.junit.Test
import org.slf4j.impl.SimpleLogger
import software.hsharp.core.models.EnvironmentService
import software.hsharp.core.util.DB
import software.hsharp.core.util.HikariCPI
import software.hsharp.core.util.queryOf

internal val sessionUrl =
    System.getenv("SESSION_URL") ?: "jdbc:postgresql://localhost:5433/idempiere?autosave=conservative"

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

    @Test
    fun `instantiate every single class from ad_table dynamically`() {
        DB.run {
            val sql = "select tablename from ad_table where isactive = 'Y' and tablename <> 'AD_WF_Activity'"
            val factory = DefaultModelFactory()
            val query = queryOf(sql, listOf()).map { factory.getPO(it.string(1), 0) }.asList
            DB.current.run(query).forEach { println(it::class) }
        }
    }

    @Test
    fun `instantiate statically`() {
        var instance : IPO
        val ctx = Env.getCtx()
        DB.run {
            instance = org.compiere.product.MAttributeInstance(ctx, 0)
            instance = org.compiere.invoicing.MInvoiceTax(ctx, 0)
            instance = org.compiere.accounting.MCost(ctx, 0)
            instance = org.compiere.order.MOrderTax(ctx, 0)
            instance = org.compiere.accounting.MAcctSchemaDefault(ctx, 0)
            instance = org.compiere.orm.MUserRoles(ctx, 0)
            instance = org.compiere.accounting.MAcctSchemaGL(ctx, 0)
            instance = org.compiere.accounting.MFactAcct(ctx, 0)
            instance = org.compiere.invoicing.MInOutLineMA(ctx, 0)
            instance = org.compiere.production.MProductionLineMA(ctx, 0)
            instance = org.compiere.product.MProductPrice(ctx, 0)
            instance = org.compiere.process.MPInstancePara(ctx, 0)
            instance = org.compiere.accounting.MProductPO(ctx, 0)
            instance = org.compiere.crm.MContactInterest(ctx, 0)
            instance = org.compiere.orm.X_AD_Role_Included(ctx, 0)
            instance = org.compiere.invoicing.MInventoryLineMA(ctx, 0)
            instance = org.compiere.accounting.MProductCategoryAcct(ctx, 0)
            instance = org.compiere.orm.X_AD_PInstance_Log(ctx, 0)
            instance = org.compiere.accounting.MCostDetail(ctx, 0)
            instance = org.compiere.accounting.X_C_POSPayment(ctx, 0)
            instance = org.compiere.production.MProductionLine(ctx, 0)
            instance = org.compiere.accounting.MStorageReservation(ctx, 0)
            instance = org.compiere.order.MRMATax(ctx, 0)
            instance = org.compiere.accounting.MStorageOnHand(ctx, 0)
            instance = org.compiere.product.MAttributeSet(ctx, 0)
            instance = org.compiere.invoicing.MInOut(ctx, 0)
            instance = org.compiere.accounting.MCash(ctx, 0)
            instance = org.compiere.accounting.MCashLine(ctx, 0)
            instance = org.compiere.process.MProcessPara(ctx, 0)
            instance = org.compiere.accounting.X_C_Activity(ctx, 0)
            instance = org.compiere.production.X_R_Category(ctx, 0)
            instance = org.compiere.production.MStatus(ctx, 0)
            instance = org.compiere.orm.MTree_Base(ctx, 0)
            instance = org.compiere.orm.M_Element(ctx, 0)
            instance = org.compiere.accounting.MPayment(ctx, 0)
            instance = org.compiere.accounting.MRequisitionLine(ctx, 0)
            instance = org.compiere.production.MProjectLine(ctx, 0)
            instance = org.compiere.production.MAchievement(ctx, 0)
            instance = org.compiere.production.MRequestUpdate(ctx, 0)
            instance = org.compiere.product.X_M_SerNoCtlExclude(ctx, 0)
            instance = org.compiere.orm.MTable(ctx, 0)
            instance = org.compiere.orm.MColumn(ctx, 0)
            instance = org.compiere.invoicing.MInOutConfirm(ctx, 0)
            instance = org.compiere.invoicing.MInOutLineConfirm(ctx, 0)
            instance = org.compiere.production.MStatusCategory(ctx, 0)
            instance = org.compiere.crm.MRegion(ctx, 0)
            instance = org.compiere.invoicing.MInventoryLine(ctx, 0)
            instance = org.compiere.production.MRequestType(ctx, 0)
            instance = org.compiere.bank.MBank(ctx, 0)
            instance = org.compiere.bank.MBankAccount(ctx, 0)
            instance = org.compiere.invoicing.MPaymentTerm(ctx, 0)
            instance = org.compiere.accounting.MDistributionLine(ctx, 0)
            instance = org.compiere.conversionrate.MConversionType(ctx, 0)
            instance = org.compiere.accounting.MPeriod(ctx, 0)
            instance = org.compiere.product.MProductCategory(ctx, 0)
            instance = org.compiere.orm.MDocType(ctx, 0)
            instance = org.compiere.accounting.MDistribution(ctx, 0)
            instance = org.compiere.accounting.MOrderLine(ctx, 0)
            instance = org.compiere.accounting.MAcctSchema(ctx, 0)
            instance = org.compiere.order.MShipper(ctx, 0)
            instance = org.compiere.product.MResource(ctx, 0)
            instance = org.compiere.product.MResourceType(ctx, 0)
            instance = org.compiere.tax.MTax(ctx, 0)
            instance = org.compiere.crm.MBPartner(ctx, 0)
            instance = org.compiere.invoicing.MBPBankAccount(ctx, 0)
            instance = org.compiere.production.X_PP_Product_BOM(ctx, 0)
            instance = org.compiere.production.X_PP_Product_Planning(ctx, 0)
            instance = org.compiere.validation.X_AD_ModelValidator(ctx, 0)
            instance = org.compiere.invoicing.MRMA(ctx, 0)
            instance = org.compiere.invoicing.MInOutLine(ctx, 0)
            instance = org.compiere.production.MProductionPlan(ctx, 0)
            instance = org.compiere.crm.MBPGroup(ctx, 0)
            instance = org.compiere.invoicing.MInvoice(ctx, 0)
            instance = org.compiere.accounting.MPaySelection(ctx, 0)
            instance = org.compiere.product.X_M_AttributeSetExclude(ctx, 0)
            instance = org.compiere.orm.MRefList(ctx, 0)
            instance = org.compiere.accounting.MCharge(ctx, 0)
            instance = org.compiere.accounting.MClient(ctx, 0)
            instance = org.compiere.accounting.MCalendar(ctx, 0)
            instance = org.compiere.orm.MRole(ctx, 0)
            instance = org.compiere.accounting.MWarehouse(ctx, 0)
            instance = org.compiere.accounting.X_M_Locator(ctx, 0)
            instance = org.compiere.product.MPriceList(ctx, 0)
            instance = org.compiere.accounting.MAcctSchemaElement(ctx, 0)
            instance = org.compiere.tax.MTaxCategory(ctx, 0)
            instance = org.compiere.invoicing.MInvoiceLine(ctx, 0)
            instance = org.compiere.production.X_PP_Product_BOMLine(ctx, 0)
            instance = org.compiere.product.MAssetGroup(ctx, 0)
            instance = org.compiere.invoicing.MInvoicePaySchedule(ctx, 0)
            instance = org.compiere.invoicing.MAsset(ctx, 0)
            instance = org.compiere.order.MShipperPickupTypes(ctx, 0)
            instance = org.compiere.order.MShipperPackaging(ctx, 0)
            instance = org.compiere.invoicing.MAssetAcct(ctx, 0)
            instance = org.compiere.invoicing.MAssetGroupAcct(ctx, 0)
            instance = org.compiere.production.MProjectIssue(ctx, 0)
            instance = org.compiere.product.MLot(ctx, 0)
            instance = org.compiere.invoicing.MInventory(ctx, 0)
            instance = org.compiere.accounting.MCashBook(ctx, 0)
            instance = org.compiere.production.MMeasure(ctx, 0)
            instance = org.compiere.invoicing.MDocTypeCounter(ctx, 0)
            instance = org.compiere.accounting.MAllocationHdr(ctx, 0)
            instance = org.compiere.production.MGroup(ctx, 0)
            instance = org.compiere.production.MResolution(ctx, 0)
            instance = org.compiere.accounting.MBankStatementLine(ctx, 0)
            instance = org.compiere.product.MAttributeSetInstance(ctx, 0)
            instance = org.compiere.product.MProductBOM(ctx, 0)
            instance = org.compiere.accounting.MPaySelectionLine(ctx, 0)
            instance = org.compiere.product.X_M_LotCtlExclude(ctx, 0)
            instance = org.compiere.orm.MEntityType(ctx, 0)
            instance = org.compiere.orm.X_AD_Reference(ctx, 0)
            instance = org.compiere.crm.MCountry(ctx, 0)
            instance = org.compiere.accounting.MCostElement(ctx, 0)
            instance = org.compiere.orm.MOrg(ctx, 0)
            instance = org.compiere.bo.MCurrency(ctx, 0)
            instance = org.compiere.product.MDiscountSchema(ctx, 0)
            instance = org.compiere.production.MProjectType(ctx, 0)
            instance = org.compiere.production.MProjectPhase(ctx, 0)
            instance = org.compiere.crm.MLanguage(ctx, 0)
            instance = org.compiere.accounting.MElementValue(ctx, 0)
            instance = org.compiere.accounting.MMatchInv(ctx, 0)
            instance = org.compiere.accounting.MMatchPO(ctx, 0)
            instance = org.compiere.accounting.MElement(ctx, 0)
            instance = org.compiere.product.MUOM(ctx, 0)
            instance = org.compiere.crm.MLocation(ctx, 0)
            instance = org.compiere.accounting.MUOMConversion(ctx, 0)
            instance = org.compiere.accounting.MYear(ctx, 0)
            instance = org.compiere.order.MRMALine(ctx, 0)
            instance = org.compiere.production.MProject(ctx, 0)
            instance = org.compiere.invoicing.MInvoiceSchedule(ctx, 0)
            instance = org.compiere.accounting.MOrder(ctx, 0)
            instance = org.compiere.accounting.X_C_Campaign(ctx, 0)
            instance = org.compiere.process.MProcess(ctx, 0)
            instance = org.compiere.orm.MSysConfig(ctx, 0)
            instance = org.compiere.accounting.MProduct(ctx, 0)
            instance = org.compiere.tax.MTaxPostal(ctx, 0)
            instance = org.compiere.crm.MBPartnerLocation(ctx, 0)
            instance = org.compiere.orm.MViewColumn(ctx, 0)
            instance = org.compiere.production.MMeasureCalc(ctx, 0)
            instance = org.compiere.order.MOrderPaySchedule(ctx, 0)
            instance = org.compiere.accounting.MBankStatement(ctx, 0)
            instance = org.compiere.production.MColorSchema(ctx, 0)
            instance = org.compiere.accounting.MHierarchy(ctx, 0)
            instance = org.compiere.product.MPriceListVersion(ctx, 0)
            instance = org.compiere.validation.MTableScriptValidator(ctx, 0)
            instance = org.compiere.invoicing.MDepreciation(ctx, 0)
            instance = org.compiere.crm.MUser(ctx, 0)
            instance = org.compiere.accounting.MJournalLine(ctx, 0)
            instance = org.compiere.invoicing.MDepreciationWorkfile(ctx, 0)
            instance = org.compiere.invoicing.MDepreciationExp(ctx, 0)
            instance = org.compiere.invoicing.MAssetType(ctx, 0)
            instance = org.compiere.invoicing.MPaymentTransaction(ctx, 0)
            instance = org.compiere.production.MQualityTest(ctx, 0)
            instance = org.compiere.order.MShipperLabels(ctx, 0)
            instance = org.compiere.production.MProduction(ctx, 0)
            instance = org.compiere.orm.MIndexColumn(ctx, 0)
            instance = org.compiere.orm.MViewComponent(ctx, 0)
            instance = org.compiere.accounting.MOrderLandedCost(ctx, 0)
            instance = org.compiere.accounting.MOrderLandedCostAllocation(ctx, 0)
            instance = org.compiere.production.MTransaction(ctx, 0)
            instance = org.compiere.invoicing.MLocatorType(ctx, 0)
            instance = org.compiere.bo.MOpportunity(ctx, 0)
            instance = org.compiere.bo.X_C_SalesStage(ctx, 0)
            instance = org.compiere.crm.MCountryGroup(ctx, 0)
            instance = org.compiere.crm.MCrmCategory(ctx, 0)
            instance = org.compiere.crm.MCrmCustomerCategory(ctx, 0)
        }
    }

    @Test
    fun `instantiate statically from row`() {
        var instance : IPO
        val ctx = Env.getCtx()
        DB.run {
            val query = queryOf("select 1 where 0=1", listOf()).map { row ->
                instance = org.compiere.product.MAttributeInstance(ctx, row)
                instance = org.compiere.invoicing.MInvoiceTax(ctx, row)
                instance = org.compiere.accounting.MCost(ctx, row)
                instance = org.compiere.order.MOrderTax(ctx, row)
                instance = org.compiere.accounting.MAcctSchemaDefault(ctx, row)
                instance = org.compiere.orm.MUserRoles(ctx, row)
                instance = org.compiere.accounting.MAcctSchemaGL(ctx, row)
                instance = org.compiere.accounting.MFactAcct(ctx, row)
                instance = org.compiere.invoicing.MInOutLineMA(ctx, row)
                instance = org.compiere.production.MProductionLineMA(ctx, row)
                instance = org.compiere.product.MProductPrice(ctx, row)
                instance = org.compiere.process.MPInstancePara(ctx, row)
                instance = org.compiere.accounting.MProductPO(ctx, row)
                instance = org.compiere.crm.MContactInterest(ctx, row)
                instance = org.compiere.orm.X_AD_Role_Included(ctx, row)
                instance = org.compiere.invoicing.MInventoryLineMA(ctx, row)
                instance = org.compiere.accounting.MProductCategoryAcct(ctx, row)
                instance = org.compiere.orm.X_AD_PInstance_Log(ctx, row)
                instance = org.compiere.accounting.MCostDetail(ctx, row)
                instance = org.compiere.accounting.X_C_POSPayment(ctx, row)
                instance = org.compiere.production.MProductionLine(ctx, row)
                instance = org.compiere.accounting.MStorageReservation(ctx, row)
                instance = org.compiere.order.MRMATax(ctx, row)
                instance = org.compiere.accounting.MStorageOnHand(ctx, row)
                instance = org.compiere.product.MAttributeSet(ctx, row)
                instance = org.compiere.invoicing.MInOut(ctx, row)
                instance = org.compiere.accounting.MCash(ctx, row)
                instance = org.compiere.accounting.MCashLine(ctx, row)
                instance = org.compiere.process.MProcessPara(ctx, row)
                instance = org.compiere.accounting.X_C_Activity(ctx, row)
                instance = org.compiere.production.X_R_Category(ctx, row)
                instance = org.compiere.production.MStatus(ctx, row)
                instance = org.compiere.orm.MTree_Base(ctx, row)
                instance = org.compiere.orm.M_Element(ctx, row)
                instance = org.compiere.accounting.MPayment(ctx, row)
                instance = org.compiere.accounting.MRequisitionLine(ctx, row)
                instance = org.compiere.production.MProjectLine(ctx, row)
                instance = org.compiere.production.MAchievement(ctx, row)
                instance = org.compiere.production.MRequestUpdate(ctx, row)
                instance = org.compiere.product.X_M_SerNoCtlExclude(ctx, row)
                instance = org.compiere.orm.MTable(ctx, row)
                instance = org.compiere.orm.MColumn(ctx, row)
                instance = org.compiere.invoicing.MInOutConfirm(ctx, row)
                instance = org.compiere.invoicing.MInOutLineConfirm(ctx, row)
                instance = org.compiere.production.MStatusCategory(ctx, row)
                instance = org.compiere.crm.MRegion(ctx, row)
                instance = org.compiere.invoicing.MInventoryLine(ctx, row)
                instance = org.compiere.production.MRequestType(ctx, row)
                instance = org.compiere.bank.MBank(ctx, row)
                instance = org.compiere.bank.MBankAccount(ctx, row)
                instance = org.compiere.invoicing.MPaymentTerm(ctx, row)
                instance = org.compiere.accounting.MDistributionLine(ctx, row)
                instance = org.compiere.conversionrate.MConversionType(ctx, row)
                instance = org.compiere.accounting.MPeriod(ctx, row)
                instance = org.compiere.product.MProductCategory(ctx, row)
                instance = org.compiere.orm.MDocType(ctx, row)
                instance = org.compiere.accounting.MDistribution(ctx, row)
                instance = org.compiere.accounting.MOrderLine(ctx, row)
                instance = org.compiere.accounting.MAcctSchema(ctx, row)
                instance = org.compiere.order.MShipper(ctx, row)
                instance = org.compiere.product.MResource(ctx, row)
                instance = org.compiere.product.MResourceType(ctx, row)
                instance = org.compiere.tax.MTax(ctx, row)
                instance = org.compiere.crm.MBPartner(ctx, row)
                instance = org.compiere.invoicing.MBPBankAccount(ctx, row)
                instance = org.compiere.production.X_PP_Product_BOM(ctx, row)
                instance = org.compiere.production.X_PP_Product_Planning(ctx, row)
                instance = org.compiere.validation.X_AD_ModelValidator(ctx, row)
                instance = org.compiere.invoicing.MRMA(ctx, row)
                instance = org.compiere.invoicing.MInOutLine(ctx, row)
                instance = org.compiere.production.MProductionPlan(ctx, row)
                instance = org.compiere.crm.MBPGroup(ctx, row)
                instance = org.compiere.invoicing.MInvoice(ctx, row)
                instance = org.compiere.accounting.MPaySelection(ctx, row)
                instance = org.compiere.product.X_M_AttributeSetExclude(ctx, row)
                instance = org.compiere.orm.MRefList(ctx, row)
                instance = org.compiere.accounting.MCharge(ctx, row)
                instance = org.compiere.accounting.MClient(ctx, row)
                instance = org.compiere.accounting.MCalendar(ctx, row)
                instance = org.compiere.orm.MRole(ctx, row)
                instance = org.compiere.accounting.MWarehouse(ctx, row)
                instance = org.compiere.accounting.X_M_Locator(ctx, row)
                instance = org.compiere.product.MPriceList(ctx, row)
                instance = org.compiere.accounting.MAcctSchemaElement(ctx, row)
                instance = org.compiere.tax.MTaxCategory(ctx, row)
                instance = org.compiere.invoicing.MInvoiceLine(ctx, row)
                instance = org.compiere.production.X_PP_Product_BOMLine(ctx, row)
                instance = org.compiere.product.MAssetGroup(ctx, row)
                instance = org.compiere.invoicing.MInvoicePaySchedule(ctx, row)
                instance = org.compiere.invoicing.MAsset(ctx, row)
                instance = org.compiere.order.MShipperPickupTypes(ctx, row)
                instance = org.compiere.order.MShipperPackaging(ctx, row)
                instance = org.compiere.invoicing.MAssetAcct(ctx, row)
                instance = org.compiere.invoicing.MAssetGroupAcct(ctx, row)
                instance = org.compiere.production.MProjectIssue(ctx, row)
                instance = org.compiere.product.MLot(ctx, row)
                instance = org.compiere.invoicing.MInventory(ctx, row)
                instance = org.compiere.accounting.MCashBook(ctx, row)
                instance = org.compiere.production.MMeasure(ctx, row)
                instance = org.compiere.invoicing.MDocTypeCounter(ctx, row)
                instance = org.compiere.accounting.MAllocationHdr(ctx, row)
                instance = org.compiere.production.MGroup(ctx, row)
                instance = org.compiere.production.MResolution(ctx, row)
                instance = org.compiere.accounting.MBankStatementLine(ctx, row)
                instance = org.compiere.product.MAttributeSetInstance(ctx, row)
                instance = org.compiere.product.MProductBOM(ctx, row)
                instance = org.compiere.accounting.MPaySelectionLine(ctx, row)
                instance = org.compiere.product.X_M_LotCtlExclude(ctx, row)
                instance = org.compiere.orm.MEntityType(ctx, row)
                instance = org.compiere.orm.X_AD_Reference(ctx, row)
                instance = org.compiere.crm.MCountry(ctx, row)
                instance = org.compiere.accounting.MCostElement(ctx, row)
                instance = org.compiere.orm.MOrg(ctx, row)
                instance = org.compiere.bo.MCurrency(ctx, row)
                instance = org.compiere.product.MDiscountSchema(ctx, row)
                instance = org.compiere.production.MProjectType(ctx, row)
                instance = org.compiere.production.MProjectPhase(ctx, row)
                instance = org.compiere.crm.MLanguage(ctx, row)
                instance = org.compiere.accounting.MElementValue(ctx, row)
                instance = org.compiere.accounting.MMatchInv(ctx, row)
                instance = org.compiere.accounting.MMatchPO(ctx, row)
                instance = org.compiere.accounting.MElement(ctx, row)
                instance = org.compiere.product.MUOM(ctx, row)
                instance = org.compiere.crm.MLocation(ctx, row)
                instance = org.compiere.accounting.MUOMConversion(ctx, row)
                instance = org.compiere.accounting.MYear(ctx, row)
                instance = org.compiere.order.MRMALine(ctx, row)
                instance = org.compiere.production.MProject(ctx, row)
                instance = org.compiere.invoicing.MInvoiceSchedule(ctx, row)
                instance = org.compiere.accounting.MOrder(ctx, row)
                instance = org.compiere.accounting.X_C_Campaign(ctx, row)
                instance = org.compiere.process.MProcess(ctx, row)
                instance = org.compiere.orm.MSysConfig(ctx, row)
                instance = org.compiere.accounting.MProduct(ctx, row)
                instance = org.compiere.tax.MTaxPostal(ctx, row)
                instance = org.compiere.crm.MBPartnerLocation(ctx, row)
                instance = org.compiere.orm.MViewColumn(ctx, row)
                instance = org.compiere.production.MMeasureCalc(ctx, row)
                instance = org.compiere.order.MOrderPaySchedule(ctx, row)
                instance = org.compiere.accounting.MBankStatement(ctx, row)
                instance = org.compiere.production.MColorSchema(ctx, row)
                instance = org.compiere.accounting.MHierarchy(ctx, row)
                instance = org.compiere.product.MPriceListVersion(ctx, row)
                instance = org.compiere.validation.MTableScriptValidator(ctx, row)
                instance = org.compiere.invoicing.MDepreciation(ctx, row)
                instance = org.compiere.crm.MUser(ctx, row)
                instance = org.compiere.accounting.MJournalLine(ctx, row)
                instance = org.compiere.invoicing.MDepreciationWorkfile(ctx, row)
                instance = org.compiere.invoicing.MDepreciationExp(ctx, row)
                instance = org.compiere.invoicing.MAssetType(ctx, row)
                instance = org.compiere.invoicing.MPaymentTransaction(ctx, row)
                instance = org.compiere.production.MQualityTest(ctx, row)
                instance = org.compiere.order.MShipperLabels(ctx, row)
                instance = org.compiere.production.MProduction(ctx, row)
                instance = org.compiere.orm.MIndexColumn(ctx, row)
                instance = org.compiere.orm.MViewComponent(ctx, row)
                instance = org.compiere.accounting.MOrderLandedCost(ctx, row)
                instance = org.compiere.accounting.MOrderLandedCostAllocation(ctx, row)
                instance = org.compiere.production.MTransaction(ctx, row)
                instance = org.compiere.invoicing.MLocatorType(ctx, row)
                instance = org.compiere.bo.MOpportunity(ctx, row)
                instance = org.compiere.bo.X_C_SalesStage(ctx, row)
                instance = org.compiere.crm.MCountryGroup(ctx, row)
                instance = org.compiere.crm.MCrmCategory(ctx, row)
                instance = org.compiere.crm.MCrmCustomerCategory(ctx, row)
                instance
            }.asSingle
        }
    }
}