package org.abacus.definition.core.persistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.abacus.common.web.AbcUtility;
import org.abacus.definition.core.persistance.repository.DefValueLevelRepository;
import org.abacus.definition.core.persistance.repository.DefValueRepository;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.abacus.definition.shared.entity.DefValueLevelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class DefValueDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private DefValueRepository valueRepository;

	@Autowired
	private DefValueLevelRepository levelRepository;


	public DefValueEntity saveValueEntity(DefValueEntity entity) {
		entity = valueRepository.save(entity);
		insertValueChildLevels(entity, false); 
		return entity;
	}

	public void deleteValueEntity(DefValueEntity entity) {
		levelRepository.deleteLevel(entity.getId());
		valueRepository.delete(entity);
	}
	
	private void insertValueChildLevels(DefValueEntity value, boolean isFullChildHierarchy){
		//False: Sadece Secili Value Levelleri Refresh Olur
		//True : Full Tree Level Insert, Node degistirme ozelligi olursa aktiflestirilecek
		em.flush();
		if (isFullChildHierarchy){
			List<DefValueEntity> resultList = getChildValueList(value.getId());
			for (DefValueEntity val : resultList) {
				insertLevelEntity(val);
			}
		} else {
			insertLevelEntity(value);
		}
	}

	public void refreshTypeLevel(String organizationId, EnumList.DefTypeEnum typeEnum){
		List<DefValueEntity> valueList = valueRepository.getValueList(organizationId, typeEnum.getName());
		for (DefValueEntity val : valueList) {
			insertLevelEntity(val);
		}
	}

	public void insertLevelEntity(DefValueEntity value){
		levelRepository.deleteLevel(value.getId());
		em.flush();
		DefValueEntity hierarchy = value; 
		List<DefValueLevelEntity> levelList = new ArrayList<DefValueLevelEntity>();
		for (Integer descOrder = 1; hierarchy!=null ; descOrder++) {
			DefValueLevelEntity lvl = new DefValueLevelEntity();
			lvl.setType(value.getType());
			lvl.setValue(value);
			lvl.setParent(hierarchy);
			lvl.setLevel_desc(descOrder);
			levelList.add(lvl);
			hierarchy = hierarchy.getParent(); 
		}
		for (DefValueLevelEntity lvl : levelList) {
			lvl.setLevel_asc(1+levelList.size()-lvl.getLevel_desc());
			lvl.setId(AbcUtility.LPad(value.getId().toString(),10,'0')+"_"+AbcUtility.LPad(lvl.getLevel_asc().toString(),2,'0'));
			levelRepository.save(lvl);
		}
	}	
	
	public List<DefValueEntity> getChildValueList(Long valueId){
		StringBuilder sb = new StringBuilder();
		sb.append("select v.* from def_value v, ");
		sb.append("	(with recursive r (id) as ( "); 
		sb.append("	select root.id from def_value root where root.id = :valueId ");
		sb.append("	union all ");
		sb.append("	select child.id from def_value child join r on child.parent_id =r.id ");
		sb.append(") select * from r) tree ");
		sb.append("where v.id = tree.id ");
		Query query = em.createNativeQuery(sb.toString(), DefValueEntity.class);
		query.setParameter("valueId", valueId);
		List<DefValueEntity> resultList = query.getResultList();
		return resultList;
	}
}