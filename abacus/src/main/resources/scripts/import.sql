insert into org_organization (id, name, level_enum, parent_id) values ('#', '# Holdingi', 'L0', null);
insert into org_organization (id, name, level_enum, parent_id) values ('#.#1', '# 1.Şirketi', 'L1', '#');
insert into org_organization (id, name, level_enum, parent_id) values ('#.#1.#1', '# 1.1.Projesi', 'L2', '#.#1');
insert into org_organization (id, name, level_enum, parent_id) values ('#.#1.#2', '# 1.2.Projesi', 'L2', '#.#1');
insert into org_organization (id, name, level_enum, parent_id) values ('#.#2', '# 2.Şirketi', 'L1', '#');
insert into org_organization (id, name, level_enum, parent_id) values ('#.#2.#1', '# 2.1.Projesi', 'L2', '#.#2');
commit;

insert into sec_authority (id, name) values ('AUTH_NONE','Önemsiz');
insert into sec_authority (id, name) values ('AUTH_REPORT','Raporlar');
insert into sec_authority (id, name) values ('AUTH_SECURITY','Güvenlik');
insert into sec_authority (id, name) values ('AUTH_ORGANIZATION','Organizasyon');
insert into sec_authority (id, name) values ('AUTH_DEFINITION','Hesaplar');
insert into sec_authority (id, name) values ('AUTH_FINANCE','Finans');
insert into sec_authority (id, name) values ('AUTH_INVENTORY','Stok');
insert into sec_authority (id, name) values ('AUTH_PURCHASE','Satınalma');
insert into sec_authority (id, name) values ('AUTH_OFFER','Teklif Giriş');
commit;

insert into sec_group (id, name, version) values (nextval('seq_id'), 'System Admin', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Satıcı Grubu', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Patron', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Şirket Yöneticisi', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Finans Yöneticisi', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Proje Yöneticisi', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Depo Yöneticisi', 0);
commit;
 
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_NONE', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_REPORT', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_SECURITY', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_ORGANIZATION', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_DEFINITION', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_FINANCE', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_INVENTORY', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_PURCHASE', 0);

insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 2, 'AUTH_OFFER', 0);
commit;

insert into sec_user( id, is_active, password) values ('admin', 1, 'e10adc3949ba59abbe56e057f20f883e');
insert into sec_user( id, is_active, password) values ('vendor1', 1, 'e10adc3949ba59abbe56e057f20f883e');
insert into sec_user( id, is_active, password) values ('vendor2', 1, 'e10adc3949ba59abbe56e057f20f883e');
commit;

insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin', 1, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'vendor1', 2, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'vendor2', 2, 0);
commit;

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#.#1', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#.#1.#1', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#.#1.#2', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#.#2', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#.#2.#1', 0);

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','#.#1', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','#.#1.#1', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','#.#1.#2', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','#.#2', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','#.#2.#1', 0);

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','#.#1', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','#.#1.#1', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','#.#1.#2', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','#.#2', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','#.#2.#1', 0);
commit;

insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#1', 'F', 'SY1','1.Şirket Ofisi',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#1', 'S', 'SD1_A','1.Şirket Depo A',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#1', 'S', 'SD1_B','1.Şirket Depo B',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#1', 'SP', 'SP1','1.Şirket Satınalma Depo',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#1.#1', 'F', 'PY1.1','1.1.Proje Ofisi',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#1.#1', 'S', 'PD1.1_X','1.1.Proje Depo X',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#1.#1', 'S', 'PD1.1_Y','1.1.Proje Depo Y',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#1.#1', 'SP', 'PP1.1','1.1.Proje Satınalma Depo',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);

insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#2.#1', 'S', 'PD2.1_X','2.1.Proje Depo X',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
commit;


