import java.util.HashSet;
import java.util.Optional;


public class TODO {
    HashSet<Task> tasks = new HashSet<Task>();

    public void add (Task task) {
        tasks.add(task);
    }


    public Optional<Task> get (int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    public boolean delete (int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }


    public void deleteCompleted () {
        tasks.removeIf(tasks -> tasks.getStatus().equals(Status.COMPLETED));
    }


    public boolean edit (int id, String description) {
        Optional<Task> task = get(id);

        if (task.isPresent()) {
            task.get().setDescription(description);
            return true;
        }
        return false;
    }


    public boolean updateStatus (int id, Status newStatus) {
        for (var task: tasks) {
            if (task.getId() == id) {
                task.setStatus(newStatus);
                return true;
            }
        }

        return false;
    }


    public boolean empty() {
        return tasks.isEmpty();
    }


    public int size() {
        return tasks.size();
    }


    public void print() {
        var pending = tasks.stream().filter(task -> task.getStatus().equals(Status.PENDING)).toList();

        if (!pending.isEmpty()) {
            System.out.println("Pendientes:");
            for (var task : pending) {
                System.out.println("\t[" + task.getId() + "] " + task.getDescription());
            }
        }

        var inProgress = tasks.stream().filter(task -> task.getStatus().equals(Status.IN_PROGRESS)).toList();

        if (!inProgress.isEmpty()) {
            System.out.println("En progreso:");

            for (var task : inProgress) {
                System.out.println("\t[" + task.getId() + "] " + task.getDescription());
            }
        }

        var completed = tasks.stream().filter(task -> task.getStatus().equals(Status.COMPLETED)).toList();
        if (!completed.isEmpty()) {
            System.out.println("Completadas:");

            for (var task : completed) {
                System.out.println("\t[" + task.getId() + "] " + task.getDescription());
            }
        }
    }
}
