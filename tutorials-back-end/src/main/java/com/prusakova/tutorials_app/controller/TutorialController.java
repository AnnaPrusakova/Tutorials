package com.prusakova.tutorials_app.controller;

import com.prusakova.tutorials_app.model.Tutorial;
import com.prusakova.tutorials_app.service.TutorialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {
  private final TutorialService tutorialService;

  public TutorialController(TutorialService tutorialService) {
    this.tutorialService = tutorialService;
  }

  @GetMapping("/tutorials")
  public ResponseEntity<List<Tutorial>> getAllTutorials(
      @RequestParam(required = false) String title) {
    try {
     List<Tutorial> tutorials = tutorialService.getAllTutorials(title);
      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    Tutorial newTutorial = tutorialService.getTutorialById(id);
    return new ResponseEntity<>(newTutorial, HttpStatus.OK);
  }

  @PostMapping("/tutorials")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    try {
      Tutorial newTutorial = tutorialService.createTutorial(tutorial);
      return new ResponseEntity<>(newTutorial, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> updateTutorialById(
      @PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Tutorial newTutorial = tutorialService.updateTutorialById(id, tutorial);
      return new ResponseEntity<>(newTutorial, HttpStatus.OK);
  }

  @DeleteMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> deleteTutorialById(@PathVariable("id") long id) {
    try {
      tutorialService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/tutorials")
  public ResponseEntity<Tutorial> deleteAllTutorials() {
    try {
      tutorialService.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tutorials/published")
  public ResponseEntity<List<Tutorial>> findByPublished() {
    try {
      return new ResponseEntity<>(tutorialService.findByPublished(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