insert into def_type (id, name, level, tr_state_type) values ('PRM_STOCK', 'Stok Param', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('VAL_CATEGORY', 'Grup/Kategori', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('VAL_MEAL', 'Yemek Öğünleri', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('VAL_RECEIPT', 'Yemek Tipleri', 1, 0);

insert into def_type (id, name, level, tr_state_type) values ('STK_WB_I', 'Stok İrsaliye Giriş Tip', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_S', 'Stok Açılış Tip', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_I', 'Stok İşlem Giriş Tip', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_O', 'Stok İşlem Çıkış Tip', 1, -1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_T', 'Stok Transfer Tip', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('REQ_IO_T', 'İstek Transfer Tip', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('REQ_IO_P', 'İstek Satınalma Tip', 1, 0);

insert into def_type (id, name, level, tr_state_type) values ('FIN_B', 'Alış Fatura', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('FIN_P', 'Ödeme', 1, -1);
insert into def_type (id, name, level, tr_state_type) values ('FIN_R', 'Tahsilat', 1, +1);

insert into def_type (id, name, level, tr_state_type) values ('ITM_SR_ST', 'Stok Malzeme/Ürün', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_SR_FN', 'Gider Hesapları', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_CM_CU', 'Müşteri Hesapları', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_CM_VE', 'Satıcı Hesapları', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_PE', 'Personel Hesapları', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_CS', 'Parasal Hesaplar', 1, 0);
commit;

insert into def_param (id, type_id, code, name) values ('PRM_STOCK_COSTTYPE', 'PRM_STOCK', 'COSTTYPE', 'Stk Cost Type');
commit;

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'STK_WB_I', 'WB-I', 'Stok İrsaliye #', 1, 0, null, null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'STK_IO_S', 'IO-S', 'Stok Açılış #', 1, 0, null, null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'STK_IO_I', 'IO-I', 'Stok Giriş #', 1, 0, null, null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'STK_IO_O', 'IO-O', 'Stok Çıkış #', 1, 0, null, null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'STK_IO_T', 'IO-T', 'Stok Transfer #', 1, 0, null, null);

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'REQ_IO_T', 'R-IO-T', 'Stok transfer isteği #', 1, 0, null, null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'REQ_IO_P', 'R-IO-P', 'Stok satınalma isteği #', 1, 0, null, null);

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'FIN_B', 'BL-1', 'Alış Fatura #', 1, 0, 'ITM_CM_VE', 'ITM_SR');

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'FIN_P', 'PY-1', 'Satıcıya Ödeme #', 1, 0, 'ITM_CS', 'ITM_CM_VE');
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'FIN_P', 'PY-2', 'Personel Maaş #', 1, 0, 'ITM_CS', 'ITM_PE');
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'FIN_P', 'PY-3', 'Masraf Giriş #', 1, 0, 'ITM_CS', 'ITM_SR_FN');

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '#', 'FIN_R', 'RC-1', 'Müşteriden Tahsil #', 1, 0, 'ITM_CS', 'ITM_CM_CU');
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

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_MEAL', null, '1', 'Kahvalti', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_MEAL', null, '2', 'Oglen Yemegi', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_MEAL', null, '3', 'Aksam Yemegi', 1, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', null, 'STK', 'Stok Grupları', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', (select v.id from def_value v where v.type_id='VAL_CATEGORY' and v.code='STK' and organization_id='#'), 'STK-H', 'Hammadde', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'H0001', 'Meyve', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'H0002', 'Sebze', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-3, 'H0003', 'Et Ürünleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-4, 'H0004', 'Süt Ürünleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-5, 'H0005', 'Bakliyat', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', (select v.id from def_value v where v.type_id='VAL_CATEGORY' and v.code='STK' and organization_id='#'), 'STK-G', 'Yemekler', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'G0001', 'ÇORBA GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'G0002', 'ET GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-3, 'G0003', 'BAKLİYAT GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-4, 'G0004', 'SEBZE GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-5, 'G0005', 'ZEYTİNYAĞLI GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-6, 'G0006', 'PİLAV MAKARNA', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-7, 'G0007', 'BÖREKLER', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-8, 'G0008', 'HOŞAF KOMPOSTO GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', (select v.id from def_value v where v.type_id='VAL_CATEGORY' and v.code='STK' and organization_id='#'), 'STK-S', 'Sarf Malzeme', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'S0001', 'Kırtasiye', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'S0002', 'Temizlik Malzemesi', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', null, 'FIN', 'Gider Tipleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'F100', 'Sabit Fatura Giderleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'F200', 'Personel Gideri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-3, 'F300', 'Ulaşım Giderleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-4, 'F400', 'Finansman Giderleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-5, 'F500', 'Genel Giderler', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', null, 'CMP', 'Firmalar', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'T100', 'Satıcı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'T200', 'Müşteri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', null, 'PER', 'Çalışanlar', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'P100', 'Personel', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'P200', 'Taşeron', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', null, 'CSH', 'Tahsilat/Ödeme', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'C100', 'Para', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', null, '?', 'Tanımsız', 1, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0001', 'Normal Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0002', 'Normal Kahvaltı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0003', 'Diyet Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0004', 'Diyet Kahvaltı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0005', 'Çocuk Diyet Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0006', 'Çocuk Diyet Kahvaltı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0007', 'Ara Öğün', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0008', 'Düğün Yemeği', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0009', 'Sünnet Yemeği', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_RECEIPT', null, 'T0010', 'Özel Yemek', 1, 0);
commit;

