package duke.commands;

import duke.util.Ui;
import duke.util.Storage;
import duke.tasks.TaskList;

/**
 * commands.Command that list out the task in the TaskList.
 */
public class ListCommand extends Command {

    /**
     * List out the task in TaskList
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @return outputString Command output.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.print();
    }
}
