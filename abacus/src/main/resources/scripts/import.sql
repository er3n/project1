insert into org_organization (id, name, level_enum, parent_id) values ('#', '# Holdingi', 'L0', null);
insert into org_organization (id, name, level_enum, parent_id) values ('#.#', '# Şirketi', 'L1', '#');
insert into org_organization (id, name, level_enum, parent_id) values ('#.#.#', '# Projesi', 'L2', '#.#');
insert into org_organization (id, name, level_enum, parent_id) values ('#.#.#.#', '# Bölgesi', 'L3', '#.#.#');
commit;

insert into sec_authority (id) values ('AUTH_SECURITY');
insert into sec_authority (id) values ('AUTH_ORGANIZATION');
insert into sec_authority (id) values ('AUTH_DEFINITION');
insert into sec_authority (id) values ('AUTH_NONE');
commit;

insert into sec_group (id, name, version) values (nextval('seq_id'), 'Administrator', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'L0.Holding', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'L1.Şirket', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'L2.Proje', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'L3.Bölge', 0);
commit;
 
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_SECURITY', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_ORGANIZATION', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_DEFINITION', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_NONE', 0);
commit;

insert into sec_user( id, is_active, password) values ('admin', 1, 'e10adc3949ba59abbe56e057f20f883e');
insert into sec_user( id, is_active, password) values ('person', 1, 'e10adc3949ba59abbe56e057f20f883e');
commit;

insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin', 1, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'person', 1, 0);
commit;

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#.#', 0);

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'person','#.#.#.#', 0);
commit;

insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#', 'F', 'SY','Şirket Yönetimi',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#', 'F', 'BY','Proje Yönetimi',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#', 'S', 'BD1','Proje Depo 1',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#', 'S', 'BD2','Proje Depo 2',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#.#', 'F', 'PY','Bölge Yönetimi',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#.#', 'S', 'PD1','Bölge Depo 1',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#.#', 'S', 'PD2','Bölge Depo 2',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#', 'F', 'SY','Şirket Yonetimi',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#', 'S', 'SD1','Şirket Depo 1',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#', 'S', 'SD2','Şirket Depo 2',0);
commit; 

insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-3, 'admin', 0, 1, 0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-3, 'admin', 0, 1, 0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-3, 'admin', 0, 1, 0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-4, 'person', 0, 0, 1);
commit;

insert into def_type (id, name, level, tr_state_type) values ('.', '.', 0, 0);
insert into def_type (id, name, level, tr_state_type) values ('PRM_STOCK', 'Stok Param', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('VAL_CATEGORY', 'Stok Kategorileri', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('VAL_MEAL', 'Yemek Öğünleri', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('VAL_RECEIPT', 'Yemek Tipleri', 1, 0);

insert into def_type (id, name, level, tr_state_type) values ('STK_WB_I', 'Stok İrsaliye Giriş Tip', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_S', 'Stok Açılış Tip', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_I', 'Stok İşlem Giriş Tip', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_O', 'Stok İşlem Çıkış Tip', 1, -1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_T', 'Stok Transfer Tip', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('REQ_IO_T', 'İstek Transfer Tip', 1, 0);

insert into def_type (id, name, level, tr_state_type) values ('FIN_B', 'Alış Fatura', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('FIN_P', 'Ödeme', 1, -1);

insert into def_type (id, name, level, tr_state_type) values ('ITM_SR_FN', 'Finans Gider Tanımları', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_SR_ST', 'Malzeme Tanımları', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_VE', 'Firma Tanımları', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_PE', 'Personel Tanımları', 1, 0);
commit;

insert into def_param (id, type_id, code, name) values ('PRM_STOCK_COSTTYPE', 'PRM_STOCK', 'COSTTYPE', 'Stk Cost Type');
commit;

insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'STK_WB_I', 'WB-I', 'Stok İrsaliye #', 1, 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'STK_IO_S', 'IO-S', 'Stok Açılış #', 1, 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'STK_IO_I', 'IO-I', 'Stok Giriş #', 1, 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'STK_IO_O', 'IO-O', 'Stok Çıkış #', 1, 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'STK_IO_T', 'IO-T', 'Stok Transfer #', 1, 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'REQ_IO_T', 'R-IO-T', 'İstek stok Transfer #', 1, 0);

insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'FIN_B', 'BL-1', 'Alış Fatura #', 1, 0);

insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'FIN_P', 'PY-1', 'Personel Maaş #', 1, 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'FIN_P', 'PY-2', 'Masraf Giriş #', 1, 0);
commit;

insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '#', 'SAY', 'Sayilabilen', 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'B0001', 'Adet', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'B0005', 'Bağ', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-3, 'B0006', 'Kutu', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-4, 'B0007', 'Bardak', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-5, 'B0008', 'Poşet', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-6, 'B0011', 'Kâse', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-7, 'B0012', 'Poşet', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-8, 'B0013', 'Paket', 1, 0);
insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '#', 'AGR', 'Agirlik',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'B0002', 'Kg.', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'B0003', 'Gr.', 0.001, 0);
insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '#', 'HAC', 'Hacim',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'B0004', 'Lt. (litre)', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'B0009', 'Şişe - 50 ml.', 0.05, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-3, 'B0010', 'Şişe - 1 Lt.', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-4, 'B0014', 'Ml.', 0.001, 0);

insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '#', 'YMK', 'Yemek',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'Y1', 'Porsiyon', 1, 0);
commit;

