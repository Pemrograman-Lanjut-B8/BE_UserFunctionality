package id.ac.ui.cs.advprog.userfunctionality.service;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;

import java.util.Optional;

public interface Command {
    Optional<ReviewRating> execute();
}