package com.blazejknie.github_api_client.service;

import com.blazejknie.github_api_client.client.GitHubClient;
import com.blazejknie.github_api_client.model.GitHubBranch;
import com.blazejknie.github_api_client.model.GitHubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRepositoriesService {
    private final GitHubClient gitHubClient;

    public List<GitHubRepository> fetchReposInformationForUser(String userName) {
        List<GitHubRepository> gitHubRepositories = gitHubClient.fetchGithubNotForkedRepositories(userName);
        return gitHubRepositories.stream()
                .peek(repo -> {
                    String repoName = repo.getName();
                    List<GitHubBranch> gitHubBranches = gitHubClient.fetchGithubBranches(userName, repoName);
                    repo.setBranches(gitHubBranches);
                })
                .toList();
    }
}
