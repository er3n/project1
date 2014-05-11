commit;

insert into org_organization (id, name, level_enum, parent_id) select replace(id,'#','01') id, replace(name,'#','01') as name, level_enum, replace(parent_id,'#','01') parent_id from org_organization where id like '#%';
insert into org_department (id, organization_id, group_enum, code, name, version) select nextval('seq_id') id, replace(organization_id,'#','01') organization_id, group_enum, code, name, version from org_department where organization_id like '#%';

insert into sec_user_organization (id, user_id, organization_id, version) values (nextval('seq_id'), 'admin','01', 0);
insert into def_task (id, organization_id, type_id, code, name, is_active, version) select nextval('seq_id') id, '01' organization_id, type_id, code, replace(name,'#','01'), is_active, version from def_task where organization_id = '#';

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) select nextval('seq_id') id, '01' organization_id, type_id, parent_id, code, name, is_active, version from def_value where organization_id = '#' and id>0;
update def_value v set parent_id = (select p.id from def_value p where p.organization_id = v.organization_id and p.type_id = v.type_id and p.code = (select x.code from def_value x where x.id = v.parent_id)) where v.organization_id = '01' and v.parent_id > 0;

insert into def_unit_group (id, organization_id, code, name, version) select nextval('seq_id'), '01' organization_id, code, name , version from def_unit_group where organization_id='#';
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) select nextval('seq_id') id, (select g.id from def_unit_group g where g.organization_id = '01' and g.code= (select x.code from def_unit_group x where x.id = y.unit_group_id)) unit_group_id, code, name, ratio, version from  def_unit_code y where unit_group_id in (select id from def_unit_group where organization_id='#');

insert into def_unit_code (id, unit_group_id, code, name, ratio, version) select nextval('seq_id') id, (select g.id from def_unit_group g where g.organization_id = '01' and g.code= (select x.code from def_unit_group x where x.id = y.unit_group_id)) unit_group_id, code, name, ratio, version from  def_unit_code y where unit_group_id in (select id from def_unit_group where organization_id='#');

commit;
