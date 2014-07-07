package org.abacus.definition.core.persistance;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.abacus.definition.core.persistance.repository.DefLevelRepository;
import org.abacus.definition.core.persistance.repository.DefValueRepository;
import org.abacus.definition.shared.entity.DefValueEntity;
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
	private DefLevelDao levelDao;

	@Autowired
	private DefLevelRepository levelRepository;

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
				levelDao.insertLevelEntity(val);
			}
		} else {
			levelDao.insertLevelEntity(value);
		}
	}

	public List<DefValueEntity> getChildValueList(Long valueId){
		StringBuilder sb = new StringBuilder();
		sb.append("select v.* from def_value v, ");
		sb.append("	(with recursive r (id) as ( "); //Oracle'da recursive silinecek "with r (id)"
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

	public List<DefValueEntity> getParentValueList(Long valueId){
		//TODO:
		return null;
	}


}