insert into org_fiscal_year(id, organization_id, name, date_start, date_finish) VALUES ('#.#1:'||trim(to_char(nextval('seq_id'),'00000000')), '#.#1', 'FY.1:2014', DATE '2014-01-01', DATE '2014-12-31');
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '#.#1:'||trim(to_char(currval('seq_id'),'00000000')), 1, DATE '2014-01-01', DATE '2014-12-31', 1, 1, 1);

insert into org_fiscal_year(id, organization_id, name, date_start, date_finish) VALUES ('#.#1.#1:'||trim(to_char(nextval('seq_id'),'00000000')), '#.#1.#1', 'Prj.1.1:2014', DATE '2014-01-01', DATE '2014-12-31');
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')), 1, DATE '2014-01-01', DATE '2014-01-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':02', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')), 2, DATE '2014-02-01', DATE '2014-02-28', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':03', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')), 3, DATE '2014-03-01', DATE '2014-03-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':04', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')), 4, DATE '2014-04-01', DATE '2014-04-30', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':05', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')), 5, DATE '2014-05-01', DATE '2014-05-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':06', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')), 6, DATE '2014-06-01', DATE '2014-06-30', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':07', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')), 7, DATE '2014-07-01', DATE '2014-07-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':08', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')), 8, DATE '2014-08-01', DATE '2014-08-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':09', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')), 9, DATE '2014-09-01', DATE '2014-09-30', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':10', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')),10, DATE '2014-10-01', DATE '2014-10-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':11', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')),11, DATE '2014-11-01', DATE '2014-11-30', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':12', '#.#1.#1:'||trim(to_char(currval('seq_id'),'00000000')),12, DATE '2014-12-01', DATE '2014-12-31', 1, 1, 1);
commit;

insert into cat_meal_filter (id, version, fiscal_year_id, meal_id, count_prepare, unit_price) values (nextval('seq_id'), 0, '#.#1.#1:'||trim(to_char(currval('seq_id')-1,'00000000')), (select v.id from def_value v where v.type_id='VAL_MEAL' and v.code='1' and organization_id='#'), 150, 3.0);
insert into cat_meal_filter (id, version, fiscal_year_id, meal_id, count_prepare, unit_price) values (nextval('seq_id'), 0, '#.#1.#1:'||trim(to_char(currval('seq_id')-2,'00000000')), (select v.id from def_value v where v.type_id='VAL_MEAL' and v.code='2' and organization_id='#'), 200, 5.0);
insert into cat_meal_filter (id, version, fiscal_year_id, meal_id, count_prepare, unit_price) values (nextval('seq_id'), 0, '#.#1.#1:'||trim(to_char(currval('seq_id')-3,'00000000')), (select v.id from def_value v where v.type_id='VAL_MEAL' and v.code='3' and organization_id='#'), 175, 7.5);
commit;

