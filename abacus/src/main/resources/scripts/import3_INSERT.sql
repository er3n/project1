insert into org_organization (id, name, level_enum, parent_id) values ('00', 'Test Holdingi', 'L0', null);
insert into org_organization (id, name, level_enum, parent_id) values ('00.01', 'T01.Şirketi', 'L1', '00');
insert into org_organization (id, name, level_enum, parent_id) values ('00.01.01', 'T01.01.Projesi', 'L2', '00.01');
insert into org_organization (id, name, level_enum, parent_id) values ('00.01.02', 'T01.02.Projesi', 'L2', '00.01');
insert into org_organization (id, name, level_enum, parent_id) values ('00.02', 'T02.Şirketi', 'L1', '00');
insert into org_organization (id, name, level_enum, parent_id) values ('00.02.01', 'T02.01.Projesi', 'L2', '00.02');

insert into sec_authority (id, code, name) values ('AUTH_ANY','0','Genel');
insert into sec_authority (id, code, name) values ('AUTH_0000','1','Sistem Yönetimi');
insert into sec_authority (id, code, name) values ('AUTH_9999','1.01','Organizasyon-Holding');
insert into sec_authority (id, code, name) values ('AUTH_0001','1.02','Organizasyon-Departman');
insert into sec_authority (id, code, name) values ('AUTH_0002','1.03','Organizasyon-Dönem');
insert into sec_authority (id, code, name) values ('AUTH_0003','1.04','Proje Menu Bilgileri');
insert into sec_authority (id, code, name) values ('AUTH_0004','1.05','Kullanıcı Grupları');
insert into sec_authority (id, code, name) values ('AUTH_0005','1.06','Kullanıcı Tanımı');
insert into sec_authority (id, code, name) values ('AUTH_0006','1.07','Seçim Kod Tanımları');
insert into sec_authority (id, code, name) values ('AUTH_0007','1.08','Stok Hareket Tanımları');
insert into sec_authority (id, code, name) values ('AUTH_0008','1.09','Finans Hareket Tanımları');
insert into sec_authority (id, code, name) values ('AUTH_0009','1.10','Stok Birim Tanımları');
insert into sec_authority (id, code, name) values ('AUTH_0010','1.11','Parametre Tanımları');
insert into sec_authority (id, code, name) values ('AUTH_0051','1.12','Aktif Kullanıcılar');

insert into sec_authority (id, code, name) values ('AUTH_0011','2','Hesap Tanım');
insert into sec_authority (id, code, name) values ('AUTH_0012','2.01','Hesap Tipleri');
insert into sec_authority (id, code, name) values ('AUTH_0013','2.02','Gelir Hizmet Hesapları');
insert into sec_authority (id, code, name) values ('AUTH_0014','2.03','Gider Hizmet Hesapları');
insert into sec_authority (id, code, name) values ('AUTH_0015','2.04','Firma Hesapları');
insert into sec_authority (id, code, name) values ('AUTH_0016','2.05','Müşteri Hesapları');
insert into sec_authority (id, code, name) values ('AUTH_0017','2.06','Personel Hesapları');
insert into sec_authority (id, code, name) values ('AUTH_0018','2.07','Nakit Hesapları');

insert into sec_authority (id, code, name) values ('AUTH_0019','3','Menu Tanım');
insert into sec_authority (id, code, name) values ('AUTH_0020','3.01','Malzeme Tanımları');
insert into sec_authority (id, code, name) values ('AUTH_0021','3.02','Yemek/Ürün Tanımları');
insert into sec_authority (id, code, name) values ('AUTH_0022','3.03','Menu Tanımları');

insert into sec_authority (id, code, name) values ('AUTH_0023','4','Stok');
insert into sec_authority (id, code, name) values ('AUTH_0024','4.01','Alış İrsaliye');
insert into sec_authority (id, code, name) values ('AUTH_0025','4.02','Satış İrsaliye');
insert into sec_authority (id, code, name) values ('AUTH_0026','4.03','Depo İşlemleri');
insert into sec_authority (id, code, name) values ('AUTH_0027','4.04','Stok İstek Talebi');
insert into sec_authority (id, code, name) values ('AUTH_0028','4.05','Stok İstek Onayı');

