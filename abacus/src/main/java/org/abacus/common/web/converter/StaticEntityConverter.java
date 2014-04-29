package org.abacus.common.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.abacus.common.shared.entity.StaticEntity;


@FacesConverter("staticEntityConverter")
public class StaticEntityConverter implements  Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		StaticEntity entitiy  = new StaticEntity();
		entitiy.setId(value);
		return entitiy;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		StaticEntity entitiy = (StaticEntity) value;
		return entitiy.getId();
	}

}
