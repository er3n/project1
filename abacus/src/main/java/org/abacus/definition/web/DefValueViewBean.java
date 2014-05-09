package org.abacus.definition.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefValueHandler;
import org.abacus.definition.shared.entity.DefTypeEntity;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.abacus.organization.core.handler.OrganizationHandler;
import org.abacus.organization.shared.entity.OrganizationEntity;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefValueViewBean implements Serializable {

	private TreeNode rootNode = null;
	private DefValueEntity rootVal = null;

	private DefTypeEntity selType;

	private TreeNode selNode;
	private DefValueEntity selVal;
	private List<DefValueEntity> valList;

	@ManagedProperty(value = "#{defValueHandler}")
	private DefValueHandler defValService;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@ManagedProperty(value = "#{organizationHandler}")
	private OrganizationHandler organizationHandler;

	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;

	private OrganizationEntity rootOrganization;

	@PostConstruct
	public void init() {
		rootOrganization = sessionInfoHelper.currentRootOrganization();
		rootVal = new DefValueEntity();
		rootVal.setId(0L);
	}

	public void setSelType(DefTypeEntity selType) {
		this.selType = selType;
		this.selVal = null;
		clearValue();
		findTypeValue();
	}

	public void saveValue() {
		int idx = 0;
		if (selVal.isNew()) {
			jsfMessageHelper.addInfo("valueKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("valueGuncellemeIslemiBasarili");
			idx = valList.indexOf(selVal);
			valList.remove(selVal);
		}
		selVal = defValService.saveValueEntity(selVal);
		valList.add(idx, selVal);
		selVal = null;
		clearValue();
		refreshTree();
	}

	public void valueSelectListener(NodeSelectEvent event) {
		// System.out.println("valueSelectListener:" +
		// event.getTreeNode().toString());
		if (selNode == null || selNode.getData() == null) {
			selVal = null;
			clearValue();
		} else {
			selVal = (DefValueEntity) selNode.getData();
		}
	}

	public void clearValue() {
		DefValueEntity parentVal = new DefValueEntity();
		parentVal.setId(0L);
		if (selVal != null && !selVal.isNew()) {
			parentVal = selVal;
		}
		selVal = new DefValueEntity();
		selVal.setParent(parentVal);
		selVal.setType(selType);
		selVal.setOrganization(rootOrganization);
	}

	public void findTypeValue() {
		clearValue();
		valList = null;
		if (selType != null) {
			valList = defValService.getValueList(rootOrganization.getId(), selType.getId());
		} else {
			valList = new ArrayList<>();
		}
		refreshTree();
		// System.out.println(valList);
	}

	private void newRoot() {
		rootNode = null;
		rootNode = new DefaultTreeNode(rootVal, null);
	}

	private void refreshTree() {
		newRoot();

		Map<Long, TreeNode> valMap = new HashMap<Long, TreeNode>(1 << 8);// 1<<8=2^8=256
		for (DefValueEntity val : valList) {
			TreeNode createNode = new DefaultTreeNode(val, null);
			valMap.put(val.getId(), createNode);
		}
		for (DefValueEntity valDto : valList) {
			TreeNode selNode = valMap.get(valDto.getId());
			if (valDto.getParent().getId().equals(0L)) {
				rootNode.getChildren().add(selNode);
			} else {
				TreeNode parNode = valMap.get(valDto.getParent().getId());
				parNode.getChildren().add(selNode);
			}
		}
	}

	public DefTypeEntity getSelType() {
		return selType;
	}

	public DefValueEntity getSelVal() {
		return selVal;
	}

	public void setSelVal(DefValueEntity selVal) {
		this.selVal = selVal;
	}

	public List<DefValueEntity> getValList() {
		return valList;
	}

	public void setValList(List<DefValueEntity> valList) {
		this.valList = valList;
	}

	public DefValueHandler getDefValService() {
		return defValService;
	}

	public void setDefValService(DefValueHandler defValService) {
		this.defValService = defValService;
	}

	public JsfMessageHelper getJsfMessageHelper() {
		return jsfMessageHelper;
	}

	public void setJsfMessageHelper(JsfMessageHelper jsfMessageHelper) {
		this.jsfMessageHelper = jsfMessageHelper;
	}

	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}

	public TreeNode getSelNode() {
		return selNode;
	}

	public void setSelNode(TreeNode selNode) {
		this.selNode = selNode;
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

	public OrganizationEntity getRootOrganization() {
		return rootOrganization;
	}

	public void setRootOrganization(OrganizationEntity rootOrganization) {
		this.rootOrganization = rootOrganization;
	}

}
