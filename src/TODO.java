import java.util.ArrayList;

enum Status {
    PENDING,
    STARTED,
    COMPLETED
}

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


    public boolean empty() {
        return this.completed.size() + this.pending.size() + this.started.size() == 0;
    }


    public void print() {
        if (pending.size() > 0) {
            System.out.println("Pending:");
            for (int i = 0; i < pending.size(); i++) {
                System.out.println("   [" + (i+1) + "] " + pending.get(i));
            }
        }

        if (started.size() > 0) {
            System.out.println("Started:");
            for (int i = 0; i < started.size(); i++) {
                System.out.println("\t[" + (i+1) + "] " + started.get(i));
            }
        }
        if (completed.size() > 0) {
            System.out.println("Completed:");
            for (int i = 0; i < completed.size(); i++) {
                System.out.println("\t[" + (i+1) + "] " + completed.get(i));
            }
        }
    }
}
