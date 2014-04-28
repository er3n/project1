package org.abacus.persistence.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.definition.core.handler.DefTypeHandler;
import org.abacus.definition.core.handler.DefValueHandler;
import org.abacus.definition.shared.entity.DefTypeEntity;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class DefinitionBean implements Serializable {

	private DefValueEntity rootVal = new DefValueEntity(0L, "Root", "-");
	private TreeNode root = new DefaultTreeNode(rootVal, null);

	private DefTypeEntity selType;
	private List<DefTypeEntity> typeList;

	private DefValueEntity newVal;
	private DefValueEntity selVal;
	private TreeNode selValNode;
	private List<DefValueEntity> valList;

	@ManagedProperty(value = "#{defTypeHandler}")
	private DefTypeHandler defTypeService;

	@ManagedProperty(value = "#{defValueHandler}")
	private DefValueHandler defValService;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	private Long viewScopeId = 0L;

	public Long getViewScopeId() {
		return viewScopeId;
	}

	public void setViewScopeText(Long viewScopeId) {
		this.viewScopeId = viewScopeId;
	}

	@PostConstruct
	public void init() {
		System.out.println("init.viewScope.Type");
		this.findTypeList();
		if (typeList.size() > 0) {
			selType = typeList.get(0);
		} else {
			clearType();
		}
	}

	public void printViewScope() {
		if (viewScopeId == null) {
			viewScopeId = 0L;
		} else {
			viewScopeId++;
		}
		System.out.println("viewScopeText.Type:" + viewScopeId);
	}

	public void saveOrUpdateType() {
		if (selType.getId() == null || selType.getIsNew()) {
			jsfMessageHelper.addInfo("typeKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("typeGuncellemeIslemiBasarili");
		}
		defTypeService.saveOrUpdateEntity(selType);
		findTypeList();
	}

	public void deleteType() {
		if (selType.getId() != null && !selType.getIsNew()) {
			defTypeService.deleteEntity(selType);
			jsfMessageHelper.addInfo("typeSilmeIslemiBasarili");
		} 
		findTypeList();
	}

	public void saveOrUpdateVal() {
		if (newVal.getId() == null || newVal.getIsNew()) {
			newVal.setType(selType);
			newVal.setParent(selVal!=null?selVal:new DefValueEntity(0L));
			valList.add(0, newVal);
			jsfMessageHelper.addInfo("valueKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("valueGuncellemeIslemiBasarili");
		}
		defValService.saveOrUpdateEntity(newVal);
		selVal = null;
		clearVal();
		refreshTree();
	}

	public void onTabChange() {
		System.out.println("onTabChange");
	}

	public void setCurrentType() {
		findValList(selType.getId());
	}

	public void setCurrentVal() {
		newVal = selVal;
		if (newVal == null) {
			clearVal();
		}
	}

	public void setCurrentValNode() {
		if (selValNode == null || selValNode.getData() == null) {
			selVal = null;
			clearVal();
		} else {
			selVal = (DefValueEntity) selValNode.getData();
			setCurrentVal();
		}
	}

	public void addNewType() {
		boolean found = false;
		for (DefTypeEntity def : typeList) {
			if (def.getIsNew()) {
				selType = def;
				found = true;
			}
		}
		if (!found) {
			clearType();
		}
		setCurrentType();

	}

	public void clearType() {
		selType = new DefTypeEntity(true);
		selType.setId("?");
		if (typeList != null) {
			typeList.add(0, selType);
		}
		clearVal();
	}

	public void clearVal() {
		newVal = new DefValueEntity();
		newVal.setIsNew(true);
	}

	public void findTypeList() {
		selType = null;
		typeList = null;
		clearType();
		typeList = defTypeService.getTypeList();
	}

	public void findValList(String typ) {
		selVal = null;
		clearVal();
		valList = null;
		valList = defValService.getValueList(typ);
		refreshTree();
	}

	private void refreshTree() {
		root = null;
		root = new DefaultTreeNode(rootVal, null);

		Map<Long, TreeNode> valMap = new HashMap<Long, TreeNode>(1 << 8);// 1<<8=2^8=256
		for (DefValueEntity val : valList) {
			TreeNode createNode = new DefaultTreeNode(val, null);
			valMap.put(val.getId(), createNode);
		}
		for (DefValueEntity valDto : valList) {
			TreeNode selNode = valMap.get(valDto.getId());
			if (valDto.getParent().getId().equals(0L)) {
				root.getChildren().add(selNode);
			} else {
				TreeNode parNode = valMap.get(valDto.getParent().getId());
				parNode.getChildren().add(selNode);
			}
		}
	}

	public DefValueEntity getRootVal() {
		return rootVal;
	}

	public void setRootVal(DefValueEntity rootVal) {
		this.rootVal = rootVal;
	}

	public DefTypeEntity getSelType() {
		return selType;
	}

	public void setSelType(DefTypeEntity selType) {
		this.selType = selType;
	}

	public List<DefTypeEntity> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<DefTypeEntity> typeList) {
		this.typeList = typeList;
	}

	public DefValueEntity getNewVal() {
		return newVal;
	}

	public void setNewVal(DefValueEntity newVal) {
		this.newVal = newVal;
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

	public DefTypeHandler getDefTypeService() {
		return defTypeService;
	}

	public void setDefTypeService(DefTypeHandler defTypeService) {
		this.defTypeService = defTypeService;
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

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public void setViewScopeId(Long viewScopeId) {
		this.viewScopeId = viewScopeId;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void onNodeExpand(NodeExpandEvent event) {
		System.out.println("Expanded" + event.getTreeNode().toString());
	}

	public void onNodeCollapse(NodeCollapseEvent event) {
		System.out.println("Collapsed" + event.getTreeNode().toString());
	}

	public void onNodeSelect(NodeSelectEvent event) {
		System.out.println("Selected:" + event.getTreeNode().toString());
		setCurrentValNode();
	}

	public void onNodeUnselect(NodeUnselectEvent event) {
		System.out.println("Unselected:" + event.getTreeNode().toString());
	}

	public TreeNode getSelValNode() {
		return selValNode;
	}

	public void setSelValNode(TreeNode selValNode) {
		this.selValNode = selValNode;
	}
	
}
