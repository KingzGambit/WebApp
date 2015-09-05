package com.study.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

@ManagedBean
@RequestScoped
public class LoginBean extends BaseBean {

    private String username;
    private String password;
    private boolean rememberMe;

    public String login() {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, new Sha256Hash(password).toHex());
        token.setRememberMe(rememberMe);
        try {
            currentUser.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            FacesContext.getCurrentInstance().addMessage("loginForm:messagePanel", new FacesMessage("Incorrect email or password. Please try again."));
            log.warn("Login Failed (USER: {})", username);
            return REMAIN_ON_THE_SAME_PAGE;
        } catch (LockedAccountException lae) {
            FacesContext.getCurrentInstance().addMessage("loginForm:messagePanel", new FacesMessage("Account locked."));
            return REMAIN_ON_THE_SAME_PAGE;
        } catch (AuthenticationException aex) {
            FacesContext.getCurrentInstance().addMessage("loginForm:messagePanel", new FacesMessage("Login failed!", aex.getMessage()));
            return REMAIN_ON_THE_SAME_PAGE;
        }
        String email = (String) SecurityUtils.getSubject().getPrincipal();
        setLoggedInStudent(studentManager.getStudentByEmail(email));
        log.info("Logged In Student (ID: {})", getLoggedInStudent().getId());
        return INDEX_PAGE_WITH_REDIRECT;
    }

    public String logout() throws IOException {
        SecurityUtils.getSubject().logout();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return INDEX_PAGE_WITH_REDIRECT;
    }

    public String studentFirstName() {
        return getLoggedInStudent().getFirstName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

}
