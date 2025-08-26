public class Event extends Task {
    private String eventStartTime;
    private String eventEndTime;

    public Event(String taskName, Boolean status, String eventStartTime, String eventEndTime) {
        super(taskName, status);
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    public Event(String taskName, String eventStartTime, String eventEndTime) {
        this(taskName, false, eventStartTime, eventEndTime);
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public String getEventEndTime() {
        return eventEndTime;
    }
    
    public static Event toEventTask(String taskString) {
        String[] parts = taskString.split(" \\| ", 4);
        String[] timeParts = parts[3].split(" to ", 2);
        return new Event(parts[2], parts[1].equals("1"), timeParts[0], timeParts[1]);
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + eventStartTime + " to " + eventEndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStartTime + " to: " + eventEndTime + ")";
    }
    
}
