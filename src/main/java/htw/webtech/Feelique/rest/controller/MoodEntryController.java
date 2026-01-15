package htw.webtech.Feelique.rest.controller;

import htw.webtech.Feelique.business.repository.UserRepository;
import htw.webtech.Feelique.business.service.MoodEntryService;
import htw.webtech.Feelique.rest.model.MoodEntry;
import htw.webtech.Feelique.rest.model.User;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moods")
public class MoodEntryController {

    private final MoodEntryService moodEntryService;
    private final UserRepository userRepository;

    public MoodEntryController(MoodEntryService moodEntryService, UserRepository userRepository) {
        this.moodEntryService = moodEntryService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<MoodEntry> getAllMoods(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return moodEntryService.getMoodsByUser(user);
    }

    @PostMapping
    public MoodEntry createMood(@Valid @RequestBody MoodEntry moodEntry, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        moodEntry.setUser(user);
        return moodEntryService.saveMood(moodEntry);
    }

    @GetMapping("/{id}")
    public MoodEntry getMoodById(@PathVariable Long id, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        MoodEntry entry = moodEntryService.getMoodById(id);

        if (!entry.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Zugriff verweigert");
        }

        return entry;
    }
}