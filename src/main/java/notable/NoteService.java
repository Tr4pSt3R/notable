package notable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public void addNote(Note note) {
        noteRepository.save(note);
    }

    public Optional<Note> getNote(String id) {
        return noteRepository.findById(id);
    }

    public Optional<Note> updateNote(Note note, String id) {
        noteRepository.save(note);

        return noteRepository.findById(id);
    }

    public Iterable<Note> deleteNote(String id) {
        noteRepository.deleteById(id);

        return noteRepository.findAll();
    }

    public Iterable<Note> allNotes() {
        return noteRepository.findAll();
    }
}
