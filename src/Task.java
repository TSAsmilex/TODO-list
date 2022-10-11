import java.util.InputMismatchException;

enum Status {
    PENDING (1),
    IN_PROGRESS (2),
    COMPLETED (3);

    private final int value;

    private Status(int s) {
        value = s;
    }

    public boolean equals(Status other) {
        return value == other.value;
    }

    public int getValue() {
        return value;
    }

    public static Status fromValue(int value) {
        for (Status status : Status.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }

        throw new InputMismatchException("Invalid status");
    }

    public static void printToMenu() {
        System.out.println("   1) No iniciada");
        System.out.println("   2) En progreso");
        System.out.println("   3) Completada");
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

    public Task (String description, Status status) {
        this.description = description;
        this.status      = status;
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
