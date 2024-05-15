//package id.ac.ui.cs.advprog.userfunctionality.service;
//
//import id.ac.ui.cs.advprog.userfunctionality.model.Book;
//import id.ac.ui.cs.advprog.userfunctionality.model.UserActivity;
//import id.ac.ui.cs.advprog.userfunctionality.repository.LogActivityRepository;
//import org.apache.catalina.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//
//@Service
//public class LogUserActivityImpl implements LogUserActivityService {
//
//    private final LogActivityRepository logActivityRepository;
//
//    @Autowired
//    public LogUserActivityImpl(LogActivityRepository logActivityRepository) {
//        this.logActivityRepository = logActivityRepository;
//    }
//
//    @Override
//    public void logBookClick(User user, Book book) {
//        String username = user.getUsername();
//        Long bookId = book.getId();
//        LocalDateTime activityTime = LocalDateTime.now();
//        String activityDescription = "User " + username + " clicked book with ID " + bookId;
//        UserActivity logActivity = new UserActivity(id, activityTime);
//        logActivityRepository.save(logActivity);
//    }
//}
//
//
//
