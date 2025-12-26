package com.blazejknie.github_api_client.client;

import com.blazejknie.github_api_client.config.GithubBaseUrlConfig;
import com.blazejknie.github_api_client.exception.NotFoundException;
import com.blazejknie.github_api_client.model.GitHubBranch;
import com.blazejknie.github_api_client.model.GitHubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class GitHubClient {

    public static final String LIST_REPO_URI = "/users/%s/repos";
    public static final String LIST_BRANCHES_URI = "/repos/%s/%s/branches";

    private final RestTemplate restTemplate;
    private final GithubBaseUrlConfig baseUrlConfig;

    public List<GitHubRepository> fetchGithubNotForkedRepositories(String userName) {
        GitHubRepository[] repos = null;
        try {
            repos = restTemplate.getForObject(baseUrlConfig.getBaseurl() + LIST_REPO_URI.formatted(userName), GitHubRepository[].class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new NotFoundException(e.getMessage());
        }

        if (repos == null || repos.length == 0) {
            throw new NotFoundException("Not Found any Repositories for User: %s".formatted(userName));
        }

        return Stream.of(repos).filter(repo -> !repo.getFork()).collect(Collectors.toList());
    }

    public List<GitHubBranch> fetchGithubBranches(String userName, String repoName) {
        GitHubBranch[] branches = restTemplate.getForObject(
                baseUrlConfig.getBaseurl() + LIST_BRANCHES_URI.formatted(userName, repoName),
                GitHubBranch[].class
        );
        return branches == null ? Collections.emptyList() : List.of(branches);
    }
}
