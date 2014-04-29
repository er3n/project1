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
import org.abacus.definition.shared.constant.DefConstant;
import org.abacus.definition.shared.entity.DefTypeEntity;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefinitionBean implements Serializable {

	private TreeNode rootNode = null;
	private DefValueEntity rootVal = new DefValueEntity(0L, ".", ".");

	private DefTypeEntity selType;
	private List<DefTypeEntity> typeList;

	private TreeNode selNode;
	private DefValueEntity selVal;
	private List<DefValueEntity> valList;

	@ManagedProperty(value = "#{defTypeHandler}")
	private DefTypeHandler defTypeService;

	@ManagedProperty(value = "#{defValueHandler}")
	private DefValueHandler defValService;

	@ManagedProperty(value = "#{jsfMessageHelper}")
	private JsfMessageHelper jsfMessageHelper;

	@PostConstruct
	public void init() {
		getNewRoot();
		System.out.println("init.viewScope.Type");
		this.findTypeList();
		if (typeList.size() > 0) {
			selType = typeList.get(0);
		} else {
			clearType();
		}
	}

	private void getNewRoot(){
		rootNode = null;
		rootNode = new DefaultTreeNode(rootVal, null);
	}
	
	public void saveOrUpdateType() {
		if (selType.isNew()) {
			jsfMessageHelper.addInfo("typeKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("typeGuncellemeIslemiBasarili");
		}
		defTypeService.saveOrUpdateEntity(selType);
		findTypeList();
	}

	public void deleteType() {
		if (!selType.isNew()) {
			defTypeService.deleteEntity(selType);
			jsfMessageHelper.addInfo("typeSilmeIslemiBasarili");
		} 
		findTypeList();
	}

	public void saveOrUpdateVal() {
		if (selVal.isNew()) {
			valList.add(0, selVal);
			jsfMessageHelper.addInfo("valueKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("valueGuncellemeIslemiBasarili");
		}
		defValService.saveOrUpdateEntity(selVal);
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

	public void addNewType() {
		boolean found = false;
		for (DefTypeEntity def : typeList) {
			if (def.isNew()) {
				selType = def;
				found = true;
				break;
			}
		}
		if (found) {
			setCurrentType();
		} else {
			clearType();
		}

	}

	public void clearType() {
		selType = new DefTypeEntity();
		if (typeList != null) {
			typeList.add(0, selType);
		}
		clearVal();
	}
	
	public void setCurrentValNode() {
		if (selNode == null || selNode.getData() == null) {
			selVal = null;
			clearVal();
		} else {
			selVal = (DefValueEntity) selNode.getData();
		}
	}

	public void clearVal() {
		DefValueEntity parentVal = new DefValueEntity(0L);
		if (selVal!=null && !selVal.isNew()){
			parentVal = selVal;
		}
		selVal = new DefValueEntity();
		selVal.setParent(parentVal);
		selVal.setType(selType);
	}

	public void findTypeList() {
		selType = null;
		typeList = null;
		clearType();
		typeList = defTypeService.getTypeList(DefConstant.GroupEnum.V.name());
	}

	public void findValList(String typ) {
		selVal = null;
		clearVal();
		valList = null;
		valList = defValService.getValueList(typ);
		refreshTree();
	}

	private void refreshTree() {
		getNewRoot();
		
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

	public void setSelType(DefTypeEntity selType) {
		this.selType = selType;
	}

	public List<DefTypeEntity> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<DefTypeEntity> typeList) {
		this.typeList = typeList;
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

}
