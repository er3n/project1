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
import org.abacus.definition.core.persistance.repository.DefItemRepository;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefItemProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class CatMenuItemToMenuMaterialConverter {
	
	@Autowired
	private DefItemProductRepository itemProductRepository;
	
	@Autowired
	private DefItemRepository itemRepository;
	
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Collection<MenuMaterialHolder> convert(CatMenuEntity menu,Set<CatMenuItemEntity> menuItemSet){
		
		
		
		BigDecimal countSpend = menu.getCountSpend();
		
		List<MenuMaterialHolder> menuMetarialSet = new ArrayList<>();
		for(CatMenuItemEntity menuItem : menuItemSet){
			
			Set<DefItemProductEntity> regends = itemProductRepository.findItemProducts(menuItem.getItem().getId());
			if(CollectionUtils.isEmpty(regends)){
				//malzeme
				putMaterial(menuMetarialSet, menuItem, menuItem.getUnitItemCount(), countSpend);
				
			}else{
				//urunun icerigindeki malzemeler
				for(DefItemProductEntity regend : regends){
					putMaterial(menuMetarialSet, regend, menuItem.getUnitItemCount(), countSpend);
				}	
				
			}	
		}
		
		return menuMetarialSet;
	}

	//urunun icerigindeki malzemeler
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private void putMaterial(List<MenuMaterialHolder> menuMetarialList, DefItemProductEntity regend, BigDecimal unitItemCount, BigDecimal countSpend) {
		MenuMaterialHolder holder = new MenuMaterialHolder();
		DefItemEntity item = itemRepository.findWithFetch(regend.getMaterialItem().getId());
		holder.setItem(item);
		holder.setUnit(regend.getMaterialUnitCode());
		countSpend = countSpend.multiply(unitItemCount).multiply(regend.getMaterialCount());
		holder.setCountSpend(countSpend);
		menuMetarialList.add(holder);
	}

	//malzeme
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	private void putMaterial(List<MenuMaterialHolder> menuMetarialList, CatMenuItemEntity menuItem, BigDecimal unitItemCount, BigDecimal countSpend) {
		MenuMaterialHolder holder = new MenuMaterialHolder();
		DefItemEntity item = itemRepository.findWithFetch(menuItem.getItem().getId());
		holder.setItem(item);
		holder.setUnit(menuItem.getUnit());
		holder.setCountSpend(countSpend.multiply(unitItemCount));
		menuMetarialList.add(holder);
	}


}
