INSERT INTO company (id, name, level, parent_id) VALUES ('01', 'HOLDING', 1, null);
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01', 'SIRKET', 2, '01');
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01.01', 'PROJE', 3, '01.01');

INSERT INTO sec_authority (id) VALUES ('KULLANICI_ISLEMLERI');
INSERT INTO sec_authority (id) VALUES ('KULLANICI_GRUP_ISLEMLERI');
INSERT INTO sec_authority (id) VALUES ('TANIMLAMA_ISLEMLERI');


INSERT INTO sec_user( id, is_active, password) VALUES ('admin', 1, 'e10adc3949ba59abbe56e057f20f883e');

INSERT INTO sec_group (id, name, version) VALUES (nextval('seq_id'), 'Sistem Yoneticisi', 0);
INSERT INTO sec_group (id, name, version) VALUES (nextval('seq_id'), 'Holding Yoneticisi', 0);
INSERT INTO sec_group (id, name, version) VALUES (nextval('seq_id'), 'Sirket Yoneticisi', 0);
INSERT INTO sec_group (id, name, version) VALUES (nextval('seq_id'), 'Proje Yoneticisi', 0);
 
INSERT INTO sec_group_authority (id, group_id, authority_id, version) VALUES (nextval('seq_id'), 1, 'KULLANICI_ISLEMLERI', 0);
INSERT INTO sec_group_authority (id, group_id, authority_id, version) VALUES (nextval('seq_id'), 1, 'KULLANICI_GRUP_ISLEMLERI', 0);
INSERT INTO sec_group_authority (id, group_id, authority_id, version) VALUES (nextval('seq_id'), 1, 'TANIMLAMA_ISLEMLERI', 0);
 
INSERT INTO sec_group_member (id, user_id, group_id, version) VALUES (nextval('seq_id'), 'admin', 1, 0);

INSERT INTO sec_user_company (id, user_id, company_id, version) VALUES (nextval('seq_id'), 'admin','01', 0);

insert into def_type (id, name, group_id, level, trtype) values ('.', '.', '.', 0, 0);
insert into def_type (id, name, group_id, level, trtype) values ('STKPARAM', 'Stk Param', 'P', 1, 0);
insert into def_type (id, name, group_id, level, trtype) values ('STKSTATE', 'Stk Status', 'S', 1, 0);
insert into def_type (id, name, group_id, level, trtype) values ('SEHIR', 'Sehir', 'V', 9, 0);
insert into def_type (id, name, group_id, level, trtype) values ('RENK', 'Renkler', 'V', 9, 0);

insert into def_type (id, name, group_id, level, trtype) values ('STK', 'Stok Islemleri', 'T', 1, 0);
insert into def_type (id, name, group_id, level, trtype) values ('STK_OC', 'Stok Open/Close', 'T', 1, 0);
insert into def_type (id, name, group_id, level, trtype) values ('STK_OC_I', 'Stok Open', 'T', 1, +1);
insert into def_type (id, name, group_id, level, trtype) values ('STK_IO', 'Stok In/Out', 'T', 1, 0);
insert into def_type (id, name, group_id, level, trtype) values ('STK_IO_I', 'Stok Inputs', 'T', 1, +1);
insert into def_type (id, name, group_id, level, trtype) values ('STK_IO_O', 'Stok Outputs', 'T', 1, -1);
insert into def_type (id, name, group_id, level, trtype) values ('STK_WB', 'Stok WayBill', 'T', 1, 0);
insert into def_type (id, name, group_id, level, trtype) values ('STK_WB_I', 'Stok WayBill IN', 'T', 1, +1);
insert into def_type (id, name, group_id, level, trtype) values ('STK_WB_O', 'Stok WayBill OUT', 'T', 1, -1);
insert into def_type (id, name, group_id, level, trtype) values ('STK_TT', 'Stok Transfer', 'T', 1, 0);

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

COMMIT;