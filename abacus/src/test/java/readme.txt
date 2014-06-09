--Stok View
create view v_stk as
select dtl.fiscal_year_id, doc.doc_date,
       dtl.item_id, itm.code item_code, itm.name item_name, 
       dtl.department_id, dpr.code department_code, dpr.name department_name,
       dtl.base_detail_count, dtl.tr_state_detail
  from stk_detail dtl, stk_document doc, def_item itm, org_department dpr
 where doc.id = dtl.document_id
   and itm.id = dtl.item_id
   and dpr.id = dtl.department_id

--Stok Hareketleri Raporu
select v.fiscal_year_id, v.doc_date,
       v.item_id, v.item_code, v.item_name, 
       v.department_id, v.department_code, v.department_name,
       v.base_detail_count, v.tr_state_detail
  from v_stk v
   
--Stok Durum Raporu
select v.fiscal_year_id, 
       v.department_code, v.department_name, 
       v.item_code, v.item_name, 
       sum(v.base_detail_count*v.tr_state_detail) net_item_count 
  from v_stk v
 group by v.fiscal_year_id, v.department_code, v.department_name, v.item_code, v.item_name
 