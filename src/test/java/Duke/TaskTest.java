package Duke;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    // test 1 : Task.getStatusIcon (!isDone)
    @Test
    public void taskTest1() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks1 = new TaskList(tasks);
        String expected = " ";
        assertEquals(expected, tasks1.getTasks().get(0).getStatusIcon());
    }

    // test 2 : Task.getStatusIcon (isDone)
    @Test
    public void taskTest2() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks2 = new TaskList(tasks);
        tasks2.getTasks().get(0).markAsDone();
        String expected = "X";
        assertEquals(expected, tasks2.getTasks().get(0).getStatusIcon());
    }

    // test 3 : updateDescription(String description)
    @Test
    public void taskTest3() throws DukeException {
        Todo todo = new Todo("placeholder");
        todo.updateDescription("new description");
        String expected = "[T][ ] new description";
        assertEquals(expected, todo.toString());
    }

}
