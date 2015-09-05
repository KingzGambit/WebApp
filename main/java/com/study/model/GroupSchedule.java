package com.study.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.study.access.Manager;

@DatabaseTable(tableName = "group_schedule")
public class GroupSchedule {

    @DatabaseField(useGetSet = true, columnName = Manager.ID_FIELD_NAME, generatedId = true)
    private Integer id;

    @DatabaseField(useGetSet = true, columnName = Manager.GROUP_ID_FIELD_NAME, foreign = true)
    private StudyGroup studyGroup;

    @DatabaseField(useGetSet = true, columnName = Manager.DAY_OF_WEEK_FIELD_NAME)
    private DayOfTheWeek dayOfTheWeek;

    @DatabaseField(useGetSet = true, columnName = Manager.START_TIME_FIELD_NAME)
    private Integer startTime;

    @DatabaseField(useGetSet = true, columnName = Manager.END_TIME_FIELD_NAME)
    private Integer endTime;

    public enum DayOfTheWeek {
        SUNDAY("Sunday", 0), MONDAY("Monday", 1), TUESDAY("Tuesday", 2), WEDNESDAY("Wednesday", 3), THURSDAY("Thursday", 4), FRIDAY("Friday", 5), SATURDAY("Saturday", 6);

        private String dayName;
        private Integer dayNumber;

        DayOfTheWeek(String dayName, Integer dayNumber) {
            this.dayName = dayName;
            this.dayNumber = dayNumber;
        }

        public String getDayName() {
            return dayName;
        }

        public Integer getDayNumber() {
            return dayNumber;
        }
    }

    public GroupSchedule() {
    }

    public GroupSchedule(DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Integer getStartTime() {
        return startTime;
    }

    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "GroupSchedule [id=" + id + ", studyGroup=" + studyGroup + ", dayOfTheWeek=" + dayOfTheWeek + ", startTime=" + startTime + ", endTime=" + endTime + "]";
    }

}
