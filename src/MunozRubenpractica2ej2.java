//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

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
}