package com.studyapp.user;

import java.io.Serializable;

public class User implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public User()
    {
    }
    
    public User(String firstName, String lastName, String emailAddress, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String createUser()
    {
        User u = new User(firstName, lastName, emailAddress, password);
        System.out.println(u);
        return "index";
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", password=" + password + "]";
    }

}