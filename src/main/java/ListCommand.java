public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.print();
    }
}