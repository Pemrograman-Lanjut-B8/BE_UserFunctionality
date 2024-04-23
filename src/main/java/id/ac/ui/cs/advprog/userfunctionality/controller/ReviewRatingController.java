package id.ac.ui.cs.advprog.userfunctionality.controller;

import id.ac.ui.cs.advprog.userfunctionality.model.ReviewRating;
import id.ac.ui.cs.advprog.userfunctionality.service.ReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
public class ReviewRatingController {

    @Autowired
    private ReviewRatingService reviewRatingService;

    @GetMapping("/create")
    public String createReviewRatingPage(Model model) {
        ReviewRating reviewRating = new ReviewRating();
        model.addAttribute("reviewRating", reviewRating);
        return "CreateReviewRating";
    }

    @PostMapping("/create")
    public String createReviewRatingPost(@ModelAttribute ReviewRating reviewRating, Model model) {
        reviewRatingService.create(reviewRating);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String reviewRatingListPage(Model model) {
        List<ReviewRating> allReviewRatings = reviewRatingService.findAll();
        model.addAttribute("reviewRatings", allReviewRatings);
        return "ReviewRatingList";
    }

    @GetMapping("/edit/{reviewId}")
    public String editReviewRatingPage(Model model, @PathVariable("reviewId") String reviewId) {
        Optional<ReviewRating> reviewRating = reviewRatingService.findById(reviewId);
        reviewRating.ifPresent(value -> model.addAttribute("reviewRating", value));
        return "EditReviewRating";
    }

    @PostMapping("/edit/{reviewId}")
    public String editReviewRatingPost(@ModelAttribute ReviewRating reviewRating, Model model, @PathVariable("reviewId") String reviewId) {
        reviewRatingService.update(reviewId, reviewRating);
        return "redirect:/review/list";
    }

    @GetMapping("/delete/{reviewId}")
    public String deleteReviewRatingGet(Model model, @PathVariable("reviewId") String reviewId) {
        reviewRatingService.delete(reviewId);
        return "redirect:/review/list";
    }
}