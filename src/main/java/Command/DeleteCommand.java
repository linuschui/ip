package Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import java.io.IOException;

/**
* Command that deletes a task from the TaskList when executed.
*/
public class DeleteCommand extends Command {
    boolean isExit;
    private String userAction;

    public DeleteCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            if (!isNumeric(userAction)) {
                throw new DukeException("I'm sorry, the input you provided is not a number!");
            } else {
                int index = Integer.parseInt(userAction) - 1;
                if (index >= tasks.getTaskListSize() || index < 0) {
                    throw new DukeException("I'm sorry, but the index you provided is out of range :-(");
                } else {
                    Task task = tasks.getTasks().get(index);
                    tasks.getTasks().remove(index);
                    storage.save();
                    return ui.sendMessage(" Noted. I've removed this task:\n" + "   " + task.toString()
                            + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
                }
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } catch (DukeException e2) {
            return e2.toString();
        }
    }

    @Override
    public String toString() {
        return "this is a delete command : delete " + userAction;
    }
}
