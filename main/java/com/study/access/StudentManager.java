package com.study.access;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.study.model.Student;

@Stateless
public class StudentManager extends Manager {
    private Dao<Student, Integer> studentDao = null;

    public void create(Student student) {
        try {
            getStudentDao().create(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentByEmail(String email) {
        List<Student> students = new ArrayList<Student>();
        QueryBuilder<Student, Integer> queryBuilder = getStudentDao().queryBuilder();
        try {
            queryBuilder.where().eq(EMAIL_FIELD_NAME, email);
            students = getStudentDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (students.size() > 0) {
            return students.get(0);
        } else {
            return null;
        }
    }

    public List<Student> getStudentsByIds(List<Integer> studentIds) {
        List<Student> students = new ArrayList<Student>();
        QueryBuilder<Student, Integer> queryBuilder = getStudentDao().queryBuilder();
        try {
            queryBuilder.where().in(ID_FIELD_NAME, studentIds);
            students = getStudentDao().query(queryBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    private Dao<Student, Integer> getStudentDao() {
        if (studentDao == null) {
            try {
                studentDao = DaoManager.createDao(getConnection(), Student.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentDao;
    }

}
