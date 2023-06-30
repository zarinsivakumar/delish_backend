package ch.bzz.delish.security;


import ch.bzz.delish.models.Login;

public class AuthResponse {

    private Login user;
    private String accessToken;

    public AuthResponse(Login user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public Login getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setUser(Login user) {
        this.user = user;
    }

}
