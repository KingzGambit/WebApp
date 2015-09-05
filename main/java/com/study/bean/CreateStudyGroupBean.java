package com.study.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.study.model.GroupMember;
import com.study.model.GroupSchedule;
import com.study.model.GroupSchedule.DayOfTheWeek;
import com.study.model.StudyGroup;

@ManagedBean
@RequestScoped
public class CreateStudyGroupBean extends BaseBean {

    private StudyGroup studyGroup = new StudyGroup();
    private List<GroupSchedule> availability = new ArrayList<GroupSchedule>();
    private Map<String, Integer> startTime;
    private Map<String, Integer> endTime;

    public String createStudyGroup() {
        studyGroup.setCreatedAt(currentDateTime());
        studyGroup.setUpdatedAt(currentDateTime());
        studyGroup.setStudent(getLoggedInStudent());
        studyGroupManager.create(studyGroup);
        for (GroupSchedule day : availability) {
            if (day.getStartTime() != null && day.getEndTime() != null) {
                day.setStudyGroup(studyGroup);
                groupScheduleManager.create(day);
            }
        }
        groupMemberManager.create(new GroupMember(getLoggedInStudent(), studyGroup, currentDateTime(), currentDateTime()));
        return "/studyGroupDetails.xhtml?faces-redirect=true&id=" + studyGroup.getId();
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public List<GroupSchedule> getAvailability() {
        return availability;
    }

    public void setAvailability(List<GroupSchedule> availability) {
        this.availability = availability;
    }

    public Map<String, Integer> getStartTime() {
        return startTime;
    }

    public Map<String, Integer> getEndTime() {
        return endTime;
    }

    @PostConstruct
    public void init() {
        availability.add(new GroupSchedule(DayOfTheWeek.MONDAY));
        availability.add(new GroupSchedule(DayOfTheWeek.TUESDAY));
        availability.add(new GroupSchedule(DayOfTheWeek.WEDNESDAY));
        availability.add(new GroupSchedule(DayOfTheWeek.THURSDAY));
        availability.add(new GroupSchedule(DayOfTheWeek.FRIDAY));
        availability.add(new GroupSchedule(DayOfTheWeek.SATURDAY));
        availability.add(new GroupSchedule(DayOfTheWeek.SUNDAY));

        startTime = getTimeIncrements();
        endTime = getTimeIncrements();
    }

    private LinkedHashMap<String, Integer> getTimeIncrements() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("1:00 AM", 60);
        map.put("2:00 AM", 120);
        map.put("3:00 AM", 180);
        map.put("4:00 AM", 240);
        map.put("5:00 AM", 300);
        map.put("6:00 AM", 360);
        map.put("7:00 AM", 420);
        map.put("8:00 AM", 480);
        map.put("9:00 AM", 540);
        map.put("10:00 AM", 600);
        map.put("11:00 AM", 660);
        map.put("12:00 PM", 720);
        map.put("1:00 PM", 780);
        map.put("2:00 PM", 840);
        map.put("3:00 PM", 900);
        map.put("4:00 PM", 960);
        map.put("5:00 PM", 1020);
        map.put("6:00 PM", 1080);
        map.put("7:00 PM", 1140);
        map.put("8:00 PM", 1200);
        map.put("9:00 PM", 1260);
        map.put("10:00 PM", 1320);
        map.put("11:00 PM", 1380);
        map.put("12:00 AM", 1440);
        return map;
    }
}
