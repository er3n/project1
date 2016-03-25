update org_organization set customer_id = (select id from def_item where organization_id='00' and type_id ='ITM_CM_CU' and code ='M101') where id ='00.#1.#1';

insert into def_item_unit(id, version, item_id, unit_code_id) select nextval('seq_id'), 0, id, (select c.id from def_unit_code c where c.code ='Y1') from def_item where organization_id='00' and type_id ='ITM_SR_ST' and class_enum='STK_P';

insert into org_organization (id, name, level_enum, parent_id) select replace(id,'00','01') id, replace(name,'00','01') as name, level_enum, replace(parent_id,'00','01') parent_id from org_organization where id like '#%';
insert into org_department (id, organization_id, group_enum, code, name, version) select nextval('seq_id') id, replace(organization_id,'00','01') organization_id, group_enum, code, name, version from org_department where organization_id like '#%';

insert into sec_user_organization (id, user_id, organization_id, version) select nextval('seq_id'), 'root', replace(organization_id,'00','01'), 0 from sec_user_organization where  organization_id like '#%' and user_id = 'root';
insert into def_task (id, organization_id, type_id, code, name, is_active, version) select nextval('seq_id') id, '01' organization_id, type_id, code, replace(name,'00','01'), is_active, version from def_task where organization_id = '00';

insert into def_value (id, organization_id, type_id, parent_id, code, name, is_active, version) select nextval('seq_id') id, '01' organization_id, type_id, parent_id, code, name, is_active, version from def_value where organization_id = '00' and id>0;
update def_value v set parent_id = (select p.id from def_value p where p.organization_id = v.organization_id and p.type_id = v.type_id and p.code = (select x.code from def_value x where x.id = v.parent_id)) where v.organization_id = '01' and v.parent_id > 0;

insert into def_unit_group (id, organization_id, code, name, version) select nextval('seq_id'), '01' organization_id, code, name , version from def_unit_group where organization_id='00';
insert into def_unit_code (id, unit_group_id, code, name, ratio, version) select nextval('seq_id') id, (select g.id from def_unit_group g where g.organization_id = '01' and g.code= (select x.code from def_unit_group x where x.id = y.unit_group_id)) unit_group_id, code, name, ratio, version from  def_unit_code y where unit_group_id in (select id from def_unit_group where organization_id='00');

insert into def_item (id, organization_id, version, is_active, code, class_enum, name, category_id, type_id, unit_group_id) select nextval('seq_id') id, '01' organization_id, version, is_active, code, class_enum, name, category_id, type_id, unit_group_id from def_item where organization_id = '00';
update def_item i set unit_group_id = (select id from def_unit_group where organization_id='01' and code = (select code from def_unit_group z where z.id=i.unit_group_id)) where organization_id='01';

create table z_def_item_product as select nextval('seq_id') id, version, material_count, item_id, material_item_id, unit_code_id,material_order from def_item_product where item_id in (select id from def_item where organization_id = '00');
update z_def_item_product z set item_id = (select id from def_item where organization_id='01' and code = (select code from def_item i where i.id=z.item_id));
update z_def_item_product z set material_item_id = (select id from def_item where organization_id='01' and code = (select code from def_item i where i.id=z.material_item_id));
insert into def_item_product(id, version, material_count, item_id, material_item_id, unit_code_id,material_order) select id, version, material_count, item_id, material_item_id, unit_code_id,material_order from z_def_item_product;
drop table z_def_item_product;

create table z_def_item_unit as select nextval('seq_id') id, version,item_id,unit_code_id from def_item_unit where item_id in (select id from def_item where organization_id = '00');
update z_def_item_unit z set item_id = (select id from def_item where organization_id='01' and code = (select code from def_item i where i.id=z.item_id));
update z_def_item_unit z set unit_code_id = (select id from def_unit_code where unit_group_id in (select id from def_unit_group where organization_id = '01') and code = (select code from def_unit_code i where i.id=z.unit_code_id));
insert into def_item_unit(id, version,item_id,unit_code_id) select id, version,item_id,unit_code_id from z_def_item_unit;
drop table z_def_item_unit;