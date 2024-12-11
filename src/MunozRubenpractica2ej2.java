//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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

    // Uso de metodo para marcar como completada
    public void markAsCompleted() {
        this.isCompleted = true;
    }
    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    // Uso otro metodo para enseñar el estado de la tarea

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
}
