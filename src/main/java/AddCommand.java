import java.io.IOException;

/**
 * Command that adds a new task and updates the storage.
 */
public class AddCommand extends Command {
    String[] parsedStrings;

    /**
     * Constructor.
     * @param parsedStrings Array of the different parameters of a Task input.
     */
    public AddCommand(String[] parsedStrings) {
        this.parsedStrings = parsedStrings;
    }

    /**
     * Adds a Task to the TaskList and update the file.
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @throws IOException If file don't exist.
     * @throws DukeException If input is not recognised.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String taskType = parsedStrings[0];
        String description = parsedStrings[1];
        String due;
        Task newTask;
        switch (taskType) {
            case "todo":
                newTask = new TodoTask(description);
                break;
            case "deadline":
                due = parsedStrings[2];
//                System.out.println(description);
//                System.out.println(due);
                newTask = new DeadlineTask(description, due);
                break;
            case "event":
                due = parsedStrings[2];
                newTask = new EventTask(description, due);
                break;
            default:
                throw new DukeException("I don't know what type of task this is :(");
        }
        taskList.add(newTask);
        ui.printAddMessage(newTask, taskList);
        super.execute(taskList, ui, storage);
    }
}
