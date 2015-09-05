package com.study.bean;

import java.text.SimpleDateFormat;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.study.model.StudyGroup;

@ManagedBean
@RequestScoped
public class SearchStudyGroupsBean extends BaseBean {

    private StudyGroup studyGroup = new StudyGroup();
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    private String topic = "";
    private String location = "";
    private String startDate = "";

    public String search() {
        if (!studyGroup.getTopic().trim().isEmpty())
            topic = studyGroup.getTopic();
        if (!studyGroup.getLocation().trim().isEmpty())
            location = studyGroup.getLocation();
        if (!studyGroup.getStartDate().equals(null)) {
            startDate = sdf.format(studyGroup.getStartDate());
        }
        return "studyGroupSearchResults.xhtml?faces-redirect=true&topic=" + topic + "&location=" + location + "&startDate =" + startDate;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

}
