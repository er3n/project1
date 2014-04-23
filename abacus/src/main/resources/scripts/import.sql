INSERT INTO company (id, name, level, parent_id) VALUES ('01', 'HOLDING', 1, null);
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01', 'SIRKET', 2, '01');
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01.01', 'PROJE', 3, '01.01');

INSERT INTO authority (id) VALUES ('AUTH_ADMIN');

INSERT INTO users (id, is_active, password, company_id) VALUES ('ADMIN', 1,'sifre123', '01');

INSERT INTO role (id, name, version) VALUES (nextval('seq_id'), 'Admin Rolü', 0);

INSERT INTO role_authority (id, role_id, authority_id, version) VALUES (nextval('seq_id'), 1, 'AUTH_ADMIN', 0);

INSERT INTO user_role (id, user_id, role_id, version) VALUES (nextval('seq_id'), 'ADMIN', 1, 0);

COMMIT;

