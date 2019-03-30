--
-- Name: crm_category; Type: TABLE; Schema: adempiere; Owner: adempiere
--

CREATE TABLE adempiere.crm_category (
                                      crm_category_id numeric NOT NULL,
                                      crm_category_uu character varying(36) DEFAULT NULL::character varying,
                                      ad_client_id numeric NOT NULL,
                                      ad_org_id numeric NOT NULL,
                                      created timestamp without time zone DEFAULT statement_timestamp() NOT NULL,
                                      createdby numeric NOT NULL,
                                      isactive character(1) NOT NULL,
                                      name character varying(60) NOT NULL,
                                      updated timestamp without time zone DEFAULT statement_timestamp() NOT NULL,
                                      updatedby numeric NOT NULL,
                                      value character varying(60) NOT NULL
);


ALTER TABLE adempiere.crm_category OWNER TO adempiere;

--
-- Name: crm_customer_category; Type: TABLE; Schema: adempiere; Owner: adempiere
--

CREATE TABLE adempiere.crm_customer_category (
                                               crm_customer_category_id numeric NOT NULL,
                                               ad_client_id numeric NOT NULL,
                                               ad_org_id numeric NOT NULL,
                                               crm_customer_category_uu character varying(36) DEFAULT NULL::character varying,
                                               created timestamp without time zone DEFAULT statement_timestamp() NOT NULL,
                                               createdby numeric NOT NULL,
                                               isactive character(1) NOT NULL,
                                               name character varying(60) NOT NULL,
                                               updated timestamp without time zone DEFAULT statement_timestamp() NOT NULL,
                                               updatedby numeric NOT NULL,
                                               c_bpartner_id numeric NOT NULL,
                                               crm_category_id numeric NOT NULL
);


ALTER TABLE adempiere.crm_customer_category OWNER TO adempiere;

--
-- Data for Name: ad_table; Type: TABLE DATA; Schema: adempiere; Owner: adempiere
--

COPY adempiere.ad_table (ad_table_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, name, description, help, tablename, isview, accesslevel, entitytype, ad_window_id, ad_val_rule_id, loadseq, issecurityenabled, isdeleteable, ishighvolume, importtable, ischangelog, replicationtype, po_window_id, copycolumnsfromtable, iscentrallymaintained, ad_table_uu, processing, databaseviewdrop, copycomponentsfromview) FROM stdin;
1000000	0	0	Y	2018-10-27 14:27:13.483	0	2018-10-27 14:27:13.483	0	CRM Category	\N	\N	Crm_Category	N	4	U	\N	\N	0	N	Y	N	N	Y	L	\N	N	Y	cbeae299-b22c-448c-ba23-0a58c6139092	N	N	N
1000001	0	0	Y	2018-10-27 14:28:34.836	0	2018-10-27 14:28:34.836	0	CRM Customer in Category	\N	\N	Crm_Customer_Category	N	4	U	\N	\N	0	N	Y	N	N	Y	L	\N	N	Y	b68ae549-7a9e-4d9d-89d0-89199f5be40d	N	N	N
\.

COPY adempiere.ad_element (ad_element_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, columnname, entitytype, name, printname, description, help, po_name, po_printname, po_description, po_help, ad_element_uu) FROM stdin;
1000000	0	0	Y	2018-10-27 14:27:28.081	0	2018-10-27 14:27:28.081	0	Crm_Category_ID	C	CRM Category	CRM Category	\N	\N	\N	\N	\N	\N	7a33a555-3463-4ab6-8c01-1df5848931ca
1000001	0	0	Y	2018-10-27 14:27:28.124	0	2018-10-27 14:27:28.124	0	Crm_Category_UU	C	Crm_Category_UU	Crm_Category_UU	\N	\N	\N	\N	\N	\N	83d02f47-cc42-484c-bb94-1704bcbd2bb2
1000002	0	0	Y	2018-10-27 14:28:39.27	0	2018-10-27 14:28:39.27	0	Crm_Customer_Category_ID	C	CRM Customer in Category	CRM Customer in Category	\N	\N	\N	\N	\N	\N	5afb2d5b-056a-4c83-bbce-1ed928d78b77
1000003	0	0	Y	2018-10-27 14:28:39.346	0	2018-10-27 14:28:39.346	0	Crm_Customer_Category_UU	C	Crm_Customer_Category_UU	Crm_Customer_Category_UU	\N	\N	\N	\N	\N	\N	fc5dde09-7617-4919-a4e9-f14f777a99f6
\.


