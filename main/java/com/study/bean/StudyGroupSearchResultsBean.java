package com.study.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.study.access.StudyGroupManager;
import com.study.model.StudyGroup;

@ManagedBean
@RequestScoped
public class StudyGroupSearchResultsBean extends BaseBean {

    @EJB
    private StudyGroupManager studyGroupManager;

    private StudyGroup studyGroup = new StudyGroup();

    public List<StudyGroup> searchResults() {
        return studyGroupManager.search(studyGroup);
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }
}
