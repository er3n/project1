package org.abacus.organization.core.handler;

import java.util.List;

import org.abacus.common.shared.AbcBusinessException;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.OrganizationDao;
import org.abacus.organization.core.persistance.repository.OrganizationRepository;
import org.abacus.organization.shared.ParentOrganizationNotFoundException;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.abacus.user.core.persistance.repository.UserOrganizationRepository;
import org.abacus.user.core.persistance.repository.UserRepository;
import org.abacus.user.shared.entity.SecUserEntity;
import org.abacus.user.shared.entity.SecUserOrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("organizationHandler")
public class OrganizationHandlerImpl implements OrganizationHandler {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private OrganizationDao organizationDao;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserOrganizationRepository userOrgRepo;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public OrganizationEntity findOne(String organizationId) {
		return organizationRepository.findOne(organizationId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<OrganizationEntity> findByOrganization(String organizationId) {
		return organizationRepository.findByOrganization(organizationId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public OrganizationEntity saveOrganizationEntity(OrganizationEntity entity)  throws AbcBusinessException {
		OrganizationEntity parent = organizationDao.findParentOrganization(entity);
		entity.setParent(parent);
		boolean newRootOrg = false;
		if (entity.getLevel().equals(EnumList.OrgOrganizationLevelEnum.L0)){
			newRootOrg = !organizationRepository.exists(entity.getId());
		}
		
		if (!entity.getLevel().equals(EnumList.OrgOrganizationLevelEnum.L0) && entity.getParent()==null){
			throw new ParentOrganizationNotFoundException();
		}
			
		entity = organizationRepository.save(entity);
		
		if (newRootOrg){
			SecUserEntity rootUser = new SecUserEntity();
			rootUser.setId("root");
			SecUserOrganizationEntity userOrg = new SecUserOrganizationEntity();
			userOrg.setUser(rootUser);
			userOrg.setOrganization(entity);
			userOrgRepo.save(userOrg);
		}
		
		return entity;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public void deleteOrganizationEntity(OrganizationEntity entity) {
		organizationRepository.delete(entity);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public OrganizationEntity findParentOrganization(OrganizationEntity child) {
		return organizationDao.findParentOrganization(child);
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<OrganizationEntity> findRootOrganization() {
		return organizationRepository.findRootOrganization();
	}

}
