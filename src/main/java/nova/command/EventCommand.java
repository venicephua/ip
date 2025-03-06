package nova.command;

import nova.exception.NovaException;
import nova.storage.Storage;
import nova.task.Event;
import nova.task.Task;
import nova.task.TaskList;
import nova.ui.Ui;

public class EventCommand extends Command {
    private final String taskName;
    private final String from;
    private final String to;

    public EventCommand(String taskName, String from, String to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NovaException {
        Task event = new Event(taskName, false, ui, from, to);
        tasks.addTask(event);
        ui.printMessage(ui.NEW_TASK_ADDED);
        ui.printMessage(event.toString());
        storage.saveTasks(tasks.getTasks());
    }
}