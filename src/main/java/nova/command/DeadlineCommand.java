package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.Deadline;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Ui;

public class DeadlineCommand extends Command {
    private final String taskName;
    private final String by;

    public DeadlineCommand(String taskName, String by) {
        this.taskName = taskName;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        Task deadline = new Deadline(taskName, false, ui, by);
        tasks.addTask(deadline);
        ui.printMessage(ui.NEW_TASK_ADDED);
        ui.printMessage(deadline.toString());
        storage.saveTasks(tasks.getTasks());
    }
}