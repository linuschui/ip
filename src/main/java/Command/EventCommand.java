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
            int initialSize = tasks.getTaskListSize();
            String[] eventString = userAction.split("/at ");
            Task newEvent = new Event(eventString[0], eventString[1]);
            tasks.addTask(newEvent);
            assert tasks.getTaskListSize() == initialSize + 1 : DukeUi.ADD_TASK_ERROR;
            storage.save();
            return ui.sendMessage(" Got it. I've added this task:\n" + "   " + newEvent.toString()
                    + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e1) {
            throw new DukeException(DukeUi.INVALID_DEADLINE);
        } catch (IOException e2) {
            throw new DukeException(e2.getMessage());
        } catch (DukeException e3) {
            return e3.toString();
        }
    }

    @Override
    public String toString() {
        return "this is an event command : event " + userAction;
    }
}
