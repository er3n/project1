INSERT INTO "COMPANY" ("ID", "NAME", "LEVEL", parent_id) VALUES ('01', 'HOLDING', 1, null);
INSERT INTO "COMPANY" ("ID", "NAME", "LEVEL", parent_id) VALUES ('01.01', 'SIRKET', 1, '01');
INSERT INTO "COMPANY" ("ID", "NAME", "LEVEL", parent_id) VALUES ('01.01.01', 'PROJE', 1, '01.01');


INSERT INTO "ROLE" ("ID", "DATE_CREATED", "DATE_UPDATED", "USER_CREATED", "USER_UPDATED","VERSION", "NAME") VALUES (nextval('SEQ_ID'), current_timestamp, null, 'ADMIN', null, 0, 'ADMIN');

commit;
