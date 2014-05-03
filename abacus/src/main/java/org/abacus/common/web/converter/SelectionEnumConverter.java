package org.abacus.common.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.abacus.definition.shared.constant.SelectionEnum;


@FacesConverter("selectionEnumConverter")
public class SelectionEnumConverter implements  Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value==null){
			return null;
		}
		return new SelectionEnum(value.toString());
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if(value == null){
			return null;
		}
		SelectionEnum entitiy = (SelectionEnum) value;
		return entitiy.name();
	}

}
