package notable;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NoteController {

    private List<Note> notes = new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST, value = "/notes")
    public void addNote(@RequestBody Note note) {
        notes.add(note);
    }

    @RequestMapping("/note/{id}")
    private Note getNote(@PathVariable String id) {
        Note note =  notes.stream().filter(n -> n.getId().equals(id)).findFirst().get();
        return note;
    }
}