insert into sec_authority (id, code, name) values ('AUTH_0029','5','Satınalma');
insert into sec_authority (id, code, name) values ('AUTH_0030','5.01','Satınalma İstek Talebi');
insert into sec_authority (id, code, name) values ('AUTH_0031','5.02','Satınalma Teklif Girişi');
insert into sec_authority (id, code, name) values ('AUTH_0032','5.03','Satınalma Onayı');

insert into sec_authority (id, code, name) values ('AUTH_0033','6','Finans');
insert into sec_authority (id, code, name) values ('AUTH_0034','6.01','Otomatik Fiş Oluştur');
insert into sec_authority (id, code, name) values ('AUTH_0035','6.02','Alış Fatura');
insert into sec_authority (id, code, name) values ('AUTH_0036','6.03','Satış Fatura');
insert into sec_authority (id, code, name) values ('AUTH_0037','6.04','Ödeme');
insert into sec_authority (id, code, name) values ('AUTH_0038','6.05','Tahsilat');
insert into sec_authority (id, code, name) values ('AUTH_0039','6.06','Mahsup');

insert into sec_authority (id, code, name) values ('AUTH_0040','7','Bütçe');
insert into sec_authority (id, code, name) values ('AUTH_0041','7.01','Tahmini Bütçe');

insert into sec_authority (id, code, name) values ('AUTH_0042','8','Raporlama');
insert into sec_authority (id, code, name) values ('AUTH_0043','8.01','Stok Durum Sorgula');
insert into sec_authority (id, code, name) values ('AUTH_0044','8.02','Stok Hareket Sorgula');
insert into sec_authority (id, code, name) values ('AUTH_0045','8.03','Hesap Durum Sorgula');
insert into sec_authority (id, code, name) values ('AUTH_0046','8.04','Hesap Hareket Sorgula');
insert into sec_authority (id, code, name) values ('AUTH_0047','8.05','Yazıcı Raporları');
insert into sec_authority (id, code, name) values ('AUTH_0048','8.06','Stok Pivot');
insert into sec_authority (id, code, name) values ('AUTH_0049','8.07','Finans Pivot');
insert into sec_authority (id, code, name) values ('AUTH_0050','8.08','Bütçe Pivot');

insert into sec_group (id, name, version) values (nextval('seq_id'), 'System Admin', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Satıcı Grubu', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Patron', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Şirket Yöneticisi', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Finans Yöneticisi', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Proje Yöneticisi', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'Depo Yöneticisi', 0);

 insert into sec_group_authority (id, group_id, authority_id, version) select nextval('seq_id'), 1, sa.id, 0 from sec_authority sa;

insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 2, 'AUTH_0029', 0);
insert into sec_group_authority (id, group_id, authority_id, version) values (nextval('seq_id'), 2, 'AUTH_0031', 0);

insert into sec_user( id, is_active, password) values ('admin', 1, 'e10adc3949ba59abbe56e057f20f883e');
insert into sec_user( id, is_active, password) values ('vendor1', 1, 'e10adc3949ba59abbe56e057f20f883e');
insert into sec_user( id, is_active, password) values ('vendor2', 1, 'e10adc3949ba59abbe56e057f20f883e');

insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin', 1, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'vendor1', 2, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'vendor2', 2, 0);

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','00', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','00.01', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','00.01.01', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','00.01.02', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','00.02', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','00.02.01', 0);

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','00.01', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','00.01.01', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','00.01.02', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','00.02', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor1','00.02.01', 0);

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','00.01', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','00.01.01', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','00.01.02', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','00.02', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'vendor2','00.02.01', 0);

insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '00.01', 'F', 'SY1','1.Şirket Ofisi',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '00.01', 'S', 'SD1_A','1.Şirket Depo A',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '00.01', 'S', 'SD1_B','1.Şirket Depo B',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '00.01', 'SP', 'SP1','1.Şirket Satınalma Depo',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '00.01.01', 'F', 'PY1.1','1.1.Proje Ofisi',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '00.01.01', 'S', 'PD1.1_X','1.1.Proje Depo X',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '00.01.01', 'S', 'PD1.1_Y','1.1.Proje Depo Y',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);
insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '00.01.01', 'SP', 'PP1.1','1.1.Proje Satınalma Depo',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);

