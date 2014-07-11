package org.abacus.definition.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefReferenceHandler;
import org.abacus.definition.shared.entity.DefReferenceEntity;
import org.abacus.definition.shared.entity.DefTypeEntity;
import org.abacus.organization.core.handler.OrganizationHandler;
import org.abacus.organization.shared.entity.OrganizationEntity;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefReferenceViewBean implements Serializable {

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{defReferenceHandler}")
	private DefReferenceHandler defReferenceService;

	@ManagedProperty(value = "#{organizationHandler}")
	private OrganizationHandler organizationHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private DefTypeEntity selType;

	private DefReferenceEntity selReference;
	private List<DefReferenceEntity> referenceList;

	private OrganizationEntity rootOrganization;

	@PostConstruct
	public void init() {
		rootOrganization = sessionInfoHelper.currentRootOrganization();
	}

	public void setSelType(DefTypeEntity selType) {
		this.selType = selType;
		findTypeReference();
	}

	public void referenceRowSelectListener() {
	}

	public void saveReference() {
		if (selReference.isNew()) {
			jsfMessageHelper.addInfo("createSuccessful","Referans");
		} else {
			jsfMessageHelper.addInfo("updateSuccessful","Referans");
		}
		defReferenceService.saveReferenceEntity(selReference);
		findTypeReference();
	}

	public void deleteReference() {
		if (!selReference.isNew()) {
			defReferenceService.deleteReferenceEntity(selReference);
			jsfMessageHelper.addInfo("deleteSuccessful","Referans");
		}
		findTypeReference();
	}

	public void createReference() {
		selReference = new DefReferenceEntity();
		selReference.setType(selType);
		selReference.setOrganization(rootOrganization);
	}

	public void findTypeReference() {
		createReference();
		referenceList = null;
		if (selType != null) {
			String org = sessionInfoHelper.currentOrganizationId();
			referenceList = defReferenceService.getReferenceList(rootOrganization.getId(), selType.getTypeEnum());
		} else {
			referenceList = new ArrayList<DefReferenceEntity>();
		}
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public DefReferenceEntity getSelReference() {
		return selReference;
	}

	public void setSelReference(DefReferenceEntity selReference) {
		if (selReference != null) {
			this.selReference = selReference;
		}
	}

	public DefReferenceHandler getDefReferenceService() {
		return defReferenceService;
	}

	public void setDefReferenceService(DefReferenceHandler defReferenceService) {
		this.defReferenceService = defReferenceService;
	}

	public List<DefReferenceEntity> getReferenceList() {
		return referenceList;
	}

	public void setReferenceList(List<DefReferenceEntity> referenceList) {
		this.referenceList = referenceList;
	}

	public OrganizationHandler getOrganizationHandler() {
		return organizationHandler;
	}

	public void setOrganizationHandler(OrganizationHandler organizationHandler) {
		this.organizationHandler = organizationHandler;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

}
