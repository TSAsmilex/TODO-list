import java.util.InputMismatchException;
import java.util.Scanner;

enum MenuOptions {
    NEW_TASK(1),
    UPDATE_STATUS_TASK(2),
    DELETE_TASK(3),
    EXIT(4);

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
        System.out.println("   1) New task");
        System.out.println("   2) Update task");
        System.out.println("   3) Delete task");
        System.out.println("   4) Exit");
    }
}


public class App {
    static TODO todolist = new TODO();

    public static void main(String[] args) throws Exception {
        System.out.print("¡Hola, persona! ");
        var option = MenuOptions.EXIT;

        while (option != MenuOptions.EXIT) {
            clearScreen();

            if (!todolist.empty()) {
                System.out.print("Esta es tu lista de tareas actual:\n");
                todolist.print();
            }
            else {
                System.out.print("Todavía no tienes tareas pendientes.\n");
            }

            System.out.println("¿Qué quieres hacer?\n");
            MenuOptions.printMenu();

            Scanner scan = new Scanner(System.in);

            try {
                option = MenuOptions.fromValue(scan.nextInt());
            }
            catch (InputMismatchException e) {
                System.out.println("Opción inválida");
                continue;
            }

            switch (option) {
                case NEW_TASK -> newTask(scan);

                case UPDATE_STATUS_TASK -> {
                    try {
                        updateStatusTask(scan);
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Se ha producido un error. Motivo:");
                        System.out.println(e.getMessage());
                    }
                }

                case DELETE_TASK -> {
                    try {
                        deleteTask(scan);
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Se ha producido un error. Motivo:");
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }


    public static void newTask(Scanner scan) {
        System.out.print("¿De qué trata la tarea?\n> ");
        scan.nextLine();
        String description = scan.nextLine();
        todolist.add(new Task(description));
    }


    public static boolean updateStatusTask (Scanner scan) throws InputMismatchException {
        System.out.println("¿Qué tarea ha recibido cambios?");

        int id = 0;

        try {
            System.out.print("> ");
            id = scan.nextInt();
        }
        catch (InputMismatchException e) {
            throw e;
        }

        if (!todolist.get(id).isPresent()) {
            throw new InputMismatchException("No existe una tarea con ese ID");
        }

        System.out.println("¿Cuál es su nuevo estado?");
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
        System.out.println("¿Qué tarea quieres eliminar?");
        System.out.print("> ");

        try {
            int id = scan.nextInt();
            return todolist.delete(id);
        }
        catch (InputMismatchException e) {
            throw e;
        }
    }


    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