insert into org_department (id, organization_id, group_enum, code, name, version) values (nextval('seq_id'), '00.02.01', 'S', 'PD2.1_X','2.1.Proje Depo X',0);
insert into sec_user_department (id, department_id, user_id, version, auth_input, auth_output) values (nextval('seq_id'), currval('seq_id')-1, 'admin', 0, 1, 1);

insert into def_type (id, name, level, tr_state_type) values ('VAL_CATEGORY', 'Grup/Kategori', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('VAL_RECEIPT', 'Yemek Tipleri', 1, 0);

insert into def_type (id, name, level, tr_state_type) values ('STK_WB_I', 'Alış İrsaliye', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('STK_WB_O', 'Satış İrsaliye', 1, -1);
insert into def_type (id, name, level, tr_state_type) values ('STK_WB_T', 'Proje Transfer', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_S', 'Stok Açılış', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_F', 'Stok Kapanış', 1, -1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_I', 'Stok Giriş', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_O', 'Stok Çıkış', 1, -1);
insert into def_type (id, name, level, tr_state_type) values ('STK_IO_T', 'Stok Transfer', 1, 0);

insert into def_type (id, name, level, tr_state_type) values ('REQ_IO_T', 'Stok Transfer İstek', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('REQ_IO_P', 'Satınalma İstek', 1, 0);

insert into def_type (id, name, level, tr_state_type) values ('FIN_B', 'Alış Fatura', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('FIN_S', 'Satış Fatura', 1, -1);
insert into def_type (id, name, level, tr_state_type) values ('FIN_P', 'Ödeme', 1, -1);
insert into def_type (id, name, level, tr_state_type) values ('FIN_R', 'Tahsilat', 1, +1);
insert into def_type (id, name, level, tr_state_type) values ('FIN_J', 'Mahsup', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('FIN_J_SC', 'Stok Maliyet', 1, -1);

insert into def_type (id, name, level, tr_state_type) values ('ITM_SR_ST', 'Stok Hesap', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_SR_FN', 'Hizmet Hesap', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_CM_CU', 'Müşteri Hesap', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_CM_VE', 'Satıcı Hesap', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_CM_PE', 'Personel Hesap', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('ITM_CS', 'Nakit Hesap', 1, 0);

insert into def_type (id, name, level, tr_state_type) values ('PRM_STOCK', 'Stok Param', 1, 0);

insert into def_param (id, type_id, code, name) values ('PRM_STOCK_COSTTYPE', 'PRM_STOCK', 'COSTTYPE', 'Stk Cost Type');

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'STK_WB_I', 'WB-I', 'Alış İrsaliye #', 1, 0, 'ITM_CM_VE', null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'STK_WB_O', 'WB-O', 'Satış İrsaliye #', 1, 0, 'ITM_CM_CU', null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'STK_IO_S', 'IO-S', 'Stok Açılış #', 1, 0, null, null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'STK_IO_I', 'IO-I', 'Stok Giriş #', 1, 0, null, null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'STK_IO_O', 'IO-O', 'Stok Çıkış #', 1, 0, null, null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'STK_IO_T', 'IO-T', 'Stok Transfer #', 1, 0, null, null);

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'REQ_IO_T', 'R-IO-T', 'Stok transfer isteği #', 1, 0, null, null);
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'REQ_IO_P', 'R-IO-P', 'Stok satınalma isteği #', 1, 0, null, null);

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'FIN_B', 'BL-1', 'Alış Fatura #', 1, 0, 'ITM_CM_VE', 'ITM_SR');
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'FIN_S', 'SL-1', 'Satış Fatura #', 1, 0, 'ITM_CM_CU', 'ITM_SR');

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'FIN_P', 'PY-1', 'Satıcıya Avans #', 1, 0, 'ITM_CS', 'ITM_CM_VE');
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'FIN_P', 'PY-2', 'Personel Maaş #', 1, 0, 'ITM_CS', 'ITM_CM_PE');
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'FIN_P', 'PY-3', 'Masraf Giriş #', 1, 0, 'ITM_CS', 'ITM_SR_FN');
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'FIN_P', 'PY-9', 'Fatura Ödeme #', 1, 0, 'ITM_CS', 'ITM____VE');

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'FIN_R', 'RC-1', 'Müşteriden Avans #', 1, 0, 'ITM_CS', 'ITM_CM_CU');
insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'FIN_R', 'RC-9', 'Fatura Tahsilat #', 1, 0, 'ITM_CS', 'ITM____CU');

