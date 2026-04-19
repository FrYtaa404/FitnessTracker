package pl.wsb.fitnesstracker.event.internal;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.event.Event;
import pl.wsb.fitnesstracker.user.api.User;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    //@MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    //@MapsId("eventId")
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "status")
    private String status;

}
