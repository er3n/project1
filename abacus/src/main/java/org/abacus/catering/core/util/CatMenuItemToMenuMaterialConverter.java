package org.abacus.catering.core.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.catering.shared.entity.CatMenuItemEntity;
import org.abacus.catering.shared.holder.MenuMaterialHolder;
import org.abacus.definition.core.persistance.repository.DefItemProductRepository;
import org.abacus.definition.shared.entity.DefItemProductEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class CatMenuItemToMenuMaterialConverter {
	
	@Autowired
	private DefItemProductRepository itemProductRepository;
	
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Collection<MenuMaterialHolder> convert(CatMenuEntity menu,Set<CatMenuItemEntity> menuItemSet){
		
		
		
		BigDecimal countSpend = menu.getCountSpend();
		
		List<MenuMaterialHolder> menuMetarialSet = new ArrayList<>();
		for(CatMenuItemEntity menuItem : menuItemSet){
			
			Set<DefItemProductEntity> regends = itemProductRepository.findItemProducts(menuItem.getItem().getId());
			if(CollectionUtils.isEmpty(regends)){
				
				putMaterial(menuMetarialSet, menuItem,countSpend);
				
			}else{
				
				for(DefItemProductEntity regend : regends){
					putMaterial(menuMetarialSet, regend, countSpend);
				}	
				
			}	
		}
		
		return menuMetarialSet;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private void putMaterial(List<MenuMaterialHolder> menuMetarialList, DefItemProductEntity regend, BigDecimal countSpend) {
		MenuMaterialHolder holder = new MenuMaterialHolder();
		holder.setItem(regend.getMaterialItem());
		holder.setUnit(regend.getMaterialUnitCode());
		countSpend = countSpend.multiply(regend.getMaterialCount());
		holder.setCountSpend(countSpend);
		menuMetarialList.add(holder);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private void putMaterial(List<MenuMaterialHolder> menuMetarialList, CatMenuItemEntity menuItem, BigDecimal countSpend) {
		MenuMaterialHolder holder = new MenuMaterialHolder();
		holder.setItem(menuItem.getItem());
		holder.setUnit(menuItem.getUnit());
		holder.setCountSpend(countSpend);
		menuMetarialList.add(holder);
	}


}
