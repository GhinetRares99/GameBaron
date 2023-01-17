package com.example.GameBaron.Controllers;

import com.example.GameBaron.Entities.Badge;
import com.example.GameBaron.Entities.Tool;
import com.example.GameBaron.Services.ToolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToolController {

    private final ToolService toolService;

    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    @RequestMapping(path = "GameBaron/Tool/getTools")
    public List<Tool> getTools(){
        return toolService.getTools();
    }

    @PostMapping
    @RequestMapping(path = "GameBaron/Tool/addTool")
    public void addTool(@Valid @RequestBody Tool tool){
        toolService.addTool(tool);
    }

    @PutMapping
    @RequestMapping(path = "GameBaron/Tool/updateTool/{toolId}")
    public void updateTool(@PathVariable Integer toolId, @RequestParam(required = false) String toolName,
                           @RequestParam(required = false) String toolDescription,
                           @RequestParam(required = false) String toolVersion){
        toolService.updateTool(toolId, toolName, toolDescription, toolVersion);
    }

    @DeleteMapping
    @RequestMapping(path = "GameBaron/Tool/deleteTool/{toolId}")
    public void deleteTool(@PathVariable("toolId") Integer toolId){
        toolService.deleteTool(toolId);
    }
}
