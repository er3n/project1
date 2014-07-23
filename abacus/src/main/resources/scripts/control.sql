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
 