insert into bud_document(id,version,fiscal_year_id,estimate_date, budget_note) values (nextval('seq_id'),0,'#.#1.#1:'||trim(to_char(currval('seq_id')-4,'00000000')), DATE '2014-01-01','2014 #.#1.#1 butcesi')
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -1,'#.#1.#1:'||trim(to_char(currval('seq_id')-4- 1,'00000000'))||':01', 'BUD_R','ESTIMATE',510)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -2,'#.#1.#1:'||trim(to_char(currval('seq_id')-4- 2,'00000000'))||':02', 'BUD_R','ESTIMATE',750)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -3,'#.#1.#1:'||trim(to_char(currval('seq_id')-4- 3,'00000000'))||':03', 'BUD_R','ESTIMATE',620)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -4,'#.#1.#1:'||trim(to_char(currval('seq_id')-4- 4,'00000000'))||':04', 'BUD_R','ESTIMATE',830)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -5,'#.#1.#1:'||trim(to_char(currval('seq_id')-4- 5,'00000000'))||':05', 'BUD_R','ESTIMATE',660)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -6,'#.#1.#1:'||trim(to_char(currval('seq_id')-4- 6,'00000000'))||':06', 'BUD_R','ESTIMATE',490)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -7,'#.#1.#1:'||trim(to_char(currval('seq_id')-4- 7,'00000000'))||':07', 'BUD_R','ESTIMATE',700)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -8,'#.#1.#1:'||trim(to_char(currval('seq_id')-4- 8,'00000000'))||':08', 'BUD_R','ESTIMATE',680)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -9,'#.#1.#1:'||trim(to_char(currval('seq_id')-4- 9,'00000000'))||':09', 'BUD_R','ESTIMATE',870)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-10,'#.#1.#1:'||trim(to_char(currval('seq_id')-4-10,'00000000'))||':10', 'BUD_R','ESTIMATE',300)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-11,'#.#1.#1:'||trim(to_char(currval('seq_id')-4-11,'00000000'))||':11', 'BUD_R','ESTIMATE',460)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-12,'#.#1.#1:'||trim(to_char(currval('seq_id')-4-12,'00000000'))||':12', 'BUD_R','ESTIMATE',450)

insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-13,'#.#1.#1:'||trim(to_char(currval('seq_id')-16- 1,'00000000'))||':01', 'BUD_X','ESTIMATE',505)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-14,'#.#1.#1:'||trim(to_char(currval('seq_id')-16- 2,'00000000'))||':02', 'BUD_X','ESTIMATE',760)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-15,'#.#1.#1:'||trim(to_char(currval('seq_id')-16- 3,'00000000'))||':03', 'BUD_X','ESTIMATE',590)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-16,'#.#1.#1:'||trim(to_char(currval('seq_id')-16- 4,'00000000'))||':04', 'BUD_X','ESTIMATE',800)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-17,'#.#1.#1:'||trim(to_char(currval('seq_id')-16- 5,'00000000'))||':05', 'BUD_X','ESTIMATE',500)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-18,'#.#1.#1:'||trim(to_char(currval('seq_id')-16- 6,'00000000'))||':06', 'BUD_X','ESTIMATE',450)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-19,'#.#1.#1:'||trim(to_char(currval('seq_id')-16- 7,'00000000'))||':07', 'BUD_X','ESTIMATE',620)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-20,'#.#1.#1:'||trim(to_char(currval('seq_id')-16- 8,'00000000'))||':08', 'BUD_X','ESTIMATE',600)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-21,'#.#1.#1:'||trim(to_char(currval('seq_id')-16- 9,'00000000'))||':09', 'BUD_X','ESTIMATE',570)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-22,'#.#1.#1:'||trim(to_char(currval('seq_id')-16-10,'00000000'))||':10', 'BUD_X','ESTIMATE',280)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-23,'#.#1.#1:'||trim(to_char(currval('seq_id')-16-11,'00000000'))||':11', 'BUD_X','ESTIMATE',415)
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-24,'#.#1.#1:'||trim(to_char(currval('seq_id')-16-12,'00000000'))||':12', 'BUD_X','ESTIMATE',400)
commit;

