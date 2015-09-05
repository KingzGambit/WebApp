package com.study.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.study.model.GroupMember;
import com.study.model.StudyGroup;

@ManagedBean
@RequestScoped
public class ManageMyStudyGroupsBean extends BaseBean {

    public List<StudyGroup> joinedStudyGroups() {
        List<GroupMember> membershipList = groupMemberManager.getGroupMembership(getLoggedInStudent().getId());
        if (membershipList.size() > 0) {
            return studyGroupManager.getStudyGroups(extractStudyGroupIds(membershipList));
        } else {
            FacesContext.getCurrentInstance().addMessage("manageForm:messagePanel", new FacesMessage("Study Groups you join or create will appear here."));
            return null;
        }
    }

    private List<Integer> extractStudyGroupIds(List<GroupMember> membershipList) {
        List<Integer> studyGroupIds = new ArrayList<Integer>();
        for (GroupMember membership : membershipList) {
            studyGroupIds.add(membership.getStudyGroup().getId());
        }
        return studyGroupIds;
    }

    public String leave(Integer studyGroupId) {
        return leaveGroup(studyGroupId);
    }
    
    public String edit(Integer studyGroupId) {
        if (isGroupOrganizer(studyGroupId)) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("studyGroupIdForEdit", studyGroupId);
            return "/app/editMyGroup.xhtml";
        }
        else {
            log.warn("User who is not group owner attempted to edit the group.");
            return INDEX_PAGE_WITH_REDIRECT;
        }
    }

}
