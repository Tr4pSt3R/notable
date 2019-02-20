package notable;

public class Note {

    private String id;
    private String content;

    public Note(String id, String content) {
        super();
        this.id = id;
        this.content = content;
    }

    public Note() {}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
