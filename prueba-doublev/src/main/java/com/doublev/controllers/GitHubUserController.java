package com.doublev.controllers;

import com.doublev.models.GitHubUser;
import com.doublev.services.GitHubUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GitHubUserController {

    @Autowired
    private GitHubUserService gitHubUserService;

    @GetMapping("/users")
    public ResponseEntity<List<GitHubUser>> searchUsers(@RequestParam String query) {
        List<GitHubUser> users = gitHubUserService.searchUsers(query);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<GitHubUser> getUserProfile(@PathVariable String username) {
        GitHubUser user = gitHubUserService.getUserProfile(username);
        return ResponseEntity.ok(user);
    }
}

