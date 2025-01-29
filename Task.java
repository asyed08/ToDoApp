public class Task {
    private String name;
    private boolean isCompleted;
    private String dueDate;

    // Constructor
    public Task(String name, String dueDate) {
        this.name = name;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    // Display task information
    @Override
    public String toString() {
        return "Task: " + name + (isCompleted ? " (Completed)" : " (Pending)") + " - Due: " + dueDate;
    }
}
