package com.study.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.study.model.GroupMember;
import com.study.model.Student;

@ManagedBean
@ViewScoped
public class UpdateGroupOrganizerBean extends BaseBean {

    private Map<String, Integer> groupMembers = new HashMap<String, Integer>();
    private Integer newOrganizerId;
    private Integer groupId;

    public String updateGroupOrganizer() {
        studyGroupManager.updateGroupOrganizer(groupId, newOrganizerId);
        log.info("Updated Study Group (ID {}) with new Organizer (ID {})", groupId, newOrganizerId);
        groupMemberManager.leaveStudyGroup(getLoggedInStudent().getId(), groupId);
        deleteIfEmptyStudyGroup(groupId);
        return INDEX_PAGE_WITH_REDIRECT;
    }

    public Map<String, Integer> getGroupMembers() {
        return groupMembers;
    }

    public Integer getNewOrganizerId() {
        return newOrganizerId;
    }

    public void setNewOrganizerId(Integer newOrganizerId) {
        this.newOrganizerId = newOrganizerId;
    }

    @PostConstruct
    public void init() {
        groupId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("studyGroupId");
        List<GroupMember> membershipList = groupMemberManager.getGroupMembers(groupId);
        List<Student> students = studentManager.getStudentsByIds(extractStudentIds(membershipList));
        for (Student student : students) {
            if (student.getId() != getLoggedInStudent().getId()) {
                groupMembers.put(student.getFirstName() + " " + student.getLastName(), student.getId());
            }
        }
    }

    private List<Integer> extractStudentIds(List<GroupMember> membershipList) {
        List<Integer> studentIds = new ArrayList<Integer>();
        for (GroupMember membership : membershipList) {
            studentIds.add(membership.getStudent().getId());
        }
        return studentIds;
    }

}
