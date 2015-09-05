package com.study.access;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.study.model.GroupMember;

@Stateless
public class GroupMemberManager extends Manager {
    private Dao<GroupMember, Integer> groupMemberDao = null;

    public void create(GroupMember groupMember) {
        try {
            getGroupMemberDao().create(groupMember);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GroupMember> getGroupMembership(Integer studentId) {
        List<GroupMember> membership = new ArrayList<GroupMember>();
        QueryBuilder<GroupMember, Integer> queryBuilder = getGroupMemberDao().queryBuilder();
        try {
            queryBuilder.where().eq(STUDENT_ID_FIELD_NAME, studentId);
            membership = getGroupMemberDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membership;
    }

    public List<GroupMember> getGroupMembers(Integer groupId) {
        List<GroupMember> members = new ArrayList<GroupMember>();
        QueryBuilder<GroupMember, Integer> queryBuilder = getGroupMemberDao().queryBuilder();
        try {
            queryBuilder.where().eq(GROUP_ID_FIELD_NAME, groupId);
            members = getGroupMemberDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public boolean isMemberOfGroup(Integer studentId, Integer groupId) {
        List<GroupMember> results = new ArrayList<GroupMember>();
        QueryBuilder<GroupMember, Integer> queryBuilder = getGroupMemberDao().queryBuilder();
        try {
            queryBuilder.selectColumns(ID_FIELD_NAME).where().eq(STUDENT_ID_FIELD_NAME, studentId).and().eq(GROUP_ID_FIELD_NAME, groupId);
            results = getGroupMemberDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (results.size() != 0) ? true : false;
    }

    public boolean studyGroupHasMembers(Integer groupId) {
        List<GroupMember> results = new ArrayList<GroupMember>();
        QueryBuilder<GroupMember, Integer> queryBuilder = getGroupMemberDao().queryBuilder();
        try {
            queryBuilder.selectColumns(ID_FIELD_NAME).where().eq(GROUP_ID_FIELD_NAME, groupId);
            results = getGroupMemberDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (results.size() > 0) ? true : false;
    }

    public void leaveStudyGroup(Integer studentId, Integer groupId) {
        DeleteBuilder<GroupMember, Integer> deleteBuilder = getGroupMemberDao().deleteBuilder();
        try {
            deleteBuilder.where().eq(STUDENT_ID_FIELD_NAME, studentId).and().eq(GROUP_ID_FIELD_NAME, groupId);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dao<GroupMember, Integer> getGroupMemberDao() {
        if (groupMemberDao == null) {
            try {
                groupMemberDao = DaoManager.createDao(getConnection(), GroupMember.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return groupMemberDao;
    }

}
