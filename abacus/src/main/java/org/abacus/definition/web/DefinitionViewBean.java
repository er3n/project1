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
import javax.faces.context.FacesContext;

import org.abacus.common.web.JsfMessageHelper;
import org.abacus.definition.core.handler.DefTypeHandler;
import org.abacus.definition.core.handler.DefValueHandler;
import org.abacus.definition.shared.constant.DefConstant;
import org.abacus.definition.shared.entity.DefTypeEntity;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DefinitionViewBean implements Serializable {

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

	@ManagedProperty(value = "#{defParamViewBean}")
	private DefParamViewBean defParamViewBean;

	private DefConstant.GroupEnum[] groupEnums;
	private DefConstant.GroupEnum selectedGroupEnum;
	
	public boolean renderGroupP;
	public boolean renderGroupS;
	public boolean renderGroupT;
	public boolean renderGroupV;

	@PostConstruct
	public void init() {
		groupEnums = DefConstant.GroupEnum.values();
		try{
			String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("type");
			selectedGroupEnum =  DefConstant.GroupEnum.valueOf(value.toUpperCase());
		}catch(Exception e){
			selectedGroupEnum = groupEnums[0];
		}
		groupChangeListener();
	}
	
	public void groupChangeListener(){
		this.findTypeList(selectedGroupEnum);
		selType=null;
		renderGroupP = selectedGroupEnum.equals(DefConstant.GroupEnum.P);//Param:--Static: working
		renderGroupS = selectedGroupEnum.equals(DefConstant.GroupEnum.S);//State:--Static: --
		renderGroupT = selectedGroupEnum.equals(DefConstant.GroupEnum.T);//Task :--Dynamc: --
		renderGroupV = selectedGroupEnum.equals(DefConstant.GroupEnum.V);//Value:--Dynamc: OK
		findValList(null);	
		
		defParamViewBean.setSelType(null);
	}

	public void typeRowSelectListener() {
		findValList(selType.getId());
		defParamViewBean.setSelType(selType);
	}

	public void saveOrUpdateType() {
		if (selType.isNew()) {
			jsfMessageHelper.addInfo("typeKayitIslemiBasarili");
		} else {
			jsfMessageHelper.addInfo("typeGuncellemeIslemiBasarili");
		}
		selType = defTypeService.saveTypeEntity(selType);
		findTypeList(selectedGroupEnum);
	}

	public void deleteType() {
		if (!selType.isNew()) {
			defTypeService.deleteTypeEntity(selType);
			jsfMessageHelper.addInfo("typeSilmeIslemiBasarili");
		}
		findTypeList(selectedGroupEnum);
	}

	public void saveOrUpdateVal() {
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
		clearVal();
		refreshTree();
	}

	public void onTabChange() {
		System.out.println("onTabChange");
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
			typeRowSelectListener();
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

	public void valueSelectListener(NodeSelectEvent event) {
		System.out.println("valueSelectListener:" + event.getTreeNode().toString());
		if (selNode == null || selNode.getData() == null) {
			selVal = null;
			clearVal();
		} else {
			selVal = (DefValueEntity) selNode.getData();
		}
	}

	public void clearVal() {
		DefValueEntity parentVal = new DefValueEntity(0L);
		if (selVal != null && !selVal.isNew()) {
			parentVal = selVal;
		}
		selVal = new DefValueEntity();
		selVal.setParent(parentVal);
		selVal.setType(selType);
	}

	public void findTypeList(DefConstant.GroupEnum groupEnum) {
		selType = null;
		typeList = null;
		typeList = defTypeService.getTypeList(groupEnum.name());
	}

	public void findValList(String typ) {
		selVal = null;
		clearVal();
		valList = null;
		if (typ!=null){
			valList = defValService.getValueList(typ);
		} else {
			valList = new ArrayList<>();
		}
		refreshTree();
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

	public DefConstant.GroupEnum[] getGroupEnums() {
		return groupEnums;
	}

	public void setGroupEnums(DefConstant.GroupEnum[] groupEnums) {
		this.groupEnums = groupEnums;
	}

	public DefConstant.GroupEnum getSelectedGroupEnum() {
		return selectedGroupEnum;
	}

	public void setSelectedGroupEnum(DefConstant.GroupEnum selectedGroupEnum) {
		this.selectedGroupEnum = selectedGroupEnum;
	}

	public boolean isRenderGroupP() {
		return renderGroupP;
	}

	public boolean isRenderGroupS() {
		return renderGroupS;
	}

	public boolean isRenderGroupT() {
		return renderGroupT;
	}

	public boolean isRenderGroupV() {
		return renderGroupV;
	}

	public DefParamViewBean getDefParamViewBean() {
		return defParamViewBean;
	}

	public void setDefParamViewBean(DefParamViewBean defParamViewBean) {
		this.defParamViewBean = defParamViewBean;
	}

}
