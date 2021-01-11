package edu.upc.dsa.minimo2;

public class Usuario {
    private String public_repos;
    private String followers;
    private String login;
    private String avatar_url;
    private String following;



    public Usuario(String name, String repos, String followers, String avatar)
    {
        this.login = name;
        this.public_repos = repos;
        this.followers = followers;
        this.avatar_url = avatar;


    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public Usuario() {
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
