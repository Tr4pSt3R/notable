package notable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public String addNotable() {
        return "{\"content\":\"Hello World\"}";
    }
}
