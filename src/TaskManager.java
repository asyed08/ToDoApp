import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private final String fileName = "tasks.json";

    // Constructor
    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    // Add a new task
    public void addTask(String name, String dueDate) {
        Task task = new Task(name, dueDate);
        tasks.add(task);
        saveTasksToFile();  // Save after adding a task
        System.out.println("Task added: " + task);
    }

    // Mark task as completed
    public void markTaskAsCompleted(String name) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                task.markAsCompleted();
                saveTasksToFile();  // Save after updating task
                System.out.println("Task completed: " + task);
                return;
            }
        }
        System.out.println("Task not found!");
    }

    // Display all tasks
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to show.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    // Display only pending tasks
    public void displayPendingTasks() {
        boolean hasPending = false;
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                System.out.println(task);
                hasPending = true;
            }
        }
        if (!hasPending) {
            System.out.println("No pending tasks.");
        }
    }

    // Save tasks to JSON file
    private void saveTasksToFile() {
        JSONArray taskArray = new JSONArray();
        for (Task task : tasks) {
            JSONObject taskObject = new JSONObject();
            taskObject.put("name", task.getName());
            taskObject.put("dueDate", task.getDueDate());
            taskObject.put("isCompleted", task.isCompleted());
            taskArray.put(taskObject);
        }

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(taskArray.toString(4)); // Indent with 4 spaces for readability
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks from JSON file
    private void loadTasksFromFile() {
        File file = new File(fileName);
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                StringBuilder content = new StringBuilder();
                int ch;
                while ((ch = reader.read()) != -1) {
                    content.append((char) ch);
                }

                JSONArray taskArray = new JSONArray(content.toString());
                for (int i = 0; i < taskArray.length(); i++) {
                    JSONObject taskObject = taskArray.getJSONObject(i);
                    String name = taskObject.getString("name");
                    String dueDate = taskObject.getString("dueDate");
                    boolean isCompleted = taskObject.getBoolean("isCompleted");

                    Task task = new Task(name, dueDate);
                    if (isCompleted) {
                        task.markAsCompleted();
                    }
                    tasks.add(task);
                }
                System.out.println("Tasks loaded from file.");
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        } else {
            System.out.println("No existing task file found. Starting fresh.");
        }
    }
}
