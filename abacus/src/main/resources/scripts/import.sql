INSERT INTO company (id, name, level, parent_id) VALUES ('01', 'HOLDING', 1, null);
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01', 'SIRKET', 2, '01');
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01.01', 'PROJE', 3, '01.01');

INSERT INTO sec_authority (id) VALUES ('AUTH_ADMIN');

INSERT INTO sec_user( id, is_active, password, company_id) VALUES ('admin', 1, 'e10adc3949ba59abbe56e057f20f883e', '01');

INSERT INTO sec_group (id, name, version) VALUES (nextval('seq_id'), 'Yonetici Grubu', 0);

INSERT INTO sec_group_authority (id, group_id, authority_id, version) VALUES (nextval('seq_id'), 1, 'AUTH_ADMIN', 0);

INSERT INTO sec_group_member (id, user_id, group_id, version) VALUES (nextval('seq_id'), 'admin', 1, 0);

COMMIT;

