import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    // class variables
    protected String deadline;
    private LocalDate dateTime;

    // constructor
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        this.deadline = deadline;
        try {
            this.dateTime = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("deadline must be of form yyyy-mm-dd");
        }
    }

    public String getDeadLine() {
        String deadlineToString = this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return deadlineToString;
    }

    @Override
    public String toString() {
        String deadlineToString = this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "[D]" + super.toString() + "(by: " + deadlineToString + ")";
    }
}
