package com.doublev.models;

import lombok.Data;

@Data
public class GitHubUser {
    private String login;
    private String avatarUrl;
    private String htmlUrl;
    private String name;
    private String location;
    private String bio;
    private int publicRepos;
    private int followers;
    private int following;
}
