package org.abacus.user.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.abacus.common.shared.entity.StaticEntity;
import org.abacus.user.shared.entity.SecAuthorityEntity;
import org.springframework.util.ObjectUtils;


@FacesConverter("authorityEntityConverter")
public class AuthorityEntityConverter implements  Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		SecAuthorityEntity entitiy  = new SecAuthorityEntity();
		entitiy.setId(value);
		return entitiy;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		StaticEntity entitiy = (SecAuthorityEntity) value;
		return entitiy.getId();
	}

}
