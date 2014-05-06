insert into sec_authority (id) values ('AUTH_SECURITY');
insert into sec_authority (id) values ('AUTH_ORGANIZATION');
insert into sec_authority (id) values ('AUTH_DEFINITION');
insert into sec_authority (id) values ('AUTH_NONE');

insert into org_organization (id, name, level_enum, parent_id) values ('01', 'A.HOLDiNG', 'L0', null);
insert into org_organization (id, name, level_enum, parent_id) values ('01.01', 'A.SiRKET', 'L1', '01');
insert into org_organization (id, name, level_enum, parent_id) values ('01.01.01', 'A.BÖLGE', 'L2', '01.01');
insert into org_organization (id, name, level_enum, parent_id) values ('01.01.01.01', 'A.PROJE', 'L3', '01.01.01');
insert into org_organization (id, name, level_enum, parent_id) values ('02', 'B.HOLDiNG', 'L0', null);
insert into org_organization (id, name, level_enum, parent_id) values ('03', 'C.HOLDiNG', 'L0', null);
commit;

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

insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin', 1, 0);

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','01', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','02', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','03', 0);

insert into def_type (id, name, group_enum, level, trtype) values ('.', '.', 'V', 0, 0);
insert into def_type (id, name, group_enum, level, trtype) values ('STKPARAM', 'Stk Param', 'P', 1, 0);
insert into def_type (id, name, group_enum, level, trtype) values ('STKSTATE', 'Stk Status', 'S', 1, 0);
insert into def_type (id, name, group_enum, level, trtype) values ('SEHIR', 'Sehir', 'V', 9, 0);
insert into def_type (id, name, group_enum, level, trtype) values ('RENK', 'Renkler', 'V', 9, 0);

insert into def_type (id, name, group_enum, level, trtype) values ('STK', 'Stok Islemleri', 'T', 1, 0);
insert into def_type (id, name, group_enum, level, trtype) values ('STK_OC', 'Stok Open/Close', 'T', 1, 0);
insert into def_type (id, name, group_enum, level, trtype) values ('STK_OC_I', 'Stok Open', 'T', 1, +1);
insert into def_type (id, name, group_enum, level, trtype) values ('STK_IO', 'Stok In/Out', 'T', 1, 0);
insert into def_type (id, name, group_enum, level, trtype) values ('STK_IO_I', 'Stok Inputs', 'T', 1, +1);
insert into def_type (id, name, group_enum, level, trtype) values ('STK_IO_O', 'Stok Outputs', 'T', 1, -1);
insert into def_type (id, name, group_enum, level, trtype) values ('STK_WB', 'Stok WayBill', 'T', 1, 0);
insert into def_type (id, name, group_enum, level, trtype) values ('STK_WB_I', 'Stok WayBill IN', 'T', 1, +1);
insert into def_type (id, name, group_enum, level, trtype) values ('STK_WB_O', 'Stok WayBill OUT', 'T', 1, -1);
insert into def_type (id, name, group_enum, level, trtype) values ('STK_TT', 'Stok Transfer', 'T', 1, 0);

insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (0, '.', null, '.', '.', 0, 0);
insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), 'SEHIR', 0, '01', 'Ankara', 1, 0);
insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), 'SEHIR', currval('seq_id')-1, '01.01', 'Cankaya', 1, 0);
insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), 'SEHIR', 0, '34', 'Istanbul', 1, 0);
insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), 'SEHIR', currval('seq_id')-1, '34.01', 'Fatih', 1, 0);

insert into def_state (id, type_id, code, name) values ('STKSTATE_PRE', 'STKSTATE', 'PRE', 'Stk Prepare State');
insert into def_state (id, type_id, code, name) values ('STKSTATE_ENT', 'STKSTATE', 'ENT', 'Stk Entry State');
insert into def_state (id, type_id, code, name) values ('STKSTATE_CAN', 'STKSTATE', 'CAN', 'Stk Cancel State');

insert into def_param (id, type_id, code, name) values ('STKPARAM_COSTTYPE', 'STKPARAM', 'COSTTYPE', 'Stk Cost Type');

insert into def_task (id, type_id, code, name, is_active, version) values (nextval('seq_id'), 'STK_IO_I', '1', 'Stk Giris', 1, 0);
insert into def_task (id, type_id, code, name, is_active, version) values (nextval('seq_id'), 'STK_IO_O', '1', 'Stk Cikis', 1, 0);

insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '01', 'A', '01-H', 'Holding Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '01.01', 'A', '01-S','Sirket Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '01.01', 'S', '01-D','Sirket Merkez Depo',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '01.01.01', 'S', '01-P','Proje Depo',0);

insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '01', 'S', 'Sayilabilen', 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'AD', 'Adet', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'DS', 'Deste', 10, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-3, 'DZ', 'Düzine', 12, 0);

insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '01', 'A', 'Agirlik',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'GR', 'Gram', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'KG', 'Kilo', 1000, 0);

insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '01', 'H', 'Hacim',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'LT', 'Litre', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'ML', 'MiliLitre', 0.001, 0);

insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '01', 'U', 'Uzunluk',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'MT', 'Metre', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'CM', 'SantiMetre', 0.01, 0);

commit;