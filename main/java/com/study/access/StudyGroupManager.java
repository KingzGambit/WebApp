package com.study.access;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.study.model.Student;
import com.study.model.StudyGroup;

@Stateless
public class StudyGroupManager extends Manager {
    private Dao<StudyGroup, Integer> studyGroupDao = null;

    public void create(StudyGroup studyGroup) {
        try {
            getStudyGroupDao().create(studyGroup);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer studyGroupId) {
        try {
            getStudyGroupDao().deleteById(studyGroupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroupOrganizer(Integer groupId, Integer newOrganizerId) {
        UpdateBuilder<StudyGroup, Integer> updateBuilder = getStudyGroupDao().updateBuilder();
        try {
            updateBuilder.updateColumnValue(ORGANIZER_STUDENT_ID_FIELD_NAME, new Student(newOrganizerId));
            updateBuilder.where().eq(ID_FIELD_NAME, groupId);
            updateBuilder.update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StudyGroup getStudyGroup(Integer studyGroupId) {
        return getStudyGroupById(studyGroupId);
    }

    public boolean studyGroupExists(Integer studyGroupId) {
        return getStudyGroupById(studyGroupId) != null ? true : false;
    }

    private StudyGroup getStudyGroupById(Integer studyGroupId) {
        StudyGroup group = new StudyGroup();
        try {
            group = getStudyGroupDao().queryForId(studyGroupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public List<StudyGroup> getStudyGroups(List<Integer> groupIds) {
        List<StudyGroup> studyGroups = new ArrayList<StudyGroup>();
        QueryBuilder<StudyGroup, Integer> queryBuilder = getStudyGroupDao().queryBuilder();
        try {
            queryBuilder.where().in(ID_FIELD_NAME, groupIds);
            studyGroups = getStudyGroupDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studyGroups;
    }

    public List<StudyGroup> search(StudyGroup studyGroup) {
        List<StudyGroup> studyGroups = new ArrayList<StudyGroup>();
        QueryBuilder<StudyGroup, Integer> queryBuilder = getStudyGroupDao().queryBuilder();
        buildQuery(queryBuilder, studyGroup);
        try {
            studyGroups = getStudyGroupDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studyGroups;
    }

    private void buildQuery(QueryBuilder<StudyGroup, Integer> queryBuilder, StudyGroup studyGroup) {
        try {
            Where<StudyGroup, Integer> search_query = queryBuilder.where();
            int num_clauses = 0;

            if (!studyGroup.getTopic().trim().isEmpty()) {
                log.info("{}", studyGroup.getTopic());

                search_query.like(TOPIC_FIELD_NAME, "%" + studyGroup.getTopic() + "%");
                num_clauses++;
            }

            if (!studyGroup.getLocation().trim().isEmpty()) {
                log.info("{}", studyGroup.getLocation());

                search_query.like(LOCATION_FIELD_NAME, "%" + studyGroup.getLocation() + "%");
                num_clauses++;
            }

            final int MAX_YEAR = 199; // = 2099
            if (studyGroup.getStartDate() != null && studyGroup.getStartDate().getYear() < MAX_YEAR) {
                log.info("{}", studyGroup.getStartDate());
                search_query.eq(START_DATE_FIELD_NAME, studyGroup.getStartDate());
                num_clauses++;
            }

            search_query.and(num_clauses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dao<StudyGroup, Integer> getStudyGroupDao() {
        if (studyGroupDao == null) {
            try {
                studyGroupDao = DaoManager.createDao(getConnection(), StudyGroup.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studyGroupDao;
    }

}
