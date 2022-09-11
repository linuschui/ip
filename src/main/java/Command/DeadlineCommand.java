package Command;
import Duke.Deadline;
import Duke.DukeUi;
import Duke.DukeException;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
* Command that adds a Deadline to the TaskList when executed.
*/
public class DeadlineCommand extends Command {
    boolean isExit;
    private String userAction;
    public DeadlineCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            String[] deadlineString = userAction.split("/by ", 2);
            if (deadlineString.length == 1) {
                throw new DukeException("The description or date/time of a deadline cannot be empty.");
            } else {
                Task newDeadline = new Deadline(deadlineString[0], deadlineString[1]);
                tasks.addTask(newDeadline);
                storage.save();
                return ui.sendMessage(" Got it. I've added this task:\n" + "   " + newDeadline.toString()
                        + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("deadline must be of form yyyy-mm-dd");
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } catch (DukeException e2) {
            return e2.toString();
        }
    }

    @Override
    public String toString() {
        return "this is a deadline command : deadline " + userAction;
    }
}
