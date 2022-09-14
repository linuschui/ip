package Duke;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
* Storage deals with loading tasks from the file and saving tasks in the file
*
* @author Linus Chui
*/
public class Storage {
    private String filePath;
    private ArrayList<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads previously stored tasks from a .txt file when the program starts and formats the
     * data to be stored in a TaskList object or creates a new .txt file if the file is not found.
     *
     * @return an ArrayList of Task objects that has been formatted by Duke after reading the .txt file.
     * @throws DukeException if the target file cannot be found and creates a new .txt file.
     * @throws IOException if error occurs when Duke is reading data from the target file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader br = new BufferedReader(fileReader);
            String nextLine = br.readLine();
            while (nextLine != null) {
                String[] nextTask = nextLine.split(",", 4);
                addTaskFromStorage(nextTask);
                nextLine = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e1) {
            try {
                System.out.println("File not found");
                // Solution below adapted from
                // https://stackoverflow.com/questions/9620683/java-fileoutputstream-create-file-if-not-exists
                File file = new File(filePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e2) {
                System.out.println(" Duke has encountered an error while loading tasks" +
                        "\n Please exit and try again");
                e2.printStackTrace();
            }
        } catch (IOException e3) {
            throw new DukeException(e3.getMessage());
        }
        return this.tasks;
    }

    /**
     * Helper function that helps write tasks from .txt file to TaskList
     *
     * @param nextTask a String array containing details of next task to be added.
     * @throws DukeException if error occurs while adding task.
     */
    public void addTaskFromStorage(String[] nextTask) throws DukeException {
        switch (nextTask[0]) {
            case "T":
                Task newTodo = new Todo(nextTask[2]);
                if (nextTask[1].equals("1")) {
                    newTodo.markAsDone();
                }
                this.tasks.add(newTodo);
                break;
            case "E":
                Task newEvent = new Event(nextTask[2], nextTask[3]);
                if (nextTask[1].equals("1")) {
                    newEvent.markAsDone();
                }
                this.tasks.add(newEvent);
                break;
            case "D":
                Task newDeadline = new Deadline(nextTask[2], nextTask[3]);
                if (nextTask[1].equals("1")) {
                    newDeadline.markAsDone();
                }
                this.tasks.add(newDeadline);
                break;
        }
    }

    /**
     * Saves the tasks to a .txt file when changes are made to the TaskList object.
     *
     * @throws DukeException if Duke is unable to find the target file to write data to.
     * @throws IOException if error occurs when Duke is writing data to the target file.
     */
    public void save() throws DukeException, IOException {
        try {
            FileWriter fw = new FileWriter("./data/tasks.txt");
            // loop through ArrayList<Task>
            for (Task task : this.tasks) {
                this.saveTaskToStorage(task, fw);
            }
            fw.close();
        } catch (FileNotFoundException e1) {
            throw new DukeException(DukeUi.FILE_NOT_FOUND_ERROR);
        } catch (IOException e2) {
            throw new DukeException(e2.getMessage());
        }
    }

    public void saveTaskToStorage(Task task, FileWriter fw) throws IOException {
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            if (todo.isDone) {
                fw.write("T,1," + todo.description + "\n");
            } else {
                fw.write("T,0," + todo.description + "\n");
            }
        } else if (task instanceof Event) {
            Event event = (Event) task;
            if (event.isDone) {
                fw.write("E,1," + event.description + "," + event.event + "\n");
            } else {
                fw.write("E,0," + event.description + "," + event.event + "\n");
            }
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            if (deadline.isDone) {
                fw.write("D,1," + deadline.description + "," + deadline.deadline + "\n");
            } else {
                fw.write("D,0," + deadline.description + "," + deadline.deadline + "\n");
            }
        }
    }
}