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

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + eventStartTime + " to: " + eventEndTime + ")";
    }
    
}
