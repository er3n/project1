INSERT INTO company (id, name, level, parent_id) VALUES ('01', 'HOLDING', 1, null);
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01', 'SIRKET', 2, '01');
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01.01', 'PROJE', 3, '01.01');

INSERT INTO sec_authority (id) VALUES ('KULLANICI_ISLEMLERI');
INSERT INTO sec_authority (id) VALUES ('KULLANICI_GRUP_ISLEMLERI');
INSERT INTO sec_authority (id) VALUES ('TANIMLAMA_ISLEMLERI');


INSERT INTO sec_user( id, is_active, password, company_id) VALUES ('admin', 1, 'e10adc3949ba59abbe56e057f20f883e', '01');

INSERT INTO sec_group (id, name, version) VALUES (nextval('seq_id'), 'Sistem Yoneticisi', 0);
INSERT INTO sec_group (id, name, version) VALUES (nextval('seq_id'), 'Holding Yoneticisi', 0);
INSERT INTO sec_group (id, name, version) VALUES (nextval('seq_id'), 'Sirket Yoneticisi', 0);
INSERT INTO sec_group (id, name, version) VALUES (nextval('seq_id'), 'Proje Yoneticisi', 0);
 
INSERT INTO sec_group_authority (id, group_id, authority_id, version) VALUES (nextval('seq_id'), 1, 'KULLANICI_ISLEMLERI', 0);
INSERT INTO sec_group_authority (id, group_id, authority_id, version) VALUES (nextval('seq_id'), 1, 'KULLANICI_GRUP_ISLEMLERI', 0);
INSERT INTO sec_group_authority (id, group_id, authority_id, version) VALUES (nextval('seq_id'), 1, 'TANIMLAMA_ISLEMLERI', 0);
 
INSERT INTO sec_group_member (id, user_id, group_id, version) VALUES (nextval('seq_id'), 'admin', 1, 0);

insert into def_type (id, name) values ('.', 'ROOTTYPE');
insert into def_type (id, name) values ('SEHIR', 'Sehir');
insert into def_type (id, name) values ('TEST', 'Test');

insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (0, '.', null, 'ROOTVALUE', 'ROOTVALUE', 0, 0);
insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), 'SEHIR', 0, '01', 'Ankara', 1, 0);
insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), 'SEHIR', currval('seq_id')-1, '01.01', 'Cankaya', 1, 0);
insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), 'SEHIR', 0, '34', 'Istanbul', 1, 0);
insert into def_value (id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), 'SEHIR', currval('seq_id')-1, '34.01', 'Fatih', 1, 0);

COMMIT;