insert into def_param_answer (id, version, param_id, organization_id, answer) values (nextval('seq_id'), 0, 'PRM_STOCK_COSTTYPE', '#', 'FIFO');
commit;

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (0, '#', '.', null, '.', '.', 0, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_MEAL', 0, '1', 'Kahvalti', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_MEAL', 0, '2', 'Oglen Yemegi', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_MEAL', 0, '3', 'Aksam Yemegi', 1, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', 0, 'H', 'Hammadde', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'H0001', 'Meyve', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'H0002', 'Sebze', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-3, 'H0003', 'Et Ürünleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-4, 'H0004', 'Süt Ürünleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-5, 'H0005', 'Tahıllar', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', 0, 'G', 'Yemekler', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'G0001', 'ÇORBA GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'G0002', 'ET GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-3, 'G0003', 'BAKLİYAT GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-4, 'G0004', 'SEBZE GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-5, 'G0005', 'ZEYTİNYAĞLI GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-6, 'G0006', 'PİLAV MAKARNA', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-7, 'G0007', 'BÖREKLER', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-8, 'G0008', 'HOŞAF KOMPOSTO GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', 0, 'S', 'Sarf Malzeme', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'S0001', 'Kırtasiye', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'S0002', 'Temizlik Malzemesi', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', 0, 'F', 'Diğer Gider Tipleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'F100', 'Sabit Fatura Giderleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'F200', 'Personel Gideri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'F300', 'Ulaşım Giderleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'F400', 'Finansman Giderleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'F500', 'Genel Giderler', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', 0, 'T', 'Tedarikçi Firmalar', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'T100', 'Gıda Firmaları', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'P100', 'Personeller', 1, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0001', 'Normal Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0002', 'Normal Kahvaltı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0003', 'Diyet Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0004', 'Diyet Kahvaltı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0005', 'Çocuk Diyet Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0006', 'Çocuk Diyet Kahvaltı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0007', 'Ara Öğün', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0008', 'Düğün Yemeği', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0009', 'Sünnet Yemeği', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', 0, 'T0010', 'Özel Yemek', 1, 0);
commit;

insert into cat_meal_filter (id, version, date_start, date_finish, description, meal_id, organization_id, count_prepare) values (nextval('seq_id'), 0, DATE '2014-01-01', DATE '2014-12-31', 'Sabah', (select v.id from def_value v where v.type_id='VAL_MEAL' and v.code='1' and organization_id='#'), '#.#', 150);
insert into cat_meal_filter (id, version, date_start, date_finish, description, meal_id, organization_id, count_prepare) values (nextval('seq_id'), 0, DATE '2014-01-01', DATE '2014-12-31', 'Öğlen', (select v.id from def_value v where v.type_id='VAL_MEAL' and v.code='2' and organization_id='#'), '#.#', 200);
insert into cat_meal_filter (id, version, date_start, date_finish, description, meal_id, organization_id, count_prepare) values (nextval('seq_id'), 0, DATE '2014-01-01', DATE '2014-12-31', 'Akşam', (select v.id from def_value v where v.type_id='VAL_MEAL' and v.code='3' and organization_id='#'), '#.#', 175);
commit;


INSERT INTO org_fiscal_year(id, cost_type, date_finish, date_start, name, year, organization_id) VALUES ('#.#:2013', 'FIFO', DATE '2013-12-31', DATE '2013-01-01', '2013 Fin.Yil', '2013', '#.#');
INSERT INTO org_fiscal_year(id, cost_type, date_finish, date_start, name, year, organization_id) VALUES ('#.#:2014', 'FIFO', DATE '2014-12-31', DATE '2014-01-01', '2014 Fin.Yil', '2014', '#.#');
INSERT INTO org_fiscal_year(id, cost_type, date_finish, date_start, name, year, organization_id) VALUES ('#.#:2015', 'FIFO', DATE '2015-12-31', DATE '2015-01-01', '2015 Fin.Yil', '2015', '#.#');

INSERT INTO org_fiscal_period(id, date_finish, date_start, period_no, fiscal_id, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#:2013:01', DATE '2013-12-31', DATE '2013-01-01','01', '#.#:2013', 1, 1, 1);
INSERT INTO org_fiscal_period(id, date_finish, date_start, period_no, fiscal_id, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#:2014:01', DATE '2014-12-31', DATE '2014-01-01','01', '#.#:2014', 1, 1, 1);
INSERT INTO org_fiscal_period(id, date_finish, date_start, period_no, fiscal_id, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#:2015:01', DATE '2015-12-31', DATE '2015-01-01','01', '#.#:2015', 1, 1, 1);
commit;

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'P101', null, 'Personel X', (select v.id from def_value v where v.code='P100' and organization_id='#'), '#', 'ITM_PE', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'T101', null, 'TazeGevrek Gıda', (select v.id from def_value v where v.code='T100' and organization_id='#'), '#', 'ITM_VE', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'T102', null, 'Dört mevsim manav', (select v.id from def_value v where v.code='T100' and organization_id='#'), '#', 'ITM_VE', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'T103', null, 'Bizim kasap', (select v.id from def_value v where v.code='T100' and organization_id='#'), '#', 'ITM_VE', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F101', null, 'Elektrik', (select v.id from def_value v where v.code='F100' and organization_id='#'), '#', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F102', null, 'Su', (select v.id from def_value v where v.code='F100' and organization_id='#'), '#', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F103', null, 'Telefon', (select v.id from def_value v where v.code='F100' and organization_id='#'), '#', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F104', null, 'Gaz', (select v.id from def_value v where v.code='F100' and organization_id='#'), '#', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F201', null, 'Personel Maaş', (select v.id from def_value v where v.code='F200' and organization_id='#'), '#', 'ITM_SR_FN', null);
commit;


