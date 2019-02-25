package notable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @CrossOrigin
    @RequestMapping("/notes")
    public Iterable<Note> allNotes() {
        return noteService.allNotes();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notes")
    public void addNote(@RequestBody Note note) {
        noteService.addNote(note);
    }

    @RequestMapping("/note/{id}")
    public Optional<Note> getNote(@PathVariable String id) {
        return noteService.getNote(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/note/{id}")
    public Optional<Note> updateNote(@RequestBody Note note, @PathVariable String id) {
        return noteService.updateNote(note, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/note/{id}")
    public Iterable<Note> deleteNote(@PathVariable String id) {
        return noteService.deleteNote(id);
    }
}
