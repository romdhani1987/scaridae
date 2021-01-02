package fr.romdhani.scaridae.controller;

import fr.romdhani.scaridae.core.orm.UserAccount;

/**
 * @author aromdhani
 */
public class CurrentSession {
    private CurrentSession() {
    }

    private static class InstanceHolder {
        private static final CurrentSession INSTANCE = new CurrentSession();
    }

    public static CurrentSession getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private String login;
    private UserAccount userAccount;

    public boolean signOut() {
        return true;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
