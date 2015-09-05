package com.study.model;

import java.util.Date;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.study.access.Manager;

@DatabaseTable(tableName = "study_group")
public class StudyGroup {

    @DatabaseField(useGetSet = true, columnName = Manager.ID_FIELD_NAME, generatedId = true)
    private Integer id;

    @DatabaseField(useGetSet = true, columnName = Manager.ORGANIZER_STUDENT_ID_FIELD_NAME, foreign = true)
    private Student student;

    @DatabaseField(useGetSet = true)
    private String topic;

    @DatabaseField(useGetSet = true)
    private String description;

    @DatabaseField(useGetSet = true)
    private String prerequisites;

    @DatabaseField(useGetSet = true)
    private Boolean onCampus;

    @DatabaseField(useGetSet = true)
    private String location;

    @DatabaseField(useGetSet = true)
    private Integer maxParticipants;

    @DatabaseField(useGetSet = true)
    private Boolean recurring;

    @DatabaseField(useGetSet = true)
    private Date startDate;

    @DatabaseField(useGetSet = true)
    private Date endDate;

    @ForeignCollectionField
    private ForeignCollection<GroupSchedule> availability;

    @DatabaseField(useGetSet = true, columnName = Manager.UPDATED_AT_FIELD_NAME)
    private Date updatedAt;

    @DatabaseField(useGetSet = true, columnName = Manager.CREATED_AT_FIELD_NAME)
    private Date createdAt;
    
    private boolean currentViewerIsOwner;

    public StudyGroup() {
        currentViewerIsOwner = true;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public Boolean getOnCampus() {
        return onCampus;
    }

    public void setOnCampus(Boolean onCampus) {
        this.onCampus = onCampus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ForeignCollection<GroupSchedule> getAvailability() {
        return availability;
    }

    public void setAvailability(ForeignCollection<GroupSchedule> availability) {
        this.availability = availability;
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
        return "StudyGroup [id=" + id + ", student=" + student + ", topic=" + topic + ", description=" + description + ", prerequisites=" + prerequisites + ", onCampus=" + onCampus + ", location=" + location + ", maxParticipants=" + maxParticipants + ", recurring=" + recurring + ", startDate=" + startDate + ", endDate=" + endDate + ", availability=" + availability + ", updatedAt=" + updatedAt
                + ", createdAt=" + createdAt + "]";
    }

    public boolean getCurrentViewerIsOwner() {
        return currentViewerIsOwner;
    }

    public void setCurrentViewerIsOwner(boolean currentViewerIsOwner) {
        this.currentViewerIsOwner = currentViewerIsOwner;
    }

}
