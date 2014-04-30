package org.abacus.user.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.abacus.user.shared.entity.SecGroupEntity;

@FacesConverter("groupEntityConverter")
public class GroupEntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		SecGroupEntity entitiy = new SecGroupEntity();
		entitiy.setId(Long.valueOf(value));
		return entitiy;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		SecGroupEntity entitiy = (SecGroupEntity) value;
		return String.valueOf(entitiy.getId());
	}

}
