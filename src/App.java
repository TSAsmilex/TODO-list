import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        var todolist = new TODO();

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
                case 1 -> {
                    System.out.print("¿De qué trata la tarea?\n> ");
                    scan.nextLine();
                    String task = scan.nextLine();
                    todolist.add(task, Status.PENDING);
                }
                case 4 -> {
                    exit = true;
                }
            }
        }
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
