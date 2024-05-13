package id.ac.ui.cs.advprog.userfunctionality.service.command;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;

import java.util.Optional;

public interface Command {
    Optional<ReviewRating> execute();
}