INSERT INTO company (id, name, level, parent_id) VALUES ('01', 'HOLDING', 1, null);
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01', 'SIRKET', 2, '01');
INSERT INTO company (id, name, level, parent_id) VALUES ('01.01.01', 'PROJE', 3, '01.01');



INSERT INTO users (id, is_active, password, company_id) VALUES ('ADMIN', 1,'PASS', '01');

INSERT INTO role (id, version, name) VALUES (nextval('seq_id'), 0, 'ROLE_ADMIN');

INSERT INTO user_role (id, version, user_id, role_id) VALUES (nextval('seq_id'), 0, 'ADMIN', 1);

commit;

