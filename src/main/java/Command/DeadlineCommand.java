import java.io.IOException;

/**
 * Handles a deadline command
 */
public class DeadlineCommand extends Command {
    boolean exit;
    private String userAction;
    public DeadlineCommand(String userAction) {
        this.exit = false;
        this.userAction = userAction;
    }

    @Override
    public void execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            String[] deadlineString = userAction.split("/by ");
            if (deadlineString[0].equals("")) {
                throw new DukeException("The description and date/time of a deadline cannot be empty.");
            } else if (deadlineString.length == 1) {
                throw new DukeException("The description or date/time of a deadline cannot be empty.");
            } else {
                Task newDeadline = new Deadline(deadlineString[0], deadlineString[1]);
                tasks.addTask(newDeadline);
                storage.save();
                DukeUi.sendMessage(" Got it. I've added this task:\n" + "   " + newDeadline.toString()
                        + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}