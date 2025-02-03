public class TaskList {
    private final Task[] tasks;
    private int taskListSize;

    public TaskList(int maxListSize) {
        this.tasks = new Task[maxListSize];
        this.taskListSize = 0;
    }

    public void addTask(String taskName) {
        System.out.println(Nova.BORDER);
        if (taskListSize == tasks.length) {
            System.out.println(Nova.INDENT + "Task list is full!");
        } else {
            Task newTask = new Task();
            newTask.setName(taskName);
            tasks[taskListSize++] = newTask;
            System.out.println(Nova.INDENT + "added: " + taskName);
        }
        System.out.println(Nova.BORDER);
    }

    public void markDone(int taskId) {
        System.out.println(Nova.BORDER);
        if (taskId < 0 || taskId >= taskListSize) {
            System.out.println(Nova.INDENT + "Hm... I can't find this task :/");
        } else {
            tasks[taskId].isDone = true;
            System.out.println(Nova.INDENT + "Yay! I've marked this task done:");
            System.out.println(Nova.INDENT + tasks[taskId].getName());
        }
        System.out.println(Nova.BORDER);
    }

    public void unmarkDone(int taskId) {
        System.out.println(Nova.BORDER);
        if (taskId < 0 || taskId >= taskListSize) {
            System.out.println(Nova.INDENT + "Hm... I can't find this task :/");
        } else {
            tasks[taskId].isDone = false;
            System.out.println(Nova.INDENT + "Okay, I've unmarked this task:");
            System.out.println(Nova.INDENT + tasks[taskId].getName());
        }
        System.out.println(Nova.BORDER);
    }

    public void printTaskList() {
        System.out.println(Nova.BORDER);
        if (taskListSize == 0) {
            System.out.println(Nova.INDENT + "To do list is empty! Woohoo! ^o^");
        } else {
            System.out.println(Nova.INDENT + "What should we do today?");
            for (int i = 0; i < taskListSize; i++) {
                System.out.println(Nova.INDENT + (i + 1) + ". " + tasks[i].getName());
            }
        }
        System.out.println(Nova.BORDER);
    }
}
