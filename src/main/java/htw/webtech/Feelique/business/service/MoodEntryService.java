package htw.webtech.Feelique.business.service;

import htw.webtech.Feelique.business.repository.MoodEntryRepository;
import htw.webtech.Feelique.rest.model.MoodEntry;
import htw.webtech.Feelique.rest.model.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MoodEntryService {

    private final MoodEntryRepository moodEntryRepository;

    public MoodEntryService(MoodEntryRepository moodEntryRepository) {
        this.moodEntryRepository = moodEntryRepository;
    }

    public List<MoodEntry> getAllMoods() {
        return moodEntryRepository.findAll();
    }

    public List<MoodEntry> getMoodsByUser(User user) {
        return moodEntryRepository.findByUser(user);
    }

    public MoodEntry saveMood(MoodEntry moodEntry) {
        return moodEntryRepository.save(moodEntry);
    }

    public MoodEntry getMoodById(Long id) {
        return moodEntryRepository.findById(id).orElseThrow(() -> new RuntimeException("Eintrag nicht gefunden"));
    }
}