package com.study.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.study.access.Manager;

@DatabaseTable(tableName = "group_member")
public class GroupMember {

    @DatabaseField(useGetSet = true, columnName = Manager.ID_FIELD_NAME, generatedId = true)
    private Integer id;

    @DatabaseField(useGetSet = true, columnName = Manager.STUDENT_ID_FIELD_NAME, foreign = true, canBeNull = false)
    private Student student;

    @DatabaseField(useGetSet = true, columnName = Manager.GROUP_ID_FIELD_NAME, foreign = true, canBeNull = false)
    private StudyGroup studyGroup;

    @DatabaseField(useGetSet = true, columnName = Manager.UPDATED_AT_FIELD_NAME)
    private Date updatedAt;

    @DatabaseField(useGetSet = true, columnName = Manager.CREATED_AT_FIELD_NAME)
    private Date createdAt;

    public GroupMember() {
    }

    public GroupMember(Student student, StudyGroup studyGroup, Date updatedAt, Date createdAt) {
        this.student = student;
        this.studyGroup = studyGroup;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "GroupMember [id=" + id + ", student=" + student + ", studyGroup=" + studyGroup + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + "]";
    }

}
