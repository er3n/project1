package org.abacus.catering.core.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.abacus.catering.shared.entity.CatMenuEntity;
import org.abacus.catering.shared.entity.CatMenuItemEntity;
import org.abacus.catering.shared.holder.MenuMaterialHolder;
import org.abacus.definition.shared.entity.DefItemEntity;
import org.abacus.definition.shared.entity.DefItemProductEntity;
import org.abacus.definition.shared.entity.DefUnitCodeEntity;
import org.springframework.util.CollectionUtils;

public class CatMenuItemToMenuMaterialConverter {
	
	public static Collection<MenuMaterialHolder> convert(CatMenuEntity menu,Set<CatMenuItemEntity> menuItemSet){
		
		BigDecimal countSpend = menu.getCountSpend();
		
		Map<String, MenuMaterialHolder> menuMetarialMap = new HashMap<>();
		for(CatMenuItemEntity menuItem : menuItemSet){
			
			DefUnitCodeEntity unit = menuItem.getUnit();
			Set<DefItemProductEntity> regends = menuItem.getItem().getItemProductSet(); 
			if(CollectionUtils.isEmpty(regends)){
				
				putMaterial(menuMetarialMap, menuItem,countSpend);
				
			}else{
				
				for(DefItemProductEntity regend : regends){
					putMaterial(menuMetarialMap, regend, null,countSpend);
				}	
				
			}

			
		}
		
		return menuMetarialMap.values();
	}

	private static void putMaterial(Map<String, MenuMaterialHolder> menuMetarialMap, DefItemProductEntity regend, Object object, BigDecimal countSpend) {
		BigDecimal materialCount = regend.getMaterialCount();
		DefUnitCodeEntity unit = regend.getMaterialUnitCode();
	}

	private static void putMaterial(Map<String, MenuMaterialHolder> menuMetarialMap, CatMenuItemEntity menuItem, BigDecimal countSpend) {
		// TODO Auto-generated method stub
		
	}


}
