package com.study.access;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.study.model.GroupMember;
import com.study.model.GroupSchedule;
import com.study.model.Student;
import com.study.model.StudyGroup;
import com.study.util.Utility;

public class Manager extends Utility {
    public static final String ID_FIELD_NAME = "id";
    public static final String STUDENT_ID_FIELD_NAME = "student_id";
    public static final String GROUP_ID_FIELD_NAME = "group_id";
    public static final String ORGANIZER_STUDENT_ID_FIELD_NAME = "organizer_id";

    public static final String FIRST_NAME_FIELD_NAME = "first_name";
    public static final String LAST_NAME_FIELD_NAME = "last_name";
    public static final String EMAIL_FIELD_NAME = "email";
    public static final String DAY_OF_WEEK_FIELD_NAME = "day_of_week";
    public static final String START_TIME_FIELD_NAME = "start_time";
    public static final String END_TIME_FIELD_NAME = "end_time";
    public static final String CREATED_AT_FIELD_NAME = "created_at";
    public static final String UPDATED_AT_FIELD_NAME = "updated_at";
    public static final String TOPIC_FIELD_NAME = "topic";
    public static final String ON_CAMPUS_FIELD_NAME ="on_campus" ;
    public static final String LOCATION_FIELD_NAME ="location" ;
    public static final String START_DATE_FIELD_NAME="startDate";


    private static final String DB_URL = "jdbc:mysql://localhost:3306/studygroup_db";
    private static final String DB_USER = "studygroupapp";
    private static final String DB_PASS = "321studyapp";

    private static JdbcPooledConnectionSource connection = null;
    private static boolean TABLES_INITIALIZED = false;

    protected static JdbcPooledConnectionSource getConnection() {
        if (connection == null) {
            try {
                connection = new JdbcPooledConnectionSource(DB_URL, DB_USER, DB_PASS);
                if (!TABLES_INITIALIZED) {
                    TableUtils.createTableIfNotExists(connection, Student.class);
                    TableUtils.createTableIfNotExists(connection, StudyGroup.class);
                    TableUtils.createTableIfNotExists(connection, GroupMember.class);
                    TableUtils.createTableIfNotExists(connection, GroupSchedule.class);
                    TABLES_INITIALIZED = true;
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        }
        return connection;
    }

}
