package Command;
import Duke.DukeException;
import Duke.DukeUi;
import Duke.Event;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import java.io.IOException;

/**
* Command that adds an Event to the TaskList when executed.
*/
public class EventCommand extends Command {
    boolean isExit;
    private String userAction;
    public EventCommand(String userAction) {
        this.isExit = false;
        this.userAction = userAction;
    }

    @Override
    public String execute(TaskList tasks, DukeUi ui, Storage storage) throws DukeException {
        try {
            String[] eventString = userAction.split("/at ");
            if (eventString[0].equals("")) {
                throw new DukeException("The description of an event cannot be empty.");
            } else if (eventString.length == 1) {
                throw new DukeException("The description or date/time of an event cannot be empty.");
            } else {
                Task newEvent = new Event(eventString[0], eventString[1]);
                tasks.addTask(newEvent);
                storage.save();
                return ui.sendMessage(" Got it. I've added this task:\n" + "   " + newEvent.toString()
                        + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } catch (DukeException e2) {
            return e2.toString();
        }
    }

    @Override
    public String toString() {
        return "this is an event command : event " + userAction;
    }
}
