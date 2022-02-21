package com.prusakova.tutorials_app.service;

import com.prusakova.tutorials_app.exception.TutorialHasNoContentException;
import com.prusakova.tutorials_app.exception.TutorialNotFoundException;
import com.prusakova.tutorials_app.model.Tutorial;
import com.prusakova.tutorials_app.repository.TutorialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

  private final TutorialRepository tutorialRepository;

  public TutorialService(TutorialRepository tutorialRepository) {
    this.tutorialRepository = tutorialRepository;
  }

  public Tutorial createTutorial(Tutorial tutorial) {
    return tutorialRepository.save(
        new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
  }

  public List<Tutorial> getAllTutorials(String title) {
    List<Tutorial> tutorials = new ArrayList<>();
    if (title == null) {
      tutorials.addAll(tutorialRepository.findAll());
    } else {
      tutorials.addAll(tutorialRepository.findByTitleContaining(title));
    }
    if (tutorials.isEmpty()) {
     throw new TutorialHasNoContentException();
    }
    return tutorials;
  }

  public Tutorial getTutorialById(long id) {
    Optional<Tutorial> tutorialOptional = tutorialRepository.findById(id);
    if(tutorialOptional.isPresent()) {
      return tutorialOptional.get();
    } else {
      throw new TutorialNotFoundException();
    }
  }

  public Tutorial updateTutorialById(long id, Tutorial tutorial) {
    Optional<Tutorial> tutorialOptional = tutorialRepository.findById(id);
    if (tutorialOptional.isPresent()) {
      Tutorial updateTutorial = tutorialOptional.get();
      updateTutorial.setTitle(tutorial.getTitle());
      updateTutorial.setDescription(tutorial.getDescription());
      updateTutorial.setPublished(tutorial.isPublished());
     return tutorialRepository.save(updateTutorial);
    } else {
      throw new TutorialNotFoundException();
    }
  }

  public void deleteById(long id) {
    tutorialRepository.deleteById(id);
  }

  public void deleteAll() {
    tutorialRepository.deleteAll();
  }

  public List<Tutorial> findByPublished() {
    List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
    if (tutorials.isEmpty()) {
     throw new TutorialHasNoContentException();
    } else {
      return tutorials;
    }
  }
}