insert into def_task (id, organization_id, type_id, code, name, is_active, version, item_type_document, item_type_detail) values (nextval('seq_id'), '00', 'FIN_J_SC', 'JSC-1', 'Stok Maliyet #', 1, 0, 'ITM_SR_ST', 'ITM_SR_ST');

insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '00', 'SAY', 'Sayilabilen', 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'B0001', 'Adet', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'B0005', 'Bağ', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-3, 'B0006', 'Kutu', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-4, 'B0007', 'Bardak', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-5, 'B0008', 'Poşet', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-6, 'B0011', 'Kâse', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-7, 'B0012', 'Poşet', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-8, 'B0013', 'Paket', 1, 0);
insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '00', 'AGR', 'Agirlik',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'B0002', 'Kg.', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'B0003', 'Gr.', 0.001, 0);
insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '00', 'HAC', 'Hacim',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'B0004', 'Lt. (litre)', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-2, 'B0009', 'Şişe - 50 ml.', 0.05, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-3, 'B0010', 'Şişe - 1 Lt.', 1, 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-4, 'B0014', 'Ml.', 0.001, 0);

insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '00', 'YMK', 'Yemek',0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'Y1', 'Porsiyon', 1, 0);

insert into def_param_answer (id, version, param_id, organization_id, answer) values (nextval('seq_id'), 0, 'PRM_STOCK_COSTTYPE', '00', 'FIFO');

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', null, 'STK', 'Stok Grupları', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', (select v.id from def_value v where v.type_id='VAL_CATEGORY' and v.code='STK' and organization_id='00'), 'STK-H', 'Hammadde', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-1, 'H0001', 'Meyve', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-2, 'H0002', 'Sebze', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-3, 'H0003', 'Et Ürünleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-4, 'H0004', 'Süt Ürünleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-5, 'H0005', 'Bakliyat', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', (select v.id from def_value v where v.type_id='VAL_CATEGORY' and v.code='STK' and organization_id='00'), 'STK-G', 'Yemekler', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-1, 'G0001', 'ÇORBA GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-2, 'G0002', 'ET GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-3, 'G0003', 'BAKLİYAT GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-4, 'G0004', 'SEBZE GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-5, 'G0005', 'ZEYTİNYAĞLI GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-6, 'G0006', 'PİLAV MAKARNA', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-7, 'G0007', 'BÖREKLER', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-8, 'G0008', 'HOŞAF KOMPOSTO GRUBU', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', (select v.id from def_value v where v.type_id='VAL_CATEGORY' and v.code='STK' and organization_id='00'), 'STK-S', 'Sarf Malzeme', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-1, 'S0001', 'Kırtasiye', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-2, 'S0002', 'Temizlik Malzemesi', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', null, 'FIN', 'Gelir/Gider Tipleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-1, 'F000', 'Gelirler', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-2, 'F100', 'Sabit Fatura Giderleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-3, 'F200', 'Personel Gideri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-4, 'F300', 'Ulaşım Giderleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-5, 'F400', 'Finansman Giderleri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-6, 'F500', 'Genel Giderler', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', null, 'CMP', 'Firmalar', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-1, 'T100', 'Satıcı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-2, 'T200', 'Müşteri', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', null, 'PER', 'Çalışanlar', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-1, 'P100', 'Personel', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-2, 'P200', 'Taşeron', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', null, 'CSH', 'Tahsilat/Ödeme', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', currval('seq_id')-1, 'C100', 'Para', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_CATEGORY', null, '?', 'Tanımsız', 1, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0001', 'Normal Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0002', 'Normal Kahvaltı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0003', 'Diyet Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0004', 'Diyet Kahvaltı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0005', 'Çocuk Diyet Yemek', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0006', 'Çocuk Diyet Kahvaltı', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0007', 'Ara Öğün', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0008', 'Düğün Yemeği', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0009', 'Sünnet Yemeği', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), '00', 'VAL_RECEIPT', null, 'T0010', 'Özel Yemek', 1, 0);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'M1', 'FIN_R', 'Kahvaltı', (select v.id from def_value v where v.code='F000' and organization_id='00'), '00', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'M2', 'FIN_R', 'Öğlen Ym', (select v.id from def_value v where v.code='F000' and organization_id='00'), '00', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'M3', 'FIN_R', 'Akşam Ym', (select v.id from def_value v where v.code='F000' and organization_id='00'), '00', 'ITM_SR_FN', null);

