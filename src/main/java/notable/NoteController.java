package notable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping("/notes")
    public List<Note> allNotes() {
        List<Note> notes = new ArrayList<>();
        noteRepository.findAll().forEach(notes::add);

        return notes;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notes")
    public void addNote(@RequestBody Note note) {
        noteRepository.save(note);
    }

    @RequestMapping("/note/{id}")
    public Optional<Note> getNote(@PathVariable String id) {
        return noteRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/note/{id}")
    public Optional<Note> updateNote(@RequestBody Note note, @PathVariable String id) {
        noteRepository.save(note);

        return noteRepository.findById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/note/{id}")
    public List<Note> deleteNote(@PathVariable String id) {
        noteRepository.deleteById(id);

        List<Note> notes = new ArrayList<>();
        noteRepository.findAll().forEach(notes::add);

        return notes;
    }
}
