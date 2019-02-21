package notable;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NoteController {

    private List<Note> notes = new ArrayList<>();

    @RequestMapping("/notes")
    public List<Note> allNotes() {
        return notes;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/notes")
    public void addNote(@RequestBody Note note) {
        notes.add(note);
    }

    @RequestMapping("/note/{id}")
    public Note getNote(@PathVariable String id) {
        Note note =  notes.stream().filter(n -> n.getId().equals(id)).findFirst().get();
        return note;
    }

    @RequestMapping(method = RequestMethod.PUT, value="/note/{id}")
    public Note updateNote(@RequestBody Note note, @PathVariable String id) {
        for (int i = 0; i < notes.size(); i++) {
            Note n = notes.get(i);
            if (n.getId().equals(id)) {
                notes.set(i, note);
            }
        }

        return getNote(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/note/{id}")
    public List<Note> deleteNote(@PathVariable String id) {
        notes.removeIf(n -> n.getId().equals(id));

        return notes;
    }
}