insert into org_fiscal_year(id, organization_id, name, date_start, date_finish, profit_rate) VALUES ('00.01:'||trim(to_char(nextval('seq_id'),'00000000')), '00.01', 'T01:2014', DATE '2014-01-01', DATE '2014-12-31',0);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '00.01:'||trim(to_char(currval('seq_id'),'00000000')), '01', DATE '2014-01-01', DATE '2014-04-30', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01:'||trim(to_char(currval('seq_id'),'00000000'))||':02', '00.01:'||trim(to_char(currval('seq_id'),'00000000')), '02', DATE '2014-05-01', DATE '2014-08-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01:'||trim(to_char(currval('seq_id'),'00000000'))||':03', '00.01:'||trim(to_char(currval('seq_id'),'00000000')), '03', DATE '2014-09-01', DATE '2014-12-31', 1, 1, 1);

insert into org_fiscal_year(id, organization_id, name, date_start, date_finish, profit_rate) VALUES ('00.01.01:'||trim(to_char(nextval('seq_id'),'00000000')), '00.01.01', 'T01.01:2014', DATE '2014-01-01', DATE '2014-12-31',0);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'01', DATE '2014-01-01', DATE '2014-01-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':02', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'02', DATE '2014-02-01', DATE '2014-02-28', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':03', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'03', DATE '2014-03-01', DATE '2014-03-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':04', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'04', DATE '2014-04-01', DATE '2014-04-30', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':05', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'05', DATE '2014-05-01', DATE '2014-05-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':06', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'06', DATE '2014-06-01', DATE '2014-06-30', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':07', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'07', DATE '2014-07-01', DATE '2014-07-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':08', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'08', DATE '2014-08-01', DATE '2014-08-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':09', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'09', DATE '2014-09-01', DATE '2014-09-30', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':10', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'10', DATE '2014-10-01', DATE '2014-10-31', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':11', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'11', DATE '2014-11-01', DATE '2014-11-30', 1, 1, 1);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.01:'||trim(to_char(currval('seq_id'),'00000000'))||':12', '00.01.01:'||trim(to_char(currval('seq_id'),'00000000')),'12', DATE '2014-12-01', DATE '2014-12-31', 1, 1, 1);


insert into cat_menu_info(id, version, fiscal_year_id, meal_id, count_prepare, count_total, unit_price) values (nextval('seq_id'), 0, '00.01.01:'||trim(to_char(currval('seq_id')-1,'00000000')), (select v.id from def_item v where v.type_id='ITM_SR_FN' and v.code='M1' and organization_id='00' and class_enum='FIN_R'), 150, 150000, 3.0);
insert into cat_menu_info(id, version, fiscal_year_id, meal_id, count_prepare, count_total, unit_price) values (nextval('seq_id'), 0, '00.01.01:'||trim(to_char(currval('seq_id')-2,'00000000')), (select v.id from def_item v where v.type_id='ITM_SR_FN' and v.code='M2' and organization_id='00' and class_enum='FIN_R'), 200, 200000, 5.0);
insert into cat_menu_info(id, version, fiscal_year_id, meal_id, count_prepare, count_total, unit_price) values (nextval('seq_id'), 0, '00.01.01:'||trim(to_char(currval('seq_id')-3,'00000000')), (select v.id from def_item v where v.type_id='ITM_SR_FN' and v.code='M3' and organization_id='00' and class_enum='FIN_R'), 175, 175000, 7.5);

