public class Note {
    public String title = null;
    public String contents = null;

    public Note(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public boolean isEmpty() {
        return (title == null && contents == null) || (title == "" && contents == "");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Note)) {
            return false;
        }

        Note noteObj = (Note) obj;
        return (title == noteObj.title) && (contents == noteObj.contents);
        // return (title == noteObj.title);
    }
}