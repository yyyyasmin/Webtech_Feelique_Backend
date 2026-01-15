package htw.webtech.Feelique.business.repository;

import htw.webtech.Feelique.rest.model.MoodEntry;
import htw.webtech.Feelique.rest.model.User; // WICHTIG: Import der User-Klasse
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // WICHTIG: Import der Liste

@Repository
public interface MoodEntryRepository extends JpaRepository<MoodEntry, Long> {
    // Findet alle Stimmungseinträge, die zu einem bestimmten Benutzer gehören
    List<MoodEntry> findByUser(User user);
}