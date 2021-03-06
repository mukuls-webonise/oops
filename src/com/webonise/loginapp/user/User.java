package com.webonise.loginapp.user;

import com.sun.istack.internal.NotNull;
import com.webonise.loginapp.login.LoginChoice;
import com.webonise.loginapp.login.LoginProvider;
import com.webonise.loginapp.login.properietary.SimpleLoginProvider;
import com.webonise.loginapp.login.social.facebook.FacebookLoginProvider;
import com.webonise.loginapp.login.social.linkedin.LinkedInLoginProvider;
import com.webonise.loginapp.login.social.twitter.TwitterLoginProvider;

/**
 * Created by mukuls-webonise on 9/8/16.
 */
public class User {

    @NotNull
    String username;
    @NotNull
    String loginProvider;

    private SessionManager sessionManager;

    User(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

//    Login user based on login choice, Writing switch here eliminates using switch anywhere else in program for same purpose
    public static User login(String username, String[] pass, LoginChoice.Choice choice) throws LoginChoice.InvalidChoiceException {

        if (choice == null) {
            throw  new LoginChoice.InvalidChoiceException();
        }

        switch (choice) {
            case FACEBOOK:
                return loginWithFacebook(username, pass);

            case TWITTER:
                return loginWithTwitter(username, pass);

            case LINKED_IN:
                return loginWithLinkedIn(username, pass);

            case SIMPLE:
                return loginWithUsername(username, pass);

                default:
                    throw new LoginChoice.InvalidChoiceException();
        }
    }

    private static User loginWithFacebook(String username, String[] password) {
        LoginProvider provider = new FacebookLoginProvider();
        return login(username, password, provider);
    }

    private static User loginWithTwitter(String username, String[] password) {
        LoginProvider provider = new TwitterLoginProvider();
        return login(username, password, provider);
    }

    private static User loginWithLinkedIn(String username, String[] password) {
        LoginProvider provider = new LinkedInLoginProvider();
        return login(username, password, provider);
    }

    private static User loginWithUsername(String username, String[] password) {
        LoginProvider provider = new SimpleLoginProvider();
        return login(username, password, provider);
    }

    private static User login(String username, String[] pass, LoginProvider loginProvider) {
        SessionManager sessionManager = new SessionManager(loginProvider);
        return sessionManager.login(username, pass);
    }

    public void signOut() {
        sessionManager.logout();
    }

    @NotNull
    public String getUsername() {
        return username;
    }

    @NotNull
    public String getLoginProvider() {
        return loginProvider;
    }
}
