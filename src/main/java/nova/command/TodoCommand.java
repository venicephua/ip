package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.Task;
import nova.task.TaskList;
import nova.task.Todo;
import nova.ui.Ui;

public class TodoCommand extends Command {
    private final String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        Task todo = new Todo(taskName, false, ui);
        tasks.addTask(todo);
        ui.printMessage(ui.NEW_TASK_ADDED);
        ui.printMessage(todo.toString());
        storage.saveTasks(tasks.getTasks());
    }
}