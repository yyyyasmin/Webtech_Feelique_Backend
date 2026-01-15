package htw.webtech.Feelique.rest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class MoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Stimmung darf nicht leer sein")
    @Size(min = 2, max = 50, message = "Stimmung muss zwischen 2 und 50 Zeichen lang sein")
    private String mood;

    @NotNull(message = "Zeitstempel darf nicht null sein")
    private LocalDateTime time;

    @Size(max = 500, message = "Notiz darf maximal 500 Zeichen lang sein")
    private String note;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Verknüpfung zum Benutzer

    // Konstruktoren
    public MoodEntry() {}

    public MoodEntry(String mood, LocalDateTime time, String note, User user) {
        this.mood = mood;
        this.time = time;
        this.note = note;
        this.user = user;
    }

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}