package org.abacus.user.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.abacus.user.core.persistance.repository.GroupRepository;
import org.abacus.user.shared.entity.SecGroupEntity;
import org.springframework.web.jsf.FacesContextUtils;

@FacesConverter("groupEntityConverter")
public class GroupEntityConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		GroupRepository repository = FacesContextUtils.getWebApplicationContext(context).getBean(GroupRepository.class);
		SecGroupEntity entitiy = repository.findOne(Long.valueOf(value));
		return entitiy;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		SecGroupEntity entitiy = (SecGroupEntity) value;
		return String.valueOf(entitiy.getId());
	}

}
