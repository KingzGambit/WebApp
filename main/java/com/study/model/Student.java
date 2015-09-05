package com.study.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.study.access.Manager;

@DatabaseTable
public class Student {

    @DatabaseField(useGetSet = true, columnName = Manager.ID_FIELD_NAME, generatedId = true)
    private Integer id;

    @DatabaseField(useGetSet = true, columnName = Manager.FIRST_NAME_FIELD_NAME)
    private String firstName;

    @DatabaseField(useGetSet = true, columnName = Manager.LAST_NAME_FIELD_NAME)
    private String lastName;

    @DatabaseField(useGetSet = true, unique = true)
    private String email;

    @DatabaseField(useGetSet = true)
    private String password;

    @DatabaseField(useGetSet = true, columnName = Manager.UPDATED_AT_FIELD_NAME)
    private Date updatedAt;

    @DatabaseField(useGetSet = true, columnName = Manager.CREATED_AT_FIELD_NAME)
    private Date createdAt;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + "]";
    }

}
