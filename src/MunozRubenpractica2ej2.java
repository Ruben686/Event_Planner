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

    // Uso metodo para marcar la tarea como completada
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    // Uso metodo para desmarcar la tarea como completada
    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    // Uso metodo toString para mostrar detalles de la tarea

    public String toString() {
        return "Tarea: " + text + " - " + (isCompleted ? "Completada" : "No completada");
    }

    public boolean isCompleted() {
        return isCompleted;
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

    // Uso metodo para agregar una tarea al evento
    public void addTask(EventTask task) {
        tasks.add(task);
    }

    // Uso Metodo toString para mostrar los detalles del evento

    public String toString() {
        int completedTasks = (int) tasks.stream().filter(EventTask::isCompleted).count();
        return "Evento: " + title + "\nFecha: " + date + "\nPrioridad: " + priority +
                "\nTareas: " + completedTasks + "/" + tasks.size();
    }

    // Getter para el título
    public String getTitle() {
        return title;
    }

    // Uso metodo para obtener la lista de tareas
    public ArrayList<EventTask> getTasks() {
        return tasks;
    }
}

// Clase principal con el menú de la aplicación
public class MunozRubenpractica2ej2 {
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

    // Mostrar el menú
    private static void showMenu() {
        System.out.println("\nBienvenido a Event Planner. Seleccione una opción:");
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

        LocalDate date = null;
        while (date == null) {
            try {
                System.out.println("Ingrese la fecha del evento en formato DD/MM/YYYY:");
                String dateInput = scanner.nextLine();
                String[] dateParts = dateInput.split("/"); // Dividimos la fecha en día, mes y año
                int day = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int year = Integer.parseInt(dateParts[2]);

                // Intentamos crear la fecha
                date = LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("Fecha no válida. Por favor, asegúrese de usar el formato DD/MM/YYYY e intente nuevamente.");
            }
        }

        System.out.println("Ingrese la prioridad (HIGH, MEDIUM, LOW):");
        Priority priority = null;
        while (priority == null) {
            try {
                priority = Priority.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Prioridad no válida. Por favor, ingrese HIGH, MEDIUM o LOW.");
            }
        }

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

    // Borrar un evento
    private static void deleteEvent(Scanner scanner) {
        System.out.println("Ingrese el título del evento a borrar:");
        String title = scanner.nextLine();

        Event eventToDelete = null;
        for (Event event : events) {
            if (event.getTitle().equals(title)) {
                eventToDelete = event;
                break;
            }
        }

        if (eventToDelete != null) {
            events.remove(eventToDelete);
            System.out.println("Evento borrado con éxito.");
        } else {
            System.out.println("No se encontró un evento con ese título.");
        }
    }

    // Listar los eventos
    private static void listEvents() {
        if (events.isEmpty()) {
            System.out.println("No hay eventos registrados.");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    // Marcar o desmarcar tarea como completada
    private static void markTaskCompleted(Scanner scanner) {
        System.out.println("Ingrese el título del evento sobre el que desea interactuar:");
        String title = scanner.nextLine();

        Event event = null;
        for (Event e : events) {
            if (e.getTitle().equals(title)) {
                event = e;
                break;
            }
        }

        if (event != null) {
            System.out.println("Seleccione la tarea que desea marcar como completada:");
            for (int i = 0; i < event.getTasks().size(); i++) {
                System.out.println((i + 1) + ". " + event.getTasks().get(i));
            }

            try {
                int taskNumber = Integer.parseInt(scanner.nextLine()) - 1;
                if (taskNumber >= 0 && taskNumber < event.getTasks().size()) {
                    EventTask task = event.getTasks().get(taskNumber);
                    if (task.isCompleted()) {
                        task.markAsIncomplete();
                        System.out.println("Tarea desmarcada como completada.");
                    } else {
                        task.markAsCompleted();
                        System.out.println("Tarea marcada como completada.");
                    }
                } else {
                    System.out.println("Número de tarea no válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debe ser un número.");
            }
        } else {
            System.out.println("Evento no encontrado.");
        }
    }
}
