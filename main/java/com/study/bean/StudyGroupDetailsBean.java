package com.study.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.study.model.GroupMember;
import com.study.model.GroupSchedule;
import com.study.model.StudyGroup;

@ManagedBean
@ViewScoped
public class StudyGroupDetailsBean extends BaseBean {

    private StudyGroup studyGroup;
    private List<GroupSchedule> availability;

    public boolean canLeaveGroup() {
        if (isLoggedIn() && studyGroupExists() && isMemberOfGroup()) {
            return true;
        } else {
            return false;
        }
    }

    public String leaveGroup() {
        return leaveGroup(studyGroup.getId());
    }

    public boolean canJoinGroup() {
        if (isLoggedIn() && studyGroupExists() && !isMemberOfGroup()) {
            return true;
        } else {
            return false;
        }
    }

    public String joinGroup() {
        groupMemberManager.create(new GroupMember(getLoggedInStudent(), studyGroup, currentDateTime(), currentDateTime()));
        log.info("Student (ID {}) joined StudyGroup (ID {})", getLoggedInStudent().getId(), studyGroup.getId());
        return INDEX_PAGE_WITH_REDIRECT;
    }

    private boolean isMemberOfGroup() {
        return groupMemberManager.isMemberOfGroup(getLoggedInStudent().getId(), studyGroup.getId());
    }

    private boolean studyGroupExists() {
        return studyGroupManager.studyGroupExists(studyGroup.getId());
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        availability = new ArrayList<GroupSchedule>(studyGroup.getAvailability());
        this.studyGroup = studyGroup;
    }

    public List<GroupSchedule> getAvailability() {
        return availability;
    }

    public void setAvailability(List<GroupSchedule> availability) {
        this.availability = availability;
    }

}
