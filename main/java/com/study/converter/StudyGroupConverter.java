package com.study.converter;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.study.access.StudyGroupManager;
import com.study.model.StudyGroup;
import com.study.util.Utility;

@ManagedBean
@ApplicationScoped
public class StudyGroupConverter extends Utility implements Converter {

    @EJB
    private StudyGroupManager studyGroupManager;

    @Override
    public StudyGroup getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else if (!value.matches("[0-9]+")) {
            throw new ConverterException("Invalid Study Group ID: " + value);
        }
        StudyGroup group = studyGroupManager.getStudyGroup(Integer.valueOf(value));
        return group;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else if (!(value instanceof StudyGroup)) {
            throw new ConverterException("Invalid Study Group: " + value);
        }
        Integer id = ((StudyGroup) value).getId();
        return (id != null) ? String.valueOf(id) : "";
    }

}
