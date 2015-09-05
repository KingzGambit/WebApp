package com.study.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.crypto.hash.Sha256Hash;

import com.study.model.Student;

@ManagedBean
@RequestScoped
public class RegistrationBean extends BaseBean {

    private Student student = new Student();

    public String createStudent() {
        if (studentManager.getStudentByEmail(student.getEmail()) != null) {
            FacesContext.getCurrentInstance().addMessage("registrationForm:messagePanel", new FacesMessage("This email is already registered."));
            return REMAIN_ON_THE_SAME_PAGE;
        }
        student.setCreatedAt(currentDateTime());
        student.setUpdatedAt(currentDateTime());
        student.setPassword(hashPlainTextPassword(student.getPassword()));
        studentManager.create(student);
        return INDEX_PAGE_NAME;
    }

    private String hashPlainTextPassword(String password) {
        return new Sha256Hash(password).toHex();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
