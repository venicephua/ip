package nova.command;

import nova.storage.Storage;
import nova.task.TaskList;
import nova.ui.Ui;

public class ListCommand extends Command {
    public static final String LIST_TASK_MESSAGE = "Hey girl~ What should we do today? 🤔";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(LIST_TASK_MESSAGE);
    }
}