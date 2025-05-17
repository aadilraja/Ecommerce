package application.server.persistence.DTOs;

import application.server.persistence.model.UserInfo;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private UserInfo user;
    public OnRegistrationCompleteEvent(UserInfo user, Locale locale, String appUrl) {
        super(user);
        this.locale = locale;
        this.appUrl = appUrl;
        this.user = user;

    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
