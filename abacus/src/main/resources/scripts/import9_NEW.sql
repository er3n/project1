alter table org_fiscal_year add profit_rate numeric(5,2) default 0 not null;
alter table cat_menu_info add count_total numeric(10,0) default 0 not null;
ALTER TABLE org_fiscal_period ALTER COLUMN period_no TYPE character varying(2);
update org_fiscal_period set period_no = '0'||period_no where period_no not in ('10','11','12');
