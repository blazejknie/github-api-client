package com.blazejknie.github_api_client.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties("com.github")
public class GithubBaseUrlConfig {
    private final String baseurl;

    @ConstructorBinding
    public GithubBaseUrlConfig(String baseurl) {
        this.baseurl = baseurl;
    }
}
