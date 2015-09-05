package com.study.access;

import java.sql.SQLException;

import javax.ejb.Stateless;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.study.model.GroupSchedule;

@Stateless
public class GroupScheduleManager extends Manager {
    private Dao<GroupSchedule, Integer> groupScheduleDao = null;

    public void create(GroupSchedule groupSchedule) {
        try {
            getGroupScheduleDao().create(groupSchedule);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer groupId) {
        DeleteBuilder<GroupSchedule, Integer> deleteBuilder = getGroupScheduleDao().deleteBuilder();
        try {
            deleteBuilder.where().eq(GROUP_ID_FIELD_NAME, groupId);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Dao<GroupSchedule, Integer> getGroupScheduleDao() {
        if (groupScheduleDao == null) {
            try {
                groupScheduleDao = DaoManager.createDao(getConnection(), GroupSchedule.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return groupScheduleDao;
    }

}
