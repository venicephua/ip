package nova.task;

import java.util.ArrayList;
import nova.ui.Ui;
import nova.exception.NovaException;

/**
 * Manages a collection of tasks and operations on them.
 * Handles adding, removing, marking, unmarking, and listing tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;

    /**
     * Constructs a new empty TaskList with the specified UI.
     * Initializes the tasks collection as an empty ArrayList.
     *
     * @param ui UI instance for displaying messages
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Constructs a new TaskList with existing tasks and UI reference.
     *
     * @param tasks ArrayList of existing Task objects
     * @param ui UI instance for displaying messages
     */
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param task The Task object to add
     */
    public void addTask(Task task, boolean isPrint) {
        tasks.add(task);
        if (isPrint) {
            ui.printMessage("Now we have " + tasks.size() + ((tasks.size() == 1) ? " task!" : " tasks!"));
        }
    }

    /**
     * Removes a task from the list by its ID and displays a confirmation message.
     *
     * @param taskId The ID of the task to remove (1-based indexing)
     * @throws NovaException If the task ID is invalid
     */
    public void removeTask(int taskId) throws NovaException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw NovaException.invalidTaskNumber();
        }

        ui.printMessage(ui.TASK_REMOVED);
        ui.printMessage(tasks.get(taskId - 1).toString());

        tasks.remove(taskId - 1);
        ui.printMessage("Now we have " + tasks.size() + ((tasks.size() == 1) ? " task!" : " tasks!"));
    }

    /**
     * Lists all tasks or displays a message if the list is empty.
     */
    public void listTasks(String message) {
        if (tasks.isEmpty()) {
            ui.printMessage(ui.EMPTY_TASK_LIST);
            return;
        }

        ui.printMessage(message);
        for (int i = 0; i < tasks.size(); i++) {
            ui.printMessage((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Marks a task as done by its ID.
     *
     * @param taskId The ID of the task to mark (1-based indexing)
     * @throws NovaException If the task ID is invalid
     */
    public void markTaskDone(int taskId) throws NovaException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw NovaException.invalidTaskNumber();
        }

        tasks.get(taskId - 1).markTaskDone();
    }

    /**
     * Unmarks a task (sets it as not done) by its ID.
     *
     * @param taskId The ID of the task to unmark (1-based indexing)
     * @throws NovaException If the task ID is invalid
     */
    public void unmarkTaskDone(int taskId) throws NovaException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw NovaException.invalidTaskNumber();
        }

        tasks.get(taskId - 1).unmarkTaskDone();
    }

    /**
     * Clears all tasks from the list or displays a message if already empty.
     */
    public void clearTasks() {
        if (tasks.isEmpty()) {
            ui.printMessage(ui.EMPTY_TASK_LIST);
            return;
        }

        tasks.clear();
        ui.printMessage(ui.TASK_LIST_CLEARED);
    }

    /**
     * Gets the list of tasks.
     *
     * @return ArrayList containing all Task objects
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}