COPY adempiere.ad_element_trl (ad_element_id, ad_language, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, name, printname, description, help, po_name, po_printname, po_description, po_help, istranslated, ad_element_trl_uu) FROM stdin;
1000000	zh_CN	0	0	Y	2018-10-27 14:27:28.081	0	2018-10-27 14:27:28.081	0	CRM Category	CRM Category	\N	\N	\N	\N	\N	\N	N	240151d5-d584-4043-b9c8-76032bb63884
1000001	zh_CN	0	0	Y	2018-10-27 14:27:28.124	0	2018-10-27 14:27:28.124	0	Crm_Category_UU	Crm_Category_UU	\N	\N	\N	\N	\N	\N	N	1cce1684-da0f-4876-ab89-88e3f2127ab9
1000002	zh_CN	0	0	Y	2018-10-27 14:28:39.27	0	2018-10-27 14:28:39.27	0	CRM Customer in Category	CRM Customer in Category	\N	\N	\N	\N	\N	\N	N	e1732a74-0ae2-4dc9-9b33-02f715437563
1000003	zh_CN	0	0	Y	2018-10-27 14:28:39.346	0	2018-10-27 14:28:39.346	0	Crm_Customer_Category_UU	Crm_Customer_Category_UU	\N	\N	\N	\N	\N	\N	N	71cc8338-cb02-491f-ba05-4f016c5d7449
\.


