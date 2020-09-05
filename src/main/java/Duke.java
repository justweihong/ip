import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke program is a Task Management App. It takes in user command for task manipulations
 * and shows a list of Tasks that is ongoing.
 */
public class Duke {
    private String filePath;
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor.
     * @param filePath string path of the file for storage purpose.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = storage.init();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Run start the program.
     * Initialise the relevant objects to handle user commands.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String inputLine = ui.readNextLine();
                Command command = Parser.parse(inputLine);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();

            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
//            TaskList taskList = storage.init();
//            this.taskList = taskList;



//            while (true) {
//                try {
//                    String input = scanner.nextLine();
//                    if (input.equals("list")) {
//                        this.taskList.print();
//                    } else if (input.length() >= 6 && input.substring(0, 4).equals("done")) {
//                        int index = Integer.valueOf(input.substring(5)) - 1;
//                        Task currentTask = taskList.get(index);
//                        currentTask.markDone();
//                        this.taskList.set(index, currentTask);
//                        System.out.println("Nice! I've marked this task as done:");
//                        System.out.println("\t" + currentTask);
//                        this.storage.updateFile(this.taskList);
//
//                    } else if (input.length() >= 8 && input.substring(0, 6).equals("delete")) {
//                        int index = Integer.valueOf(input.substring(7)) - 1;
//                        Task currentTask = this.taskList.get(index);
//                        this.taskList.remove(index);
//                        System.out.println("Noted. I've removed this task:");
//                        System.out.println("\t" + currentTask);
//                        System.out.println("Now you have " + String.valueOf(this.taskList.size()) + " tasks in the list.");
//                        System.out.println("Now you have " + String.valueOf(this.taskList.size()) + " tasks in the list.");
////                    updateFile(filePath, taskList );
//                        this.storage.updateFile(this.taskList);
//                    } else if (input.equals("bye")) {
//                        ui.printExitMessage();
//                        break;
//                    } else if (input.length() >=4 && input.substring(0, 4).equals("todo")) {
//                        if (input.length() == 4 || (input.length() == 5 && input.substring(4,5).equals(" "))) {
//                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
//                        } else if (input.length() == 5 && !input.substring(4,5).equals(" ")) {
//                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                        }
//                        Task newTask = new TodoTask(input);
//                        this.taskList.add(newTask);
//                        System.out.println("Got it. I've added this task:");
//                        System.out.println("\t" + newTask);
//                        System.out.println("Now you have " + String.valueOf(this.taskList.size()) + " tasks in your list.");
////                    updateFile(filePath, this.taskList );
//                        this.storage.updateFile(this.taskList);
//
//                    } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
//                        if (input.length() == 8 || (input.length() == 9 && input.substring(8,9).equals(" "))) {
//                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
//                        } else if (input.length() == 9 && !input.substring(8,9).equals(" ")) {
//                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                        }
//                        String[] descDue = input.substring(9).split("/");
//                        Task newTask = new DeadlineTask(descDue[0], descDue[1]);
//                        this.taskList.add(newTask);
//                        System.out.println("Got it. I've added this task:");
//                        System.out.println("\t" + newTask);
//                        System.out.println("Now you have " + String.valueOf(this.taskList.size()) + " tasks in your list.");
////                    updateFile(filePath, this.taskList );
//                        this.storage.updateFile(this.taskList);
//
//                    } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
//                        if (input.length() == 5 || (input.length() == 6 && input.substring(5,6).equals(" "))) {
//                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
//                        } else if (input.length() == 6 && !input.substring(5,6).equals(" ")) {
//                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                        }
//                        String[] descDue = input.substring(6).split("/");
//                        Task newTask = new EventTask(descDue[0], descDue[1]);
//                        this.taskList.add(newTask);
//                        System.out.println("Got it. I've added this task:");
//                        System.out.println("\t" + newTask);
//                        System.out.println("Now you have " + String.valueOf(this.taskList.size()) + " tasks in your list.");
////                    updateFile(filePath, this.taskList );
//                        this.storage.updateFile(this.taskList);
//                    } else {
//                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    }
//                } catch (DukeException | IOException err) {
//                    ui.printErrorMessage(err.getMessage());
//                }
//            }
    }

    /** Initialise empty duke.txt if not found, else initialise the taskList based on existing duke.txt **/
    public static void initFile(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);

    }
    /** Updates the file in hard disk based on the new taskList. **/
    public static void updateFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int index = 0; index < taskList.size(); index++) {
            Task task = taskList.get(index);
            fileWriter.write(task.fileString() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Main program to run the application.
     * @param args
     */
    public static void main(String[] args) {
        String filePath = "data/duke.txt";
        Duke duke = new Duke(filePath);
        duke.run();
    }
}
