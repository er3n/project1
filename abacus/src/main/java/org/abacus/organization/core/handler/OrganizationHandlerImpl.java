package org.abacus.organization.core.handler;

import java.util.List;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.organization.core.persistance.OrganizationDao;
import org.abacus.organization.core.persistance.repository.OrganizationRepository;
import org.abacus.organization.shared.entity.OrganizationEntity;
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
	private SessionInfoHelper sessionInfoHelper;		

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public OrganizationEntity findOne(String organizationId) {
		return organizationRepository.findOne(organizationId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly=true)
	public List<OrganizationEntity> findByOrganization(String organization) {
		return organizationRepository.findByOrganization(organization);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly=false)
	public OrganizationEntity saveOrganizationEntity(OrganizationEntity entity) {
		OrganizationEntity parent = organizationDao.findParentOrganization(entity);
		entity.setParent(parent);
		return organizationRepository.save(entity);
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
	

}
