package id.ac.ui.cs.advprog.userfunctionality.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.Date;

@Table(name = "user_activity")
@Entity
@NoArgsConstructor
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "activity_time")
    private Date activityTime;

    @OneToMany
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "activity_description")
    private String activityDescription;

    public UserActivity(Date activityTime, User user, String activityDescription) {
        this.activityTime = activityTime;
        this.user = user;
        this.activityDescription = activityDescription;
    }

    // Getter and Setter methods
}
