public class TaskList {
    private final Task[] tasks;
    private int taskListSize;

    public TaskList(int maxListSize) {
        this.tasks = new Task[maxListSize];
        this.taskListSize = 0;
    }

    public void addTask(String taskName) {
        if (taskListSize == tasks.length) {
            System.out.println(Nova.SPACE + "Task list is full!");
            return;
        }

        Task newTask = new Task();
        newTask.setName(taskName);
        tasks[taskListSize++] = newTask;
        System.out.println(Nova.BORDER);
        System.out.println(Nova.SPACE + "added: " + newTask.getName());
        System.out.println(Nova.BORDER);
    }

    public void printTaskList() {
        System.out.println(Nova.BORDER);
        if (taskListSize == 0) {
            System.out.println(Nova.SPACE + "To do list is empty! Woohoo! ^o^");
        } else {
            System.out.println(Nova.SPACE + "What tasks should we do today?");
            for (int i = 0; i < taskListSize; i++) {
                System.out.println(Nova.SPACE + (i + 1) + ". " + tasks[i].getName());
            }
        }
        System.out.println(Nova.BORDER);
    }
}
