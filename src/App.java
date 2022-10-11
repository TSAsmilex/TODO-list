import java.util.InputMismatchException;
import java.util.Scanner;

enum MenuOptions {
    NEW_TASK(1),
    UPDATE_STATUS_TASK(2),
    DELETE_TASK(3),
    DELETE_COMPLETED(4),
    EXIT(5);

    private final int value;

    private MenuOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuOptions fromValue(int value) {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }

        throw new InputMismatchException("Invalid option");
    }

    public static void printMenu() {
        System.out.println("   1) Añadir una nueva tarea");
        System.out.println("   2) Cambiar el estado de una tarea");
        System.out.println("   3) Borrar una tarea");
        System.out.println("   4) Limpiar las tareas completadas");
        System.out.println("   5) Salir");
    }
}


public class App {
    static TODO todolist = new TODO();

    public static void main(String[] args) throws Exception {
        System.out.print("¡Hola, persona! ");

        var option = MenuOptions.EXIT;
        Exception error = null;


        do {
            clearScreen();

            if  (error != null) {
                System.err.println("Se ha producido un error. Motivo:\t" + error.getMessage() + "\n");
            }

            if (!todolist.empty()) {
                System.out.println("Esta es tu lista de tareas actual:");
                todolist.print();
            }
            else {
                System.out.println("Todavía no tienes tareas pendientes.");
            }

            System.out.println("\n¿Qué quieres hacer?");
            MenuOptions.printMenu();

            Scanner scan = new Scanner(System.in);
            error = null;

            try {
                System.out.print("> ");
                option = MenuOptions.fromValue(scan.nextInt());
            }
            catch (InputMismatchException e) {
                error = new InputMismatchException("Introduce un comando válido.");
            }

            switch (option) {
                case NEW_TASK -> newTask(scan);

                case UPDATE_STATUS_TASK -> {
                    try {
                        updateStatusTask(scan);
                    }
                    catch (InputMismatchException e) {
                        error = e;
                    }
                }

                case DELETE_TASK -> {
                    try {
                        deleteTask(scan);
                    }
                    catch (InputMismatchException e) {
                        error = e;
                    }
                }

                case DELETE_COMPLETED -> todolist.deleteCompleted();
                case EXIT             -> System.out.println("¡Hasta luego!");
            }
        } while (option != MenuOptions.EXIT || error != null);
    }


    public static void newTask(Scanner scan) {
        System.out.print("\n¿De qué trata la tarea?\n> ");
        scan.nextLine();
        String description = scan.nextLine();
        todolist.add(new Task(description));
    }


    public static boolean updateStatusTask (Scanner scan) throws InputMismatchException {
        System.out.println("\n¿Qué tarea ha recibido cambios?");

        int id = 0;

        try {
            System.out.print("> ");
            id = scan.nextInt();
        }
        catch (InputMismatchException e) {
            throw new InputMismatchException("Introduce un número.");
        }

        if (!todolist.get(id).isPresent()) {
            throw new InputMismatchException("No existe una tarea con ese ID.");
        }

        System.out.println("\n¿Cuál es su nuevo estado?");
        Status.printToMenu();

        Status state = null;

        try {
            System.out.print("> ");
            state = Status.fromValue(scan.nextInt());
        }
        catch (InputMismatchException e) {
            throw new InputMismatchException("No existe un estado válido con dicho valor");
        }

        return todolist.updateStatus(id, state);
    }


    static boolean deleteTask(Scanner scan) {
        System.out.println("\n¿Qué tarea quieres eliminar?");
        System.out.print("> ");

        try {
            int id = scan.nextInt();
            return todolist.delete(id);
        }
        catch (InputMismatchException e) {
            throw new InputMismatchException("No existe una tarea con ese ID");
        }
    }


    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
