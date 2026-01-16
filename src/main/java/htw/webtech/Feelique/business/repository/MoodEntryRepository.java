package htw.webtech.Feelique.business.repository;

import htw.webtech.Feelique.rest.model.MoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {

    // Kalender/Tag-Ansicht: Einträge in Zeitraum (z.B. Tagesbeginn bis Tagesende)
    List<MoodEntry> findByTimeBetween(LocalDateTime start, LocalDateTime end);

    // Suche: im Mood oder Note
    List<MoodEntry> findByMoodContainingIgnoreCaseOrNoteContainingIgnoreCase(String mood, String note);

    // Filter nach Mood
    List<MoodEntry> findByMoodIgnoreCase(String mood);
}
