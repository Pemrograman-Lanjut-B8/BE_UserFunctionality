package id.ac.ui.cs.advprog.userfunctionality.controller;
import id.ac.ui.cs.advprog.userfunctionality.model.Book;
import id.ac.ui.cs.advprog.userfunctionality.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class LandingPageController {
    @Autowired
    private BookService bookService;

    public LandingPageController(BookService bookService) {
        this.bookService = bookService;
    }

//    @GetMapping("/list")
//    public String showLandingPage(Model model) {
//        List<Book> books = bookService.getAllBooks();
//        model.addAttribute("books", books);
//        return "LandingPage";
//    }
}