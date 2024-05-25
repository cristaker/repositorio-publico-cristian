package com.doublev.services;

import com.doublev.models.GitHubUser;
import com.doublev.models.GitHubUserSearchResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class GitHubUserService {

    private static final String GITHUB_API_URL = "https://api.github.com/search/users?q=";

    public List<GitHubUser> searchUsers(String query) {
        String url = GITHUB_API_URL + query;
        ResponseEntity<GitHubUserSearchResponse> response = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                null,
                GitHubUserSearchResponse.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            GitHubUserSearchResponse body = response.getBody();
            if (body != null) {
                return body.getItems();
            }
        }
        return Collections.emptyList();
    }

    public GitHubUser getUserProfile(String username) {
        String url = "https://api.github.com/users/" + username;
        ResponseEntity<GitHubUser> response = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                null,
                GitHubUser.class
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }
}

