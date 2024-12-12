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

