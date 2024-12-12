import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// Enumeración para la prioridad de los eventos
enum Priority {
    HIGH, MEDIUM, LOW;
}

// Clase que representa una tarea asociada a un evento
class EventTask {
    private String text;
    private boolean isCompleted;

    public EventTask(String text) {
        this.text = text;
        this.isCompleted = false;
    }

    // Marcar la tarea como completada
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    // Desmarcar la tarea como completada
    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    //  toString para mostrar detalles de la tarea

    public String toString() {
        return "Tarea: " + text + " - " + (isCompleted ? "Completada" : "No completada");
    }
}

// Clase que representa un evento
class Event {
    private String title;
    private LocalDate date;
    private Priority priority;
    private ArrayList<EventTask> tasks;

    public Event(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.tasks = new ArrayList<>();
    }

    //  agregar una tarea al evento
    public void addTask(EventTask task) {
        tasks.add(task);
    }

    //  toString para mostrar los detalles del evento

    public String toString() {
        int completedTasks = (int) tasks.stream().filter(EventTask::isCompleted).count();
        return "Evento: " + title + "\nFecha: " + date + "\nPrioridad: " + priority +
                "\nTareas: " + completedTasks + "/" + tasks.size();
    }

    // Getter para el título
    public String getTitle() {
        return title;
    }

    //  para obtener la lista de tareas
    public ArrayList<EventTask> getTasks() {
        return tasks;
    }


    // Clase principal con el menú de la aplicación
    public class Main {
        private static ArrayList<Event> events = new ArrayList<>();

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                showMenu();
                String option = scanner.nextLine();
                switch (option) {
                    case "1":
                        addEvent(scanner);
                        break;
                    case "2":
                        deleteEvent(scanner);
                        break;
                    case "3":
                        listEvents();
                        break;
                    case "4":
                        markTaskCompleted(scanner);
                        break;
                    case "5":
                        System.out.println("¡Hasta luego!");
                        return;
                    default:
                        System.out.println("Opción no válida, por favor intente nuevamente.");
                }
            }
        }
    }
}
// Mostrar el menú
private static void showMenu() {
    System.out.println("Bienvenido a Event Planner. Seleccione una opción:");
    System.out.println("[1] Añadir evento");
    System.out.println("[2] Borrar evento");
    System.out.println("[3] Listar eventos");
    System.out.println("[4] Marcar/desmarcar tarea de un evento como completada");
    System.out.println("[5] Salir");
}
// Añadir un evento
private static void addEvent(Scanner scanner) {
    System.out.println("Ingrese el título del evento:");
    String title = scanner.nextLine();

    System.out.println("Ingrese la fecha del evento (año, mes, día):");
            int year = Integer.parseInt(scanner.nextLine());
            int month = Integer.parseInt(scanner.nextLine());
            int day = Integer.parseInt(scanner.nextLine());
            LocalDate date = LocalDate.of(year, month, day);

            System.out.println("Ingrese la prioridad (HIGH, MEDIUM, LOW):");
            Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());

            Event event = new Event(title, date, priority);

            while (true) {
                System.out.println("¿Desea agregar una tarea a este evento? (sí/no):");
                String addTask = scanner.nextLine();
                if (addTask.equalsIgnoreCase("sí")) {
                    System.out.println("Ingrese la descripción de la tarea:");
                    String taskDescription = scanner.nextLine();
                    EventTask task = new EventTask(taskDescription);
                    event.addTask(task);
                } else {
                    break;
                }
            }

            events.add(event);
            System.out.println("Evento añadido con éxito.");
        }


