package com.example.EcoSight.controllers;


import com.example.EcoSight.entity.Behaviour.Behaviour;
import com.example.EcoSight.services.BehaviourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/behaviour")
@RequiredArgsConstructor
public class BehaviourController {

    private final BehaviourService behaviourService;

    @PostMapping
    public ResponseEntity<Behaviour> addBehaviour(@RequestBody Behaviour behaviour) {
        try {
            Behaviour behaviourShown = behaviourService.addBehaviour(behaviour);
            return new ResponseEntity<>(behaviourShown, HttpStatus.CREATED);
        }

        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBehaviour(@PathVariable String name) {
        try{
            behaviourService.deleteBehaviour(name);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
