package org.abacus.common.web.component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.abacus.common.web.SessionInfoHelper;
import org.abacus.definition.core.handler.DefValueHandler;
import org.abacus.definition.shared.constant.EnumList;
import org.abacus.definition.shared.entity.DefValueEntity;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class TreeSelectionViewBean implements Serializable {

	@ManagedProperty(value = "#{defValueHandler}")
	private DefValueHandler defValueHandler;
	
	@ManagedProperty(value = "#{sessionInfoHelper}")
	private SessionInfoHelper sessionInfoHelper;
	
	private Map<String, TreeNode> resultMap = new HashMap<>();
	
	public void init() {
	}

	private TreeNode refreshTree(List<DefValueEntity> valList) {
		DefValueEntity rootVal = new DefValueEntity();
		rootVal.setId(0L);
		TreeNode rootNode = new DefaultTreeNode(rootVal, null);

		Map<Long, TreeNode> valMap = new HashMap<Long, TreeNode>(1 << 8);// 1<<8=2^8=256
		for (DefValueEntity val : valList) {
			TreeNode createNode = new DefaultTreeNode(val, null);
			valMap.put(val.getId(), createNode);
		}
		for (DefValueEntity valDto : valList) {
			TreeNode selNode = valMap.get(valDto.getId());
			if (valDto.getParent()==null) {
				rootNode.getChildren().add(selNode);
			} else {
				TreeNode parNode = valMap.get(valDto.getParent().getId());
				if (parNode==null){
					parNode = rootNode;
				}
				parNode.getChildren().add(selNode);
			}
		}
		return rootNode;
	}
	
	public TreeNode getValueTree(EnumList.DefTypeEnum typeEnum, EnumList.DefTypeEnum itemEnum) {
		if (typeEnum==null){
			DefValueEntity rootVal = new DefValueEntity();
			rootVal.setId(0L);
			return new DefaultTreeNode(rootVal, null);
		}
		String key = typeEnum.name()+(itemEnum==null?"":itemEnum.getName());
		if (resultMap.containsKey(key)) {
			return resultMap.get(key);
		} else {
			List<DefValueEntity> list = defValueHandler.getValueList(sessionInfoHelper.currentRootOrganizationId(), typeEnum, itemEnum);
			TreeNode rootNode = refreshTree(list);
			resultMap.put(key, rootNode);
			return rootNode;
		}
	}

	public DefValueHandler getDefValueHandler() {
		return defValueHandler;
	}

	public void setDefValueHandler(DefValueHandler defValueHandler) {
		this.defValueHandler = defValueHandler;
	}

	public SessionInfoHelper getSessionInfoHelper() {
		return sessionInfoHelper;
	}

	public void setSessionInfoHelper(SessionInfoHelper sessionInfoHelper) {
		this.sessionInfoHelper = sessionInfoHelper;
	}

}