insert into bud_document(id,version,fiscal_year_id,budget_note) values (nextval('seq_id'),0,'00.01.01:'||trim(to_char(currval('seq_id')-4,'00000000')), '2014 #.01.01 butcesi');
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -1,'00.01.01:'||trim(to_char(currval('seq_id')-4- 1,'00000000'))||':01', 'BUD_R','ESTIMATE',1510);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -2,'00.01.01:'||trim(to_char(currval('seq_id')-4- 2,'00000000'))||':02', 'BUD_R','ESTIMATE',750);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -3,'00.01.01:'||trim(to_char(currval('seq_id')-4- 3,'00000000'))||':03', 'BUD_R','ESTIMATE',4620);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -4,'00.01.01:'||trim(to_char(currval('seq_id')-4- 4,'00000000'))||':04', 'BUD_R','ESTIMATE',830);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -5,'00.01.01:'||trim(to_char(currval('seq_id')-4- 5,'00000000'))||':05', 'BUD_R','ESTIMATE',6660);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -6,'00.01.01:'||trim(to_char(currval('seq_id')-4- 6,'00000000'))||':06', 'BUD_R','ESTIMATE',490);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -7,'00.01.01:'||trim(to_char(currval('seq_id')-4- 7,'00000000'))||':07', 'BUD_R','ESTIMATE',1700);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -8,'00.01.01:'||trim(to_char(currval('seq_id')-4- 8,'00000000'))||':08', 'BUD_R','ESTIMATE',3680);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id') -9,'00.01.01:'||trim(to_char(currval('seq_id')-4- 9,'00000000'))||':09', 'BUD_R','ESTIMATE',870);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-10,'00.01.01:'||trim(to_char(currval('seq_id')-4-10,'00000000'))||':10', 'BUD_R','ESTIMATE',4300);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-11,'00.01.01:'||trim(to_char(currval('seq_id')-4-11,'00000000'))||':11', 'BUD_R','ESTIMATE',6460);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-12,'00.01.01:'||trim(to_char(currval('seq_id')-4-12,'00000000'))||':12', 'BUD_R','ESTIMATE',450);

insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-13,'00.01.01:'||trim(to_char(currval('seq_id')-16- 1,'00000000'))||':01', 'BUD_X','ESTIMATE',1505);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-14,'00.01.01:'||trim(to_char(currval('seq_id')-16- 2,'00000000'))||':02', 'BUD_X','ESTIMATE',760);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-15,'00.01.01:'||trim(to_char(currval('seq_id')-16- 3,'00000000'))||':03', 'BUD_X','ESTIMATE',590);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-16,'00.01.01:'||trim(to_char(currval('seq_id')-16- 4,'00000000'))||':04', 'BUD_X','ESTIMATE',800);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-17,'00.01.01:'||trim(to_char(currval('seq_id')-16- 5,'00000000'))||':05', 'BUD_X','ESTIMATE',1500);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-18,'00.01.01:'||trim(to_char(currval('seq_id')-16- 6,'00000000'))||':06', 'BUD_X','ESTIMATE',450);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-19,'00.01.01:'||trim(to_char(currval('seq_id')-16- 7,'00000000'))||':07', 'BUD_X','ESTIMATE',620);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-20,'00.01.01:'||trim(to_char(currval('seq_id')-16- 8,'00000000'))||':08', 'BUD_X','ESTIMATE',1600);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-21,'00.01.01:'||trim(to_char(currval('seq_id')-16- 9,'00000000'))||':09', 'BUD_X','ESTIMATE',570);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-22,'00.01.01:'||trim(to_char(currval('seq_id')-16-10,'00000000'))||':10', 'BUD_X','ESTIMATE',1280);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-23,'00.01.01:'||trim(to_char(currval('seq_id')-16-11,'00000000'))||':11', 'BUD_X','ESTIMATE',415);
insert into bud_detail(id,version,document_id,fiscal_period_id,budget_rx,budget_type,budget_amount) values (nextval('seq_id'),0,currval('seq_id')-24,'00.01.01:'||trim(to_char(currval('seq_id')-16-12,'00000000'))||':12', 'BUD_X','ESTIMATE',1400);

insert into org_fiscal_year(id, organization_id, name, date_start, date_finish, profit_rate) VALUES ('00.01.02:'||trim(to_char(nextval('seq_id'),'00000000')), '00.01.02', 'T01.02:2014', DATE '2014-01-01', DATE '2014-12-31',0);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.01.02:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '00.01.02:'||trim(to_char(currval('seq_id'),'00000000')), 1, DATE '2014-01-01', DATE '2014-12-31', 1, 1, 1);

