package com.blazejknie.github_api_client.model;

import com.blazejknie.github_api_client.model.deserializer.CommitShaDeserializer;
import com.blazejknie.github_api_client.model.serialization.Views;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import tools.jackson.databind.annotation.JsonDeserialize;

@Data
public final class GitHubBranch {

    @JsonView({Views.Public.class})
    private String name;

    @JsonProperty("commit")
    @JsonDeserialize(using = CommitShaDeserializer.class)
    @JsonView({Views.Public.class})
    private String commitSha;
}
