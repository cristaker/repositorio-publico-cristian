package com.doublev.models;

import lombok.Data;

import java.util.List;

@Data
public class GitHubUserSearchResponse {
    private List<GitHubUser> items;
}
