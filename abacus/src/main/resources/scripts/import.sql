
insert into org_organization (id, name, level_enum, parent_id) values ('#', '# Holding', 'L0', null);
insert into org_organization (id, name, level_enum, parent_id) values ('#.#', '# Company', 'L1', '#');
insert into org_organization (id, name, level_enum, parent_id) values ('#.#.#', '# Section', 'L2', '#.#');
insert into org_organization (id, name, level_enum, parent_id) values ('#.#.#.#', '# Project', 'L3', '#.#.#');

commit;

insert into sec_authority (id) values ('AUTH_SECURITY');
insert into sec_authority (id) values ('AUTH_ORGANIZATION');
insert into sec_authority (id) values ('AUTH_DEFINITION');
insert into sec_authority (id) values ('AUTH_NONE');

insert into sec_group (id, name, version) values (nextval('seq_id'), 'AdminSystem', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'L0.Holding Yonet', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'L1.Sirket Yonet', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'L2.Bolge Yonet', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'L3.Proje Yonet', 0);
 
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_SECURITY', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_ORGANIZATION', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_DEFINITION', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 1, 'AUTH_NONE', 0);

insert into sec_user( id, is_active, password) values ('admin', 1, 'e10adc3949ba59abbe56e057f20f883e');
insert into sec_user( id, is_active, password) values ('person', 1, 'e10adc3949ba59abbe56e057f20f883e');
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin', 1, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'person', 1, 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'person','#', 0);

insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#', 'A', 'HY', 'Holding Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#', 'S', 'HD', 'Holding Depo',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#', 'A', 'SY','Sirket Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#', 'S', 'SD','Sirket Depo',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#', 'A', 'BY','Bolge Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#', 'S', 'BD','Bolge Depo',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#.#', 'A', 'PY','Project Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#.#', 'S', 'PD','Project Depo',0);

insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-2, 'person', 0, 0, 1);

insert into def_type (id, name, level, trtype) values ('.', '.', 0, 0);

insert into def_type (id, name, level, trtype) values ('PRM_STOCK', 'Stok Param', 1, 0);

insert into def_type (id, name, level, trtype) values ('VAL_CATEGORY', 'Stok Kategorileri', 1, 0);
insert into def_type (id, name, level, trtype) values ('VAL_MEAL', 'Yemek Ögünleri', 1, 0);

insert into def_type (id, name, level, trtype) values ('STK_OC', 'Stok Open/Close', 1, 0);
insert into def_type (id, name, level, trtype) values ('STK_OC_I', 'Stok Open', 1, +1);
insert into def_type (id, name, level, trtype) values ('STK_IO', 'Stok In/Out', 1, 0);
insert into def_type (id, name, level, trtype) values ('STK_IO_I', 'Stok Inputs', 1, +1);
insert into def_type (id, name, level, trtype) values ('STK_IO_O', 'Stok Outputs', 1, -1);
insert into def_type (id, name, level, trtype) values ('STK_WB', 'Stok WayBill', 1, 0);
insert into def_type (id, name, level, trtype) values ('STK_WB_I', 'Stok WayBill IN', 1, +1);
insert into def_type (id, name, level, trtype) values ('STK_WB_O', 'Stok WayBill OUT', 1, -1);
insert into def_type (id, name, level, trtype) values ('STK_TT', 'Stok Transfer', 1, 0);

insert into def_type (id, name, level, trtype) values ('FIN_CS', 'Masraf Giris', 1, 0);

insert into def_type (id, name, level, trtype) values ('ITM_SR_FN', 'Hizmet Tanimlari', 1, 0);
insert into def_type (id, name, level, trtype) values ('ITM_SR_ST', 'Malzeme Tanimlari', 1, 0);

insert into def_param (id, type_id, code, name) values ('PRM_STOCK_COSTTYPE', 'PRM_STOCK', 'COSTTYPE', 'Stk Cost Type');

insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'STK_IO_I', '1', 'Stk Giris #', 1, 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'STK_IO_O', '1', 'Stk Cikis #', 1, 0);

insert into def_unit_group (id, organization_id, code, name, version) values (0, '#', '*', 'Bilinmeyen', 0);
insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '#', 'Y', 'Yemek', 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'PR', 'Porsiyon', 1, 0);
insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '#', 'S', 'Sayilabilen', 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'AD', 'Adet', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'DS', 'Deste', 10, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-3, 'DZ', 'Düzine', 12, 0);
insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '#', 'A', 'Agirlik',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'GR', 'Gram', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'KG', 'Kilo', 1000, 0);
insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '#', 'H', 'Hacim',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'LT', 'Litre', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'ML', 'MiliLitre', 0.001, 0);
insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '#', 'U', 'Uzunluk',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'MT', 'Metre', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'CM', 'SantiMetre', 0.01, 0);

insert into def_param_answer (id, version, param_id, organization_id, answer) values (nextval('seq_id'), 0, 'PRM_STOCK_COSTTYPE', '#', 'FIFO');

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (0, '#', '.', null, '.', '.', 0, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', 0, 'G', 'Gida', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'G1', 'Et', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'G2', 'Meyve', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-3, 'G3', 'Sebze', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-3, 'G4', 'Sut Urunleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', 0, 'Y', 'Yemek Tipleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'Y1', 'Ana Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'Y2', 'Salata', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-3, 'Y3', 'Tatli', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-4, 'Y4', 'Corba', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', 0, 'S', 'Sarf Malzeme', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-1, 'S1', 'Kirtasiye', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_CATEGORY', currval('seq_id')-2, 'S2', 'Temizlik', 1, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_MEAL', 0, '1', 'Kahvalti', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_MEAL', 0, '2', 'Oglen Yemegi', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_MEAL', 0, '3', 'Aksam Yemegi', 1, 0);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_M:'||currval('seq_id'), 'STK_M', 'STK_M:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1, 'STK_P:'||currval('seq_id'), 'STK_P', 'STK_P:'||currval('seq_id'), 0 , '#' , 'ITM_SR_ST' , 0);

insert into cat_meal_filter (id, version, date_start, date_finish, description, meal_id, organization_id, count_prepare) values (nextval('seq_id'), 0, DATE '2013-01-01', DATE '2015-01-30', 'sabah ocak', 55, '#.#.#.#', 150);
insert into cat_meal_filter (id, version, date_start, date_finish, description, meal_id, organization_id, count_prepare) values (nextval('seq_id'), 0, DATE '2013-01-01', DATE '2015-05-01', 'oglen', 56, '#.#.#.#', 200);
insert into cat_meal_filter (id, version, date_start, date_finish, description, meal_id, organization_id, count_prepare) values (nextval('seq_id'), 0, DATE '2013-01-01', DATE '2015-12-31', 'aksam', 57, '#.#.#.#', 175);
insert into cat_meal_filter (id, version, date_start, date_finish, description, meal_id, organization_id, count_prepare) values (nextval('seq_id'), 0, DATE '2013-03-01', DATE '2015-03-30', 'sabah mart', 55, '#.#.#.#', 150);


