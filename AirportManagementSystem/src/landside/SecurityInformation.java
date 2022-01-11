package landside;

public class SecurityInformation {
    private String title;
    private final int id;
    private String description;
    private int priority;

    public SecurityInformation(String title, int id, String description, int priority) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}



