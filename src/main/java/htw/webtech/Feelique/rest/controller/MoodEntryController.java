package htw.webtech.Feelique.rest.controller;

import htw.webtech.Feelique.business.service.MoodEntryService;
import htw.webtech.Feelique.rest.model.MoodEntry;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/moods")
@CrossOrigin(origins = "http://localhost:5173")
public class MoodEntryController {

    private final MoodEntryService service;

    public MoodEntryController(MoodEntryService service) {
        this.service = service;
    }

    // 1) Alle anzeigen
    @GetMapping
    public List<MoodEntry> getAll() {
        return service.getAll();
    }

    // 2) Einzelnen anzeigen
    @GetMapping("/{id}")
    public MoodEntry getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // 3) Erstellen
    @PostMapping
    public MoodEntry create(@Valid @RequestBody MoodEntry moodEntry) {
        return service.create(moodEntry);
    }

    // 4) Bearbeiten
    @PutMapping("/{id}")
    public MoodEntry update(@PathVariable Long id, @Valid @RequestBody MoodEntry updated) {
        return service.update(id, updated);
    }

    // 5) Löschen
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // 6) Kalender/Datum: /moods/by-date?date=2026-01-16
    @GetMapping("/by-date")
    public List<MoodEntry> byDate(@RequestParam String date) {
        return service.getByDate(LocalDate.parse(date));
    }

    // 7) Suche: /moods/search?q=stress
    @GetMapping("/search")
    public List<MoodEntry> search(@RequestParam String q) {
        return service.search(q);
    }

    // Bonus: Filter nach Mood: /moods/filter?mood=Traurig
    @GetMapping("/filter")
    public List<MoodEntry> filter(@RequestParam String mood) {
        return service.filterByMood(mood);
    }
}
