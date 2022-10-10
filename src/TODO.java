import java.util.ArrayList;

enum Status {
    PENDING (1),
    STARTED (2),
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


// TODO cambiar las tres listas separadas por una única, de Task {String, Status}
public class TODO {
    ArrayList<String> pending   = new ArrayList<>();
    ArrayList<String> completed = new ArrayList<>();
    ArrayList<String> started   = new ArrayList<>();


    public void add (String task, Status status) {
        switch (status) {
            case PENDING -> pending.add(task);
            case STARTED -> started.add(task);
            case COMPLETED -> completed.add(task);
        }
    }


    public void delete (int index, Status status) {
        switch (status) {
            case PENDING -> pending.remove(index);
            case STARTED -> started.remove(index);
            case COMPLETED -> completed.remove(index);
        }
    }


    public void delete (int index) {
        if (index < pending.size()) {
            pending.remove(index);
        }
        else if (index < pending.size() + started.size()) {
            started.remove(index - pending.size());
        }
        else if (index < pending.size() + started.size() + completed.size()) {
            completed.remove(index - pending.size() - started.size());
        }
    }


    public void deleteCompleted () {
        completed.clear();
    }


    public void edit (int index, Status status, String task) {
        switch (status) {
            case PENDING -> pending.set(index, task);
            case STARTED -> started.set(index, task);
            case COMPLETED -> completed.set(index, task);
        }
    }

    public void modify(int index, Status status) {

    }

    public boolean empty() {
        return total() == 0;
    }

    public int total() {
        return this.completed.size() + this.pending.size() + this.started.size();
    }


    public void print() {
        int count = 0;

        if (pending.size() > 0) {
            System.out.println("Pending:");
            for (int i = 0; i < pending.size(); i++) {
                count++;
                System.out.println("   [" + count + "] " + pending.get(i));
            }
        }

        if (started.size() > 0) {
            System.out.println("Started:");
            for (int i = 0; i < started.size(); i++) {
                count++;
                System.out.println("\t[" + count + "] " + started.get(i));
            }
        }

        if (completed.size() > 0) {
            System.out.println("Completed:");
            for (int i = 0; i < completed.size(); i++) {
                count++;
                System.out.println("\t[" + count + "] " + completed.get(i));
            }
        }
    }
}
