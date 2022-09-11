package Duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String deadline;
    private LocalDate dateTime;

    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        this.deadline = deadline;
        this.dateTime = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        String deadlineToString = this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return "[D]" + super.toString() + "(by: " + deadlineToString + ")";
    }
}