COPY adempiere.ad_column (ad_column_id, ad_client_id, ad_org_id, isactive, created, updated, createdby, updatedby, name, description, help, version, entitytype, columnname, ad_table_id, ad_reference_id, ad_reference_value_id, ad_val_rule_id, fieldlength, defaultvalue, iskey, isparent, ismandatory, isupdateable, readonlylogic, isidentifier, seqno, istranslated, isencrypted, callout, vformat, valuemin, valuemax, isselectioncolumn, ad_element_id, ad_process_id, issyncdatabase, isalwaysupdateable, columnsql, mandatorylogic, infofactoryclass, isautocomplete, isallowlogging, formatpattern, ad_column_uu, isallowcopy, seqnoselection, istoolbarbutton, issecure, ad_chart_id, fkconstraintname, fkconstrainttype, pa_dashboardcontent_id) FROM stdin;
1000000	0	0	Y	2018-10-27 14:27:28.077	2018-10-27 14:27:28.077	0	0	CRM Category	\N	\N	0.0	C	Crm_Category_ID	1000000	13	\N	\N	131089	\N	Y	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	1000000	\N	N	N	\N	\N	\N	N	Y	\N	0d826df9-8bca-42fa-814d-ccd8a82e3946	Y	\N	N	N	\N	\N	N	\N
1000001	0	0	Y	2018-10-27 14:27:28.124	2018-10-27 14:27:28.124	0	0	Crm_Category_UU	\N	\N	0.0	C	Crm_Category_UU	1000000	10	\N	\N	36	\N	N	N	N	N	\N	N	\N	N	N	\N	\N	\N	\N	N	1000001	\N	N	N	\N	\N	\N	N	Y	\N	f854dfae-c21e-406f-912b-9e0c977dc824	Y	\N	N	N	\N	\N	N	\N
1000002	0	0	Y	2018-10-27 14:27:28.154	2018-10-27 14:27:28.154	0	0	Client	Client/Tenant for this installation.	A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.	0.0	C	AD_Client_ID	1000000	19	\N	129	131089	@#AD_Client_ID@	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	102	\N	N	N	\N	\N	\N	N	Y	\N	06754e27-165a-430c-9b97-c416ad33e8ab	Y	\N	N	N	\N	\N	D	\N
1000003	0	0	Y	2018-10-27 14:27:28.176	2018-10-27 14:27:28.176	0	0	Organization	Organizational entity within client	An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.	0.0	C	AD_Org_ID	1000000	19	\N	104	131089	@#AD_Org_ID@	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	113	\N	N	N	\N	\N	\N	N	Y	\N	dce9a2f3-ed32-4401-833a-0c846d30019e	Y	\N	N	N	\N	\N	D	\N
1000004	0	0	Y	2018-10-27 14:27:28.198	2018-10-27 14:27:28.198	0	0	Created	Date this record was created	The Created field indicates the date that this record was created.	0.0	C	Created	1000000	16	\N	\N	29	SYSDATE	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	245	\N	N	N	\N	\N	\N	N	Y	\N	18a030c6-e49d-4b49-9727-d2ffaa9e8ad6	Y	\N	N	N	\N	\N	N	\N
1000005	0	0	Y	2018-10-27 14:27:28.219	2018-10-27 14:27:28.219	0	0	Created By	User who created this records	The Created By field indicates the user who created this record.	0.0	C	CreatedBy	1000000	18	110	\N	131089	\N	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	246	\N	N	N	\N	\N	\N	N	Y	\N	1a4ba004-b6c7-4027-8e0d-facbc68a73ab	Y	\N	N	N	\N	\N	D	\N
1000006	0	0	Y	2018-10-27 14:27:28.252	2018-10-27 14:27:28.252	0	0	Active	The record is active in the system	There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.\nThere are two reasons for de-activating and not deleting records:\n(1) The system requires the record for audit purposes.\n(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.	0.0	C	IsActive	1000000	20	\N	\N	1	Y	N	N	Y	Y	\N	N	\N	N	N	\N	\N	\N	\N	N	348	\N	N	N	\N	\N	\N	N	Y	\N	eb2f4746-84c3-444a-b163-0933f0eadae0	Y	\N	N	N	\N	\N	N	\N
1000007	0	0	Y	2018-10-27 14:27:28.298	2018-10-27 14:27:28.298	0	0	Name	Alphanumeric identifier of the entity	The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.	0.0	C	Name	1000000	10	\N	\N	60	\N	N	N	Y	Y	\N	Y	\N	N	N	\N	\N	\N	\N	Y	469	\N	N	N	\N	\N	\N	N	Y	\N	3dc4dec8-f075-4ee2-b8cc-525c413cb771	Y	\N	N	N	\N	\N	N	\N
1000008	0	0	Y	2018-10-27 14:27:28.353	2018-10-27 14:27:28.353	0	0	Updated	Date this record was updated	The Updated field indicates the date that this record was updated.	0.0	C	Updated	1000000	16	\N	\N	29	SYSDATE	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	607	\N	N	N	\N	\N	\N	N	Y	\N	12732ea5-68cc-49b4-8920-44ed3461dfe8	Y	\N	N	N	\N	\N	N	\N
1000009	0	0	Y	2018-10-27 14:27:28.386	2018-10-27 14:27:28.386	0	0	Updated By	User who updated this records	The Updated By field indicates the user who updated this record.	0.0	C	UpdatedBy	1000000	18	110	\N	131089	\N	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	608	\N	N	N	\N	\N	\N	N	Y	\N	2d8e1cc6-fb76-4358-96d4-12562120bd25	Y	\N	N	N	\N	\N	D	\N
1000010	0	0	Y	2018-10-27 14:27:28.419	2018-10-27 14:27:28.419	0	0	Search Key	Search key for the record in the format required - must be unique	A search key allows you a fast method of finding a particular record.\nIf you leave the search key empty, the system automatically creates a numeric number.  The document sequence used for this fallback number is defined in the "Maintain Sequence" window with the name "DocumentNo_<TableName>", where TableName is the actual name of the table (e.g. C_Order).	0.0	C	Value	1000000	10	\N	\N	60	\N	N	N	Y	Y	\N	N	\N	N	N	\N	\N	\N	\N	Y	620	\N	N	N	\N	\N	\N	N	Y	\N	c3eedd5b-0fee-4669-b4f6-9e41165bd0ad	Y	\N	N	N	\N	\N	N	\N
1000011	0	0	Y	2018-10-27 14:28:39.269	2018-10-27 14:28:39.269	0	0	CRM Customer in Category	\N	\N	0.0	C	Crm_Customer_Category_ID	1000001	13	\N	\N	131089	\N	Y	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	1000002	\N	N	N	\N	\N	\N	N	Y	\N	fe6f6c30-b400-4486-841e-cc5e20ff48a2	Y	\N	N	N	\N	\N	N	\N
1000012	0	0	Y	2018-10-27 14:28:39.295	2018-10-27 14:28:39.295	0	0	Client	Client/Tenant for this installation.	A Client is a company or a legal entity. You cannot share data between Clients. Tenant is a synonym for Client.	0.0	C	AD_Client_ID	1000001	19	\N	129	131089	@#AD_Client_ID@	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	102	\N	N	N	\N	\N	\N	N	Y	\N	79a91f90-9827-4aca-a13f-c6df49819c76	Y	\N	N	N	\N	\N	D	\N
1000013	0	0	Y	2018-10-27 14:28:39.322	2018-10-27 14:28:39.322	0	0	Organization	Organizational entity within client	An organization is a unit of your client or legal entity - examples are store, department. You can share data between organizations.	0.0	C	AD_Org_ID	1000001	19	\N	104	131089	@#AD_Org_ID@	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	113	\N	N	N	\N	\N	\N	N	Y	\N	4593a5c4-bb44-4a1f-ab0b-975cb10330d5	Y	\N	N	N	\N	\N	D	\N
1000014	0	0	Y	2018-10-27 14:28:39.345	2018-10-27 14:28:39.345	0	0	Crm_Customer_Category_UU	\N	\N	0.0	C	Crm_Customer_Category_UU	1000001	10	\N	\N	36	\N	N	N	N	N	\N	N	\N	N	N	\N	\N	\N	\N	N	1000003	\N	N	N	\N	\N	\N	N	Y	\N	2be74536-67c6-45c6-8c4e-f130de91b2f4	Y	\N	N	N	\N	\N	N	\N
1000016	0	0	Y	2018-10-27 14:28:39.39	2018-10-27 14:28:39.39	0	0	Created By	User who created this records	The Created By field indicates the user who created this record.	0.0	C	CreatedBy	1000001	18	110	\N	131089	\N	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	246	\N	N	N	\N	\N	\N	N	Y	\N	dea8c52c-ee4f-44c1-87c2-fc2d09c6c165	Y	\N	N	N	\N	\N	D	\N
1000017	0	0	Y	2018-10-27 14:28:39.416	2018-10-27 14:28:39.416	0	0	Active	The record is active in the system	There are two methods of making records unavailable in the system: One is to delete the record, the other is to de-activate the record. A de-activated record is not available for selection, but available for reports.\nThere are two reasons for de-activating and not deleting records:\n(1) The system requires the record for audit purposes.\n(2) The record is referenced by other records. E.g., you cannot delete a Business Partner, if there are invoices for this partner record existing. You de-activate the Business Partner and prevent that this record is used for future entries.	0.0	C	IsActive	1000001	20	\N	\N	1	Y	N	N	Y	Y	\N	N	\N	N	N	\N	\N	\N	\N	N	348	\N	N	N	\N	\N	\N	N	Y	\N	89c90270-8b8a-47d2-8fe4-8c9f5552d268	Y	\N	N	N	\N	\N	N	\N
1000018	0	0	Y	2018-10-27 14:28:39.442	2018-10-27 14:28:39.442	0	0	Name	Alphanumeric identifier of the entity	The name of an entity (record) is used as an default search option in addition to the search key. The name is up to 60 characters in length.	0.0	C	Name	1000001	10	\N	\N	60	\N	N	N	Y	Y	\N	Y	\N	N	N	\N	\N	\N	\N	Y	469	\N	N	N	\N	\N	\N	N	Y	\N	dfa4d58e-5029-4137-b556-12731a993d22	Y	\N	N	N	\N	\N	N	\N
1000019	0	0	Y	2018-10-27 14:28:39.463	2018-10-27 14:28:39.463	0	0	Updated	Date this record was updated	The Updated field indicates the date that this record was updated.	0.0	C	Updated	1000001	16	\N	\N	29	SYSDATE	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	607	\N	N	N	\N	\N	\N	N	Y	\N	ead2971f-eb7b-4259-900e-264e2dbd2ca4	Y	\N	N	N	\N	\N	N	\N
1000020	0	0	Y	2018-10-27 14:28:39.482	2018-10-27 14:28:39.482	0	0	Updated By	User who updated this records	The Updated By field indicates the user who updated this record.	0.0	C	UpdatedBy	1000001	18	110	\N	131089	\N	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	608	\N	N	N	\N	\N	\N	N	Y	\N	ca17763f-9da9-4344-a630-5390609cf15b	Y	\N	N	N	\N	\N	D	\N
1000021	0	0	Y	2018-10-27 14:28:39.502	2018-10-27 14:28:39.502	0	0	Business Partner 	Identifies a Business Partner	A Business Partner is anyone with whom you transact.  This can include Vendor, Customer, Employee or Salesperson	0.0	C	C_BPartner_ID	1000001	30	\N	\N	131089	\N	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	187	\N	N	N	\N	\N	\N	N	Y	\N	ad897cff-70b2-41f6-9592-453d40c2f49e	Y	\N	N	N	\N	\N	N	\N
1000022	0	0	Y	2018-10-27 14:28:39.523	2018-10-27 14:28:39.523	0	0	CRM Category	\N	\N	0.0	C	Crm_Category_ID	1000001	19	\N	\N	131089	\N	N	N	Y	N	\N	N	\N	N	N	\N	\N	\N	\N	N	1000000	\N	N	N	\N	\N	\N	N	Y	\N	141db155-2130-4302-97f5-b124d12aeb45	Y	\N	N	N	\N	\N	N	\N
\.

COPY adempiere.ad_sequence (ad_sequence_id, ad_client_id, ad_org_id, isactive, created, createdby, updated, updatedby, name, description, vformat, isautosequence, incrementno, startno, currentnext, currentnextsys, isaudited, istableid, prefix, suffix, startnewyear, datecolumn, decimalpattern, ad_sequence_uu, startnewmonth, isorglevelsequence, orgcolumn) FROM stdin;
200301	0	0	Y	2018-10-27 14:27:13.535	0	2018-10-27 14:27:13.535	0	Crm_Category	Table Crm_Category	\N	Y	1	1000000	1000000	200000	N	Y	\N	\N	N	\N	\N	6a5e43bb-bbe0-4778-b016-110e6e022fba	N	N	\N
200302	0	0	Y	2018-10-27 14:28:34.879	0	2018-10-27 14:28:34.879	0	Crm_Customer_Category	Table Crm_Customer_Category	\N	Y	1	1000000	1000000	200000	N	Y	\N	\N	N	\N	\N	07461929-e9e1-4359-972f-f385f22f9b5f	N	N	\N
\.