insert into org_fiscal_year(id, organization_id, name, date_start, date_finish, profit_rate) VALUES ('00.02:'||trim(to_char(nextval('seq_id'),'00000000')), '00.02', 'T02:2014', DATE '2014-01-01', DATE '2014-12-31',0);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.02:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '00.02:'||trim(to_char(currval('seq_id'),'00000000')), 1, DATE '2014-01-01', DATE '2014-12-31', 1, 1, 1);

insert into org_fiscal_year(id, organization_id, name, date_start, date_finish, profit_rate) VALUES ('00.02.01:'||trim(to_char(nextval('seq_id'),'00000000')), '00.02.01', 'T02.01:2014', DATE '2014-01-01', DATE '2014-12-31',0);
insert into org_fiscal_period(id, fiscal_year_id, period_no, date_start, date_finish, is_acc_active, is_fin_active, is_stk_active) VALUES ('00.02.01:'||trim(to_char(currval('seq_id'),'00000000'))||':01', '00.02.01:'||trim(to_char(currval('seq_id'),'00000000')), 1, DATE '2014-01-01', DATE '2014-12-31', 1, 1, 1);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'P101', null, 'Personel X', (select v.id from def_value v where v.code='P100' and organization_id='00'), '00', 'ITM_CM_PE', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'C101', null, 'Nakit', (select v.id from def_value v where v.code='C100' and organization_id='00'), '00', 'ITM_CS', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'C102', null, 'Banka', (select v.id from def_value v where v.code='C100' and organization_id='00'), '00', 'ITM_CS', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'T101', null, 'TazeGevrek Gıda', (select v.id from def_value v where v.code='T100' and organization_id='00'), '00', 'ITM_CM_VE', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'T102', null, 'Dört mevsim manav', (select v.id from def_value v where v.code='T100' and organization_id='00'), '00', 'ITM_CM_VE', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'T103', null, 'Bizim kasap', (select v.id from def_value v where v.code='T100' and organization_id='00'), '00', 'ITM_CM_VE', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'M101', null, 'Müşteri X', (select v.id from def_value v where v.code='T200' and organization_id='00'), '00', 'ITM_CM_CU', null);

insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F101', 'FIN_X', 'Elektrik', (select v.id from def_value v where v.code='F100' and organization_id='00'), '00', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F102', 'FIN_X', 'Su', (select v.id from def_value v where v.code='F100' and organization_id='00'), '00', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F103', 'FIN_X', 'Telefon', (select v.id from def_value v where v.code='F100' and organization_id='00'), '00', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F104', 'FIN_X', 'Gaz', (select v.id from def_value v where v.code='F100' and organization_id='00'), '00', 'ITM_SR_FN', null);
insert into def_item (id, version, is_active, code, class_enum, name, category_id, organization_id, type_id, unit_group_id) values (nextval('seq_id'), 0, 1,  'F201', 'FIN_X', 'Personel Maaş', (select v.id from def_value v where v.code='F200' and organization_id='00'), '00', 'ITM_SR_FN', null);

insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'00','ITM_SR_ST','VAL_CATEGORY',(select p.id from def_value p where p.code = 'STK' and p.organization_id = '00' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'00','ITM_SR_FN','VAL_CATEGORY',(select p.id from def_value p where p.code = 'FIN' and p.organization_id = '00' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'00','ITM_CM_VE','VAL_CATEGORY',(select p.id from def_value p where p.code = 'CMP' and p.organization_id = '00' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'00','ITM_CM_CU','VAL_CATEGORY',(select p.id from def_value p where p.code = 'CMP' and p.organization_id = '00' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'00','ITM_CM_PE','VAL_CATEGORY',(select p.id from def_value p where p.code = 'PER' and p.organization_id = '00' and p.type_id ='VAL_CATEGORY'));
insert into def_reference(id,version,organization_id,type_id,ref_type_id,ref_value_id) values (nextval('seq_id'),0,'00','ITM_CS','VAL_CATEGORY',(select p.id from def_value p where p.code = 'CSH' and p.organization_id = '00' and p.type_id ='VAL_CATEGORY'));

update sec_user s set vendor_id = (select t.id from def_item t where t.code = 'T101' and t.organization_id='00' and type_id='ITM_CM_VE') where s.id = 'vendor1';
update sec_user s set vendor_id = (select t.id from def_item t where t.code = 'T102' and t.organization_id='00' and type_id='ITM_CM_VE') where s.id = 'vendor2';
