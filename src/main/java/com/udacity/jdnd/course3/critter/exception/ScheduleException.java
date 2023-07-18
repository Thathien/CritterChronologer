package com.udacity.jdnd.course3.critter.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Schedule is not found")
public class ScheduleException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ScheduleException(String message) {
		super(message);
	}
}
