package org.abacus.definition.core.persistance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.abacus.common.web.AbcUtility;
import org.abacus.definition.core.persistance.repository.DefLevelRepository;
import org.abacus.definition.shared.entity.DefLevelEntity;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("serial")
@Service
public class DefLevelDao implements Serializable {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private DefLevelRepository levelRepository;

	public void insertLevelEntity(DefValueEntity value){
		levelRepository.deleteLevel(value.getId());
		em.flush();
		DefValueEntity hierarchy = value; 
		List<DefLevelEntity> levelList = new ArrayList<DefLevelEntity>();
		for (Integer descOrder = 1; hierarchy.getParent()!=null ; descOrder++) {
			DefLevelEntity lvl = new DefLevelEntity();
			lvl.setType(value.getType());
			lvl.setValue(value);
			lvl.setParent(hierarchy);
			lvl.setLevel_desc(descOrder);
			levelList.add(lvl);
			hierarchy = hierarchy.getParent(); 
		}
		for (DefLevelEntity lvl : levelList) {
			lvl.setLevel_asc(1+levelList.size()-lvl.getLevel_desc());
			lvl.setId(AbcUtility.LPad(value.getId().toString(),10,'0')+"_"+AbcUtility.LPad(lvl.getLevel_asc().toString(),2,'0'));
			levelRepository.save(lvl);
		}
	}	

}
