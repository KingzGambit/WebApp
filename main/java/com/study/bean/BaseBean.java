package com.study.bean;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.study.access.GroupMemberManager;
import com.study.access.GroupScheduleManager;
import com.study.access.StudentManager;
import com.study.access.StudyGroupManager;
import com.study.model.Student;
import com.study.model.StudyGroup;
import com.study.util.Utility;

@ManagedBean
@SessionScoped
public class BaseBean extends Utility {

    @EJB
    protected StudentManager studentManager;
    @EJB
    protected StudyGroupManager studyGroupManager;
    @EJB
    protected GroupMemberManager groupMemberManager;
    @EJB
    protected GroupScheduleManager groupScheduleManager;

    public static final String INDEX_PAGE_NAME = "/index";
    public static final String INDEX_PAGE_WITH_REDIRECT = "/index?faces-redirect=true";
    public static final String UPDATE_GROUP_ORGANIZER_PAGE = "/app/updateGroupOrganizer.xhtml";
    public static final String REMAIN_ON_THE_SAME_PAGE = "";

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    private static final String LOGGED_IN_STUDENT = "loggedInStudent";

    Student getLoggedInStudent() {
        return (Student) session.getAttribute(LOGGED_IN_STUDENT);
    }

    void setLoggedInStudent(Student student) {
        session.setAttribute(LOGGED_IN_STUDENT, student);
    }

    public boolean isLoggedIn() {
        return getLoggedInStudent() != null;
    }

    Date currentDateTime() {
        return new Date();
    }

    String leaveGroup(Integer groupId) {
        if (isGroupOrganizer(groupId) && groupMemberManager.getGroupMembers(groupId).size() > 1) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("studyGroupId", groupId);
            return UPDATE_GROUP_ORGANIZER_PAGE;
        }
        groupMemberManager.leaveStudyGroup(getLoggedInStudent().getId(), groupId);
        log.info("Student(ID {}) left Study Group (ID {})", getLoggedInStudent().getId(), groupId);
        deleteIfEmptyStudyGroup(groupId);
        return INDEX_PAGE_WITH_REDIRECT;
    }

    public boolean isGroupOrganizer(Integer groupId) {
        StudyGroup studyGroup = studyGroupManager.getStudyGroup(groupId);
        if (studyGroup.getStudent().getId() == getLoggedInStudent().getId()) {
            return true;
        }
        return false;
    }

    void deleteIfEmptyStudyGroup(Integer groupId) {
        if (!groupMemberManager.studyGroupHasMembers(groupId)) {
            studyGroupManager.delete(groupId);
            groupScheduleManager.delete(groupId);
            log.info("Deleted StudyGroup (ID {}) with zero members", groupId);
        }
    }

}
