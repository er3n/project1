
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
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin', 1, 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','#', 0);

insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#', 'A', 'HY', 'Holding Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#', 'S', 'HD', 'Holding Depo',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#', 'A', 'SY','Sirket Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#', 'S', 'SD','Sirket Depo',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#', 'A', 'BY','Bolge Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#', 'S', 'BD','Bolge Depo',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#.#', 'A', 'PY','Project Yonetim',0);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '#.#.#.#', 'S', 'PD','Project Depo',0);

insert into def_type (id, name, level, trtype) values ('.', '.', 0, 0);

insert into def_type (id, name, level, trtype) values ('PRM_STOCK', 'Stok Param', 1, 0);

insert into def_type (id, name, level, trtype) values ('STT_STOCK', 'Stok State', 1, 0);

insert into def_type (id, name, level, trtype) values ('VAL_SEHIR', 'Sehirler', 9, 0);
insert into def_type (id, name, level, trtype) values ('VAL_TAT', 'Tat Tipleri', 1, 0);
insert into def_type (id, name, level, trtype) values ('VAL_BESIN', 'Besin Tipleri', 1, 0);

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

insert into def_type (id, name, level, trtype) values ('ITM_SRV_FIN', 'Hizmet Tanimlari', 1, 0);
insert into def_type (id, name, level, trtype) values ('ITM_SRV_STK', 'Malzeme Tanimlari', 1, 0);


insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (0, '#', '.', null, '.', '.', 0, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_TAT', 0, 'TA', 'Tatli', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_TAT', 0, 'TU', 'Tuzlu', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_TAT', 0, 'EK', 'Eksi', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_TAT', 0, 'AC', 'Aci', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_BESIN', 0, 'Y', 'Yag', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_BESIN', 0, 'K', 'Karbonhidrat', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_BESIN', 0, 'Y', 'Protein', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_SEHIR', 0, '06', 'Ankara', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_SEHIR', currval('seq_id')-1, '06.01', 'Cankaya', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_SEHIR', 0, '34', 'Istanbul', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'VAL_SEHIR', currval('seq_id')-1, '34.01', 'Fatih', 1, 0);

insert into def_state (id, type_id, code, name) values ('STT_STOCK_PRE', 'STT_STOCK', 'PRE', 'Stk Prepare State');
insert into def_state (id, type_id, code, name) values ('STT_STOCK_ENT', 'STT_STOCK', 'ENT', 'Stk Entry State');
insert into def_state (id, type_id, code, name) values ('STT_STOCK_CAN', 'STT_STOCK', 'CAN', 'Stk Cancel State');

insert into def_param (id, type_id, code, name) values ('PRM_STOCK_COSTTYPE', 'PRM_STOCK', 'COSTTYPE', 'Stk Cost Type');

insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'STK_IO_I', '1', 'Stk Giris', 1, 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) values (nextval('seq_id'), '#', 'STK_IO_O', '1', 'Stk Cikis', 1, 0);

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

commit;

insert into org_organization (id, name, level_enum, parent_id) select replace(id,'#','01') id, replace(name,'#','01') as name, level_enum, replace(parent_id,'#','01') parent_id from org_organization where id like '#%';
insert into org_department (id, organization_id, group_enum, code, name, version) select nextval('seq_id') id, replace(organization_id,'#','01') organization_id, group_enum, code, name, version from org_department where organization_id like '#%';

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','01', 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) select nextval('seq_id') id, '01' organization_id, type_id, code, name, is_active, version from def_task where organization_id = '#';
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) select nextval('seq_id') id, '01' organization_id, type_id, parent_id, code, name, is_active, version from def_value where organization_id = '#' and id>0;
update def_value v set parent_id = (select p.id from def_value p where p.organization_id = v.organization_id and p.type_id = v.type_id and p.code = (select x.code from def_value x where x.id = v.parent_id)) where v.organization_id = '01' and v.parent_id > 0;

insert into def_unit_group (id, organization_id, code, name, version) select nextval('seq_id'), '01' organization_id, code, name , version from def_unit_group where organization_id='#';
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) select nextval('seq_id') id, (select g.id from def_unit_group g where g.organization_id = '01' and g.code= (select x.code from def_unit_group x where x.id = y.unit_group_id)) unit_group_id, code, name, ratio, version from  def_unit_code y where unit_group_id in (select id from def_unit_group where organization_id='#');

commit;
