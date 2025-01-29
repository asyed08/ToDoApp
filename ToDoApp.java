import java.util.Scanner;

public class ToDoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();  // Tasks will be loaded from file here
        
        while (true) {
            System.out.println("\n--- ToDo App ---");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. View All Tasks");
            System.out.println("4. View Pending Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter due date (e.g., YYYY-MM-DD): ");
                    String dueDate = scanner.nextLine();
                    taskManager.addTask(name, dueDate);
                    break;
                case 2:
                    System.out.print("Enter task name to mark as completed: ");
                    String taskName = scanner.nextLine();
                    taskManager.markTaskAsCompleted(taskName);
                    break;
                case 3:
                    taskManager.displayTasks();
                    break;
                case 4:
                    taskManager.displayPendingTasks();
                    break;
                case 5:
                    System.out.println("Exiting app...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
