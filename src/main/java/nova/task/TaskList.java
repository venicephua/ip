package nova.task;

import java.util.ArrayList;
import nova.ui.Ui;
import nova.exception.NovaException;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void addTask(Task task) {
        tasks.add(task);
        ui.printMessage("Now we have " + tasks.size() + ((tasks.size() == 1) ? " task!" : " tasks!"));
    }

    public void removeTask(int taskId) throws NovaException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw NovaException.invalidTaskNumber();
        }

        ui.printMessage(ui.TASK_REMOVED);
        ui.printMessage(tasks.get(taskId - 1).toString());

        tasks.remove(taskId - 1);
        ui.printMessage("Now we have " + tasks.size() + ((tasks.size() == 1) ? " task!" : " tasks!"));
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            ui.printMessage(ui.EMPTY_TASK_LIST);
            return;
        }

        ui.printMessage("Hey girl~ What should we do today? 🤔");
        for (int i = 0; i < tasks.size(); i++) {
            ui.printMessage((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    public void markTaskDone(int taskId) throws NovaException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw NovaException.invalidTaskNumber();
        }

        tasks.get(taskId - 1).markTaskDone();
    }

    public void unmarkTaskDone(int taskId) throws NovaException {
        if (taskId < 1 || taskId > tasks.size()) {
            throw NovaException.invalidTaskNumber();
        }

        tasks.get(taskId - 1).unmarkTaskDone();
    }

    public void clearTasks() {
        if (tasks.isEmpty()) {
            ui.printMessage(ui.EMPTY_TASK_LIST);
            return;
        }

        tasks.clear();
        ui.printMessage(ui.TASK_LIST_CLEARED);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}