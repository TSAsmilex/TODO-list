enum Status {
    PENDING (1),
    INPROGRESS (2),
    COMPLETED (3);

    private final int value;

    private Status(int s) {
        value = s;
    }

    public boolean equals(Status other) {
        return value == other.value;
    }

    public static int minValue() {
        return 1;
    }
    public static int maxValue() {
        return 3;
    }
}


public class Task {
    private String description;
    private Status status;
    private int id;
    private static int nextId = 1;

    public Task(String description) {
        this.description = description;
        this.status      = Status.PENDING;
        this.id          = nextId++;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d) %s (%s)", id, description, status);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Task && ((Task) obj).id == id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
