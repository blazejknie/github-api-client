package com.blazejknie.github_api_client;

import com.blazejknie.github_api_client.config.GithubBaseUrlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(GithubBaseUrlConfig.class)
@SpringBootApplication
public class GithubApiClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubApiClientApplication.class, args);
	}

}
