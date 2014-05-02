package org.abacus.common.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.abacus.definition.shared.constant.DefConstant;


@FacesConverter("enumEntitiyConverter")
public class GroupEnumEntityConverter implements  Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		DefConstant.DefTypeGroupEnum[] groupEnums = DefConstant.DefTypeGroupEnum.values();
		for(DefConstant.DefTypeGroupEnum groupEnum : groupEnums){
			if(groupEnum.name().equals(value)){
				return groupEnum;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value instanceof String){
			return null;
		}
		DefConstant.DefTypeGroupEnum entitiy = (DefConstant.DefTypeGroupEnum) value;
		return entitiy.name();
	}

}
