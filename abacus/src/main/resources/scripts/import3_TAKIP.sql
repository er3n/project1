insert into org_organization (id, name, level_enum, parent_id) values ('00', 'Test İli', 				'L0', null);
insert into org_organization (id, name, level_enum, parent_id) values ('00.01', 'Test', 				'L1', '00');
insert into org_organization (id, name, level_enum, parent_id) values ('00.02', 'İlçe', 				'L1', '00');
insert into org_organization (id, name, level_enum, parent_id) values ('00.01.01', 'Test.Müd.',			'L2', '00.01');
insert into org_organization (id, name, level_enum, parent_id) values ('00.01.02', 'T.Okul1',			'L2', '00.01');
insert into org_organization (id, name, level_enum, parent_id) values ('00.01.03', 'T.Okul2',			'L2', '00.02');
insert into org_organization (id, name, level_enum, parent_id) values ('00.02.01', 'İlçe.Müd.',			'L2', '00.02');
insert into org_organization (id, name, level_enum, parent_id) values ('00.02.02', 'İ.Okul3',			'L2', '00.02');
insert into org_organization (id, name, level_enum, parent_id) values ('00.02.03', 'İ.Okul4',			'L2', '00.02');


insert into org_organization (id, name, level_enum, parent_id) values ('43', 'Kütahya İli', 			'L0', null);
insert into org_organization (id, name, level_enum, parent_id) values ('43.01', 'Kütahya', 				'L1', '43');
insert into org_organization (id, name, level_enum, parent_id) values ('43.11', 'Simav', 				'L1', '43');
insert into org_organization (id, name, level_enum, parent_id) values ('43.01.01', 'Kütah.Müd.', 		'L2', '43.01');
insert into org_organization (id, name, level_enum, parent_id) values ('43.11.01', 'Simav.Müd.', 		'L2', '43.11');
insert into org_organization (id, name, level_enum, parent_id) values ('43.11.02', 'Osmanbey', 			'L2', '43.11');
insert into org_organization (id, name, level_enum, parent_id) values ('43.11.03', '4Eylül', 			'L2', '43.11');

insert into org_organization (id, name, level_enum, parent_id) values ('72', 'Batman İli', 				'L0', null);
insert into org_organization (id, name, level_enum, parent_id) values ('72.01', 'Batman', 				'L1', '72');
insert into org_organization (id, name, level_enum, parent_id) values ('72.02', 'Gercüş', 				'L1', '72');
insert into org_organization (id, name, level_enum, parent_id) values ('72.03', 'Hasankeyf', 			'L1', '72');
insert into org_organization (id, name, level_enum, parent_id) values ('72.04', 'Beşiri', 				'L1', '72');
insert into org_organization (id, name, level_enum, parent_id) values ('72.05', 'Kozluk', 				'L1', '72');
insert into org_organization (id, name, level_enum, parent_id) values ('72.06', 'Sason', 				'L1', '72');
insert into org_organization (id, name, level_enum, parent_id) values ('72.01.01', 'Batman.Müd.', 		'L1', '72.01');
insert into org_organization (id, name, level_enum, parent_id) values ('72.02.01', 'Gercüş.Müd.', 		'L1', '72.03');
insert into org_organization (id, name, level_enum, parent_id) values ('72.03.01', 'Hasankeyf.Müd.', 	'L1', '72.04');
insert into org_organization (id, name, level_enum, parent_id) values ('72.04.01', 'Beşiri.Müd.', 		'L1', '72.02');
insert into org_organization (id, name, level_enum, parent_id) values ('72.05.01', 'Kozluk.Müd.', 		'L1', '72.05');
insert into org_organization (id, name, level_enum, parent_id) values ('72.06.01', 'Sason.Müd.', 		'L1', '72.06');



insert into sec_authority (id, code, name) values ('AUTH_ANY','0','Genel');
insert into sec_authority (id, code, name) values ('AUTH_0000','1','Sistem Yönetimi');
insert into sec_authority (id, code, name) values ('AUTH_0002','1.03','Okul EÖ.Yılı');
insert into sec_authority (id, code, name) values ('AUTH_0003','1.04','Ders Tipleri');
insert into sec_authority (id, code, name) values ('AUTH_0004','1.05','Kullanıcı Grupları');
insert into sec_authority (id, code, name) values ('AUTH_0005','1.06','Kullanıcı Tanımı');
insert into sec_authority (id, code, name) values ('AUTH_0006','1.07','Kodlama Tanımları');
insert into sec_authority (id, code, name) values ('AUTH_0051','1.12','Aktif Kullanıcılar');

