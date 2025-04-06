package com.gescom.beans;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.gescom.model.Role;

@FacesConverter("roleConverter")
public class RoleConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        RoleBean roleBean = context.getApplication().evaluateExpressionGet(context, "#{roleBean}", RoleBean.class);
        return roleBean.getRoleById(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(((Role) value).getIdrole());
    }
}
