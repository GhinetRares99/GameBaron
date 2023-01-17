package com.example.GameBaron.Controllers;

import com.example.GameBaron.Entities.Possession;
import com.example.GameBaron.Services.PossessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PossessionController {

    private final PossessionService possessionService;

    @Autowired
    public PossessionController(PossessionService possessionService) {
        this.possessionService = possessionService;
    }

    @GetMapping
    @RequestMapping(path = "GameBaron/Possession/getPossessions")
    public List<Possession> getPossessions(){
        return possessionService.getPossessions();
    }

    @PostMapping
    @RequestMapping(path = "GameBaron/Possession/addPossession")
    public void addPossession(@Valid @RequestBody Possession possession){ possessionService.addPossession(possession); }

    @DeleteMapping
    @RequestMapping(path = "GameBaron/Possession/deletePossession/{possessionId}")
    public void deletePossession(@PathVariable("possessionId") Integer possessionId){ possessionService.deletePossession(possessionId);}
}
