create or replace view v_fin_info as select f.id, f.id document_id, (select coalesce(sum(base_detail_amount),0) from tra_detail dt where dt.document_fin_id = f.id and dt.tr_state_detail=+1) debit_amount, (select coalesce(sum(base_detail_amount),0) from tra_detail dt where dt.document_fin_id = f.id and dt.tr_state_detail=-1) credit_amount, (select coalesce(sum(base_detail_amount),0) from tra_detail dt where dt.document_fin_id = f.id and dt.resource='ACC') bs_amount, (select coalesce(sum(base_detail_amount),0) from tra_detail pr where pr.bs_document_id = f.id) pr_amount from fin_document f;

create or replace view v_stk as select doc.fiscal_period1_id, doc.fiscal_period2_id, doc.doc_date, dtl.item_id, itm.code item_code, itm.name item_name, dtl.department_id, dpr.code department_code, dpr.name department_name, dtl.base_detail_count, dtl.tr_state_detail from tra_detail dtl, stk_document doc, def_item itm, org_department dpr where doc.id = dtl.document_stk_id and itm.id = dtl.item_id and dpr.id = dtl.department_id

create or replace view v_bud as select doc.fiscal_year_id, det.fiscal_period_id, det.budget_rx, det.budget_type, det.budget_amount* (case when det.budget_rx = 'BUD_R' then 1 ELSE (-1) end) budget_amount from bud_detail det, bud_document doc where det.document_id = doc.id;

create index ix_bud_detail on bud_detail (document_id);

create unique index ix_bud_document on bud_document (fiscal_year_id);

create unique index ix_cat_menu_info on cat_menu_info (fiscal_year_id, meal_id);

create unique index ix_cat_menu on cat_menu (fiscal_year_id, menu_date, menu_info_id);

create unique index ix_cat_menu_item on cat_menu_item (menu_id, item_id);

create unique index ix_def_item on def_item (organization_id, type_id, code);

create unique index ix_def_item_product on def_item_product (item_id, material_item_id);

create unique index ix_def_item_unit on def_item_unit (item_id, unit_code_id);

create unique index ix_def_item_value on def_item_value (item_id, value_type_enum, value_id);

create unique index ix_def_param on def_param (type_id, code);

create unique index ix_def_param_answer on def_param_answer (param_id, organization_id);

create unique index ix_def_reference on def_reference (organization_id, type_id, ref_type_id, ref_value_id);

create unique index ix_def_task on def_task (organization_id, type_id, code);

create unique index ix_def_unit_code on def_unit_code (unit_group_id, code);

create unique index ix_def_unit_group on def_unit_group (organization_id, code);

create unique index ix_def_value on def_value (organization_id, type_id, code);

create unique index ix_def_value_level_1 on def_value_level (value_id, level_asc);
create unique index ix_def_value_level_2 on def_value_level (value_id, level_desc);

create index ix_fin_document_1 on fin_document (organization_id, fiscal_period1_id, doc_date);
create index ix_fin_document_2 on fin_document (organization_id, fiscal_period2_id, doc_date);

create unique index ix_org_department on org_department (organization_id, code);

create unique index ix_org_fiscal_period on org_fiscal_period (fiscal_year_id, period_no);

create unique index ix_org_fiscal_year on org_fiscal_year (organization_id, date_start);

create index ix_rep_pivot on rep_pivot (organization_id);

create unique index ix_req_detail_offer on req_detail_offer (detail_id, vendor_item_id);

create index ix_req_document_1 on req_document (organization_id, fiscal_period1_id, doc_date);
create index ix_req_document_2 on req_document (organization_id, fiscal_period2_id, doc_date);

create unique index ix_sec_group on sec_group (name);

create unique index ix_sec_group_authority on sec_group_authority (group_id, authority_id);

create unique index ix_sec_user_department_1 on sec_user_department (user_id, department_id);
create unique index ix_sec_user_department_2 on sec_user_department (department_id, user_id);

create unique index ix_sec_user_group_1 on sec_user_group (user_id, group_id);
create unique index ix_sec_user_group_2 on sec_user_group (group_id, user_id);

create unique index ix_sec_user_organization_1 on sec_user_organization (user_id, organization_id);
create unique index ix_sec_user_organization_2 on sec_user_organization (organization_id, user_id);

create index ix_stk_detail_track_1 on stk_detail_track (detail_id);
create index ix_stk_detail_track_2 on stk_detail_track (root_detail_id);
create index ix_stk_detail_track_3 on stk_detail_track (parent_track_id);

create index ix_stk_document_1 on stk_document (organization_id, fiscal_period1_id, doc_date);
create index ix_stk_document_2 on stk_document (organization_id, fiscal_period2_id, doc_date);

create index ix_tra_detail_req on tra_detail (document_req_id);
create index ix_tra_detail_stk on tra_detail (document_stk_id);
create index ix_tra_detail_fin on tra_detail (document_fin_id);

