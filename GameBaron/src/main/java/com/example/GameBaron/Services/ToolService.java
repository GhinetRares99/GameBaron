package com.example.GameBaron.Services;

import com.example.GameBaron.Entities.Game;
import com.example.GameBaron.Entities.Tool;
import com.example.GameBaron.Repositories.GameRepository;
import com.example.GameBaron.Repositories.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ToolService {

    private final ToolRepository toolRepository;
    private final GameRepository gameRepository;

    @Autowired
    public ToolService(ToolRepository toolRepository, GameRepository gameRepository) {
        this.toolRepository = toolRepository;
        this.gameRepository = gameRepository;
    }

    public List<Tool> getTools(){
        return toolRepository.findAll();
    }

    public Tool addTool(Tool tool){
        Optional<Game> findGameByGameId = gameRepository.findById(tool.getGame().getGameId());
        if(findGameByGameId.isEmpty()){
            throw new IllegalStateException("This game does not exist!");
        }
        toolRepository.save(tool);
        return tool;
    }

    @Transactional
    public Tool updateTool(Integer toolId, String toolName, String toolDescription, String toolVersion){
        Tool tool = toolRepository.findById(toolId).orElseThrow(() -> new IllegalStateException("The specified tool does not exist!"));

        if(toolName != null && toolName.length() > 0 && !Objects.equals(tool.getToolName(), toolName)){
            tool.setToolName(toolName);
        }

        if(toolDescription != null && toolDescription.length() > 0 && !Objects.equals(tool.getToolDescription(), toolDescription)){
            tool.setToolDescription(toolDescription);
        }

        if(toolVersion != null && toolVersion.length() > 0 && !Objects.equals(tool.getToolVersion(), toolVersion)){
            tool.setToolVersion(toolVersion);
        }
        return tool;
    }

    public Integer deleteTool(Integer toolId) {
        boolean exists = toolRepository.existsById(toolId);
        if(!exists){
            throw new IllegalStateException("The specified tool does not exist!");
        }
        toolRepository.deleteById(toolId);
        return toolId;
    }

}
