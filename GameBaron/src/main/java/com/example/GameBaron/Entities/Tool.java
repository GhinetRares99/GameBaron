package com.example.GameBaron.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table
public class Tool {

    @Id
    @SequenceGenerator(name = "tool_sequence", sequenceName = "tool_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tool_sequence")
    private Integer ToolId;
    @NotBlank(message = "The name field is mandatory!")
    private String ToolName;
    @NotBlank(message = "The description field is mandatory!")
    private String ToolDescription;
    @NotBlank(message = "The version field is mandatory!")
    private String ToolVersion;

    @JsonBackReference(value = "game-tool")
    @ManyToOne
    @JoinColumn(name = "GameId", nullable = false)
    private Game game;

    public Tool() {}

    public Tool(String toolName, String toolDescription, String toolVersion, Game game) {
        ToolName = toolName;
        ToolDescription = toolDescription;
        ToolVersion = toolVersion;
        this.game = game;
    }

    public Tool(Integer toolId, String toolName, String toolDescription, String toolVersion, Game game) {
        ToolId = toolId;
        ToolName = toolName;
        ToolDescription = toolDescription;
        ToolVersion = toolVersion;
        this.game = game;
    }

    public Integer getToolId() {
        return ToolId;
    }

    public void setToolId(Integer toolId) {
        ToolId = toolId;
    }

    public String getToolName() {
        return ToolName;
    }

    public void setToolName(String toolName) {
        ToolName = toolName;
    }

    public String getToolDescription() {
        return ToolDescription;
    }

    public void setToolDescription(String toolDescription) {
        ToolDescription = toolDescription;
    }

    public String getToolVersion() {
        return ToolVersion;
    }

    public void setToolVersion(String toolVersion) {
        ToolVersion = toolVersion;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "ToolId=" + ToolId +
                ", ToolName='" + ToolName + '\'' +
                ", ToolDescription='" + ToolDescription + '\'' +
                ", ToolVersion='" + ToolVersion + '\'' +
                ", game=" + game +
                '}';
    }
}
