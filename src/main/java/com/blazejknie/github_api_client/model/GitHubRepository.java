package com.blazejknie.github_api_client.model;

import com.blazejknie.github_api_client.model.deserializer.OwnerLoginDeserializer;
import com.blazejknie.github_api_client.model.serialization.Views;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@Data
@JsonPropertyOrder({"name", "owner", "branches"})
public final class GitHubRepository {

    @JsonView({Views.Public.class})
    private String name;

    @JsonProperty("owner")
    @JsonDeserialize(using = OwnerLoginDeserializer.class)
    @JsonView({Views.Public.class})
    private String ownerLogin;

    @JsonView({Views.Public.class})
    private List<GitHubBranch> branches;

    private Boolean fork;

}
