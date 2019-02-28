package notable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @CrossOrigin
    @RequestMapping("/api/notes")
    public Iterable<Note> allNotes() {
        return noteService.allNotes();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, value = "/api/notes")
    public void addNote(@RequestBody Note note) {
        noteService.addNote(note);
    }

    @RequestMapping("/api/note/{id}")
    public Optional<Note> getNote(@PathVariable String id) {
        return noteService.getNote(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/api/note/{id}")
    public Optional<Note> updateNote(@RequestBody Note note, @PathVariable String id) {
        return noteService.updateNote(note, id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, value="/api/note/{id}")
    public Iterable<Note> deleteNote(@PathVariable String id) {
        return noteService.deleteNote(id);
    }
}