insert into org_fiscal_year(id, organization_id, name, date_start, date_finish) VALUES ('#.#1.#2:'||trim(to_char(nextval('seq_id'),'00000000')), '#.#1.#2', 'Prj.1.2:2014', DATE '2014-01-01', DATE '2014-12-31');
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#1.#2:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '#.#1.#2:'||trim(to_char(currval('seq_id'),'00000000')), 1, DATE '2014-01-01', DATE '2014-12-31', 1, 1, 1);


insert into org_fiscal_year(id, organization_id, name, date_start, date_finish) VALUES ('#.#2:'||trim(to_char(nextval('seq_id'),'00000000')), '#.#2', 'FY.2:2014', DATE '2014-01-01', DATE '2014-12-31');
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#2:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '#.#2:'||trim(to_char(currval('seq_id'),'00000000')), 1, DATE '2014-01-01', DATE '2014-12-31', 1, 1, 1);

insert into org_fiscal_year(id, organization_id, name, date_start, date_finish) VALUES ('#.#2.#1:'||trim(to_char(nextval('seq_id'),'00000000')), '#.#2.#1', 'Prj.2.1:2014', DATE '2014-01-01', DATE '2014-12-31');
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('#.#2.#1:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '#.#2.#1:'||trim(to_char(currval('seq_id'),'00000000')), 1, DATE '2014-01-01', DATE '2014-12-31', 1, 1, 1);
commit;


insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'P101', null, 'Personel X', (select v.id from def_value v where v.code='P100' and organization_id='#'), '#', 'ITM_PE', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'C101', null, 'Nakit', (select v.id from def_value v where v.code='C100' and organization_id='#'), '#', 'ITM_CS', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'C102', null, 'Banka', (select v.id from def_value v where v.code='C100' and organization_id='#'), '#', 'ITM_CS', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'T101', null, 'TazeGevrek Gıda', (select v.id from def_value v where v.code='T100' and organization_id='#'), '#', 'ITM_CM_VE', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'T102', null, 'Dört mevsim manav', (select v.id from def_value v where v.code='T100' and organization_id='#'), '#', 'ITM_CM_VE', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'T103', null, 'Bizim kasap', (select v.id from def_value v where v.code='T100' and organization_id='#'), '#', 'ITM_CM_VE', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'M101', null, 'Müşteri X', (select v.id from def_value v where v.code='T200' and organization_id='#'), '#', 'ITM_CM_CU', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F101', null, 'Elektrik', (select v.id from def_value v where v.code='F100' and organization_id='#'), '#', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F102', null, 'Su', (select v.id from def_value v where v.code='F100' and organization_id='#'), '#', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F103', null, 'Telefon', (select v.id from def_value v where v.code='F100' and organization_id='#'), '#', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F104', null, 'Gaz', (select v.id from def_value v where v.code='F100' and organization_id='#'), '#', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F201', null, 'Personel Maaş', (select v.id from def_value v where v.code='F200' and organization_id='#'), '#', 'ITM_SR_FN', null);
commit;

insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'#','ITM_SR_ST','VAL_CATEGORY',(select p.id from def_value p where p.code = 'STK' and p.organization_id = '#' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'#','ITM_SR_FN','VAL_CATEGORY',(select p.id from def_value p where p.code = 'FIN' and p.organization_id = '#' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'#','ITM_CM_VE','VAL_CATEGORY',(select p.id from def_value p where p.code = 'CMP' and p.organization_id = '#' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'#','ITM_CM_CU','VAL_CATEGORY',(select p.id from def_value p where p.code = 'CMP' and p.organization_id = '#' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'#','ITM_PE','VAL_CATEGORY',(select p.id from def_value p where p.code = 'PER' and p.organization_id = '#' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'#','ITM_CS','VAL_CATEGORY',(select p.id from def_value p where p.code = 'CSH' and p.organization_id = '#' and p.type_id ='VAL_CATEGORY'));
commit;

update sec_user s set vendor_id = (select t.id from def_item t where t.code = 'T101' and t.organization_id='#' and type_id='ITM_CM_VE') where s.id = 'vendor1';
update sec_user s set vendor_id = (select t.id from def_item t where t.code = 'T102' and t.organization_id='#' and type_id='ITM_CM_VE') where s.id = 'vendor2';
commit;
