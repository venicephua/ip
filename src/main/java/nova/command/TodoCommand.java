package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.Task;
import nova.task.TaskList;
import nova.task.Todo;
import nova.ui.Ui;

/**
 * Command to create a new todo task.
 * When executed, creates an todo task with the specified name,
 * adds it to the task list, and updates the storage.
 */
public class TodoCommand extends Command {
    private final String taskName;

    /**
     * Constructs a new TodoCommand with the specified task name and time period.
     *
     * @param taskName The name/description of the event task
     */
    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes the todo command by creating a new todo task,
     * adding it to the task list, displaying a confirmation message,
     * and saving the updated task list.
     *
     * @param tasks The task list to add the todo to
     * @param ui The UI for displaying confirmation messages
     * @param storage The storage to save the updated task list
     * @throws NovaException If an error occurs during task creation or storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        Task todo = new Todo(taskName, false, ui);
        tasks.addTask(todo);
        ui.printMessage(ui.NEW_TASK_ADDED);
        ui.printMessage(todo.toString());
        storage.saveTasks(tasks.getTasks());
    }
}