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

    public MoodEntry() {}

    public MoodEntry(String mood, LocalDateTime time, String note) {
        this.mood = mood;
        this.time = time;
        this.note = note;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
