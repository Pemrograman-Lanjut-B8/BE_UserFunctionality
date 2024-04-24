package id.ac.ui.cs.advprog.userfunctionality.controller;

import ch.qos.logback.core.model.Model;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ModelAndView showLandingPage() {
        List<Book> books = bookService.getAllBooks();
        ModelAndView modelAndView = new ModelAndView("LandingPage");
        modelAndView.addObject("books", books);
        return modelAndView;
    }
}
