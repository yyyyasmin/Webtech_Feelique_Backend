package htw.webtech.Feelique.business.service;

import htw.webtech.Feelique.business.repository.MoodEntryRepository;
import htw.webtech.Feelique.rest.model.MoodEntry;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MoodEntryService {

    private final MoodEntryRepository repo;

    public MoodEntryService(MoodEntryRepository repo) {
        this.repo = repo;
    }

    public List<MoodEntry> getAll() {
        return repo.findAll();
    }

    public MoodEntry getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Eintrag nicht gefunden"));
    }

    public MoodEntry create(MoodEntry entry) {
        // time setzen, falls Frontend es nicht schickt
        if (entry.getTime() == null) entry.setTime(LocalDateTime.now());
        return repo.save(entry);
    }

    public MoodEntry update(Long id, MoodEntry updated) {
        MoodEntry existing = getById(id);
        existing.setMood(updated.getMood());
        existing.setNote(updated.getNote());
        if (updated.getTime() != null) existing.setTime(updated.getTime());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<MoodEntry> getByDate(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay().minusNanos(1);
        return repo.findByTimeBetween(start, end);
    }

    public List<MoodEntry> search(String q) {
        return repo.findByMoodContainingIgnoreCaseOrNoteContainingIgnoreCase(q, q);
    }

    public List<MoodEntry> filterByMood(String mood) {
        return repo.findByMoodIgnoreCase(mood);
    }
}
