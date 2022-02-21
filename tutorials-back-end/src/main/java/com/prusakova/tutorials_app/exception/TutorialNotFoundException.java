package com.prusakova.tutorials_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TutorialNotFoundException extends RuntimeException {
  public TutorialNotFoundException() {}
}