insert into sec_authority (id, code, name) values ('AUTH_0011','2','Hesap Tanım');
insert into sec_authority (id, code, name) values ('AUTH_0017','2.06','Personel Tanımları');

insert into sec_authority (id, code, name) values ('AUTH_0019','3','Ders Planı');
insert into sec_authority (id, code, name) values ('AUTH_0022','3.03','Devam Takip');

insert into sec_authority (id, code, name) values ('AUTH_0040','7','Bütçe');
insert into sec_authority (id, code, name) values ('AUTH_0041','7.01','Tahmini Bütçe');

insert into sec_authority (id, code, name) values ('AUTH_0042','8','Raporlama');
insert into sec_authority (id, code, name) values ('AUTH_0047','8.05','Yazıcı Raporları');
insert into sec_authority (id, code, name) values ('AUTH_0050','8.08','Bütçe Pivot');

insert into sec_group (id, name, version) values (nextval('seq_id'), 'Zystem', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'IL Yönetim', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'ILCE Yönetim', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'OKUL Yönetim', 0);
insert into sec_group (id, name, version) values (nextval('seq_id'), 'PERSONEL', 0);

insert into sec_group_authority (id, group_id, authority_id, version) select nextval('seq_id'), 1, sa.id, 0 from sec_authority sa;

insert into sec_user( id, is_active, password, organization_root) values ('root', 	1, 'e977f5da70f10ce715d33d6b7c8d4e65', null);
insert into sec_user( id, is_active, password, organization_root) values ('admin', 	1, '01e529ff116bfb1dca9914fe9c45ea8f', null);
insert into sec_user( id, is_active, password, organization_root) values ('admin00', 1, '21232f297a57a5a743894a0e4a801fc3', '00');
insert into sec_user( id, is_active, password, organization_root) values ('admin43', 1, '21232f297a57a5a743894a0e4a801fc3', '43');
insert into sec_user( id, is_active, password, organization_root) values ('admin72', 1, '21232f297a57a5a743894a0e4a801fc3', '72');

insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'root', 	1, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin', 	1, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin00', 2, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin43', 2, 0);
insert into sec_user_group(id, user_id, group_id, version) values (nextval('seq_id'), 'admin72', 2, 0);


insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'root',	'00', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'root',	'43', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'root',	'72', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin',	'00', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin',	'43', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin',	'72', 0);

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin00','00', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin43','43', 0);
insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin72','72', 0);


insert into def_type (id, name, level, tr_state_type) values ('VAL_CATEGORY', 'Gruplama', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('VAL_IZIN', 'İzin Tipleri', 1, 0);
insert into def_type (id, name, level, tr_state_type) values ('VAL_RECEIPT', 'Ders Tipleri', 1, 0);

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

insert into def_type (id, name, level, tr_state_type) values ('ITM_CM_PE', 'Personel Hesap', 1, 0);

insert into def_type (id, name, level, tr_state_type) values ('PRM_STOCK', 'Stok Param', 1, 0);

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

insert into def_unit_group (id, organization_id, code, name, version) values (nextval('seq_id'), '00', 'SAY', 'Sayilan', 0);
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) values (nextval('seq_id'), currval('seq_id')-1, 'B0001', 'Adet', 1, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_IZIN', null, '02.01', 'Mazeret', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_IZIN', null, '02.02', 'Sağlık', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_IZIN', null, '02.03', 'İşKaz.MesHas', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_IZIN', null, '02.04', 'Özel Durum', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_IZIN', null, '02.05', 'Özürsüz', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_IZIN', null, '02.07', 'Ücretsiz İzin', 1, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_CATEGORY', null, 'GRP1', 'Grup1', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_CATEGORY', (select v.id from def_value v where v.type_id='VAL_CATEGORY' and v.code='GRP1'), 'A1', 'Alt1', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_CATEGORY', currval('seq_id')-1, 'D11', 'Detay1.1', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_CATEGORY', currval('seq_id')-2, 'D12', 'Detay1.2', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_CATEGORY', (select v.id from def_value v where v.type_id='VAL_CATEGORY' and v.code='GRP1'), 'A1', 'Alt2', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_CATEGORY', currval('seq_id')-1, 'D21', 'Detay2.1', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_CATEGORY', currval('seq_id')-2, 'D22', 'Detay2.2', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_CATEGORY', null, 'GRP2', 'Grup2', 1, 0);

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_RECEIPT', null, 'T0001', 'Normal Ders', 1, 0);
insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) values (nextval('seq_id'), null, 'VAL_RECEIPT', null, 'T0002', 'Ek Ders', 1, 0);


