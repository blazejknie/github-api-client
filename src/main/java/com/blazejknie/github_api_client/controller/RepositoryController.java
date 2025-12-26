package com.blazejknie.github_api_client.controller;

import com.blazejknie.github_api_client.exception.NotFoundException;
import com.blazejknie.github_api_client.model.GitHubRepository;
import com.blazejknie.github_api_client.model.NotFoundRepositoriesResponse;
import com.blazejknie.github_api_client.model.serialization.Views;
import com.blazejknie.github_api_client.service.UserRepositoriesService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RepositoryController {
    private final UserRepositoriesService userRepositoriesService;

    @JsonView(Views.Public.class)
    @GetMapping("/api/v1/repositories/{userName}")
    public ResponseEntity<List<GitHubRepository>> findRepositoriesForUser(@PathVariable String userName) {
        return ResponseEntity.ok(userRepositoriesService.fetchReposInformationForUser(userName));
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<NotFoundRepositoriesResponse> handleNotFoundRepositoriesException(NotFoundException e) {
        NotFoundRepositoriesResponse response = new NotFoundRepositoriesResponse("404", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler({RestClientException.class})
    public ResponseEntity<String> handleRestClientException(NotFoundException e) {
        NotFoundRepositoriesResponse response = new NotFoundRepositoriesResponse("404", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
