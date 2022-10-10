import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    static TODO todolist = new TODO();
    public static void main(String[] args) throws Exception {
        System.out.print("¡Hola, persona! ");
        boolean exit = false;

        while (!exit) {

            clearScreen();

            if (!todolist.empty()) {
                System.out.print("Esta es tu lista de tareas actual:\n");
                todolist.print();
            }
            else {
                System.out.print("Todavía no tienes tareas pendientes.\n");
            }

            System.out.print(
                    "\n¿Qué quieres hacer?\n"
                +   "   1) Nueva tarea\n"
                +   "   2) Cambiar estado\n"
                +   "   3) Eliminar\n"
                +   "   4) Salir\n"
            );

            Scanner scan = new Scanner(System.in);
            int option = 0;

            try {
                do {
                    System.out.print("> ");
                    option = scan.nextInt();
                } while (option < 1 || option > 4);
            }
            catch (InputMismatchException e) {
                scan.nextLine();
            }

            switch (option) {
                case 1 -> addTask(scan);
                case 2 -> modifyTask(scan);
                case 3 -> deleteTask(scan);
                case 4 -> exit = true;
            }
        }
    }


    public static void addTask(Scanner scan) {
        System.out.print("¿De qué trata la tarea?\n> ");
        scan.nextLine();
        String task = scan.nextLine();
        todolist.add(task, Status.PENDING);
    }


    public static void modifyTask (Scanner scan) {
        System.out.println("¿Qué tarea ha recibido cambios?");

        int index = 0, state = 0;

        try {
            System.out.print("> ");
            index = scan.nextInt();
        }
        catch (InputMismatchException e) {
            scan.nextLine();
        }

        if (index <= 0 || index > todolist.total()) {
            System.out.println("No existe esa tarea.");
        }
        else {
            System.out.println(
                    "¿Cuál es su nuevo estado?\n"
                +   "   1) Pendiente\n"
                +   "   2) En progreso\n"
                +   "   3) Completada\n"
            );

            try {
                System.out.print("> ");
                state = scan.nextInt();
            }
            catch (InputMismatchException e) {
                scan.nextLine();
            }
        }

        if (state < Status.minValue() || state > Status.maxValue()) {
            System.out.println("No existe ese estado.");
        }
        else {
            Status status = Status.values()[state];
            todolist.modify(index, status);
        }


    }


    static void deleteTask(Scanner scan) {
        System.out.println("¿Qué tarea quieres eliminar?");
        int index = -1;

        try {
            index = scan.nextInt();
            System.out.print("> ");
        }
        catch (InputMismatchException e) {
            scan.nextLine();
        }

        todolist.delete(index-1);
    }


    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
