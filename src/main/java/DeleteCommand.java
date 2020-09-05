import java.io.IOException;

/**
 * Command that deletes a task and updates the storage.
 */
public class DeleteCommand extends Command {
    int index;

    /**
     * Constructor.
     * @param index index integer of the TaskList containing the Task to be removed.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Remove a Task to the TaskList an d update the file.
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @throws IOException If file don't exist.
     * @throws DukeException If input is not recognised.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task deletedTask = taskList.remove(index);
        ui.printDeleteMessage(deletedTask, taskList);
        super.execute(taskList, ui, storage);
    }
}
