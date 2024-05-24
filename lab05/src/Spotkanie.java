import java.time.LocalTime;

public class Spotkanie {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String prority;
    public static final LocalTime MINIMAL_START_TIME = LocalTime.of(8,0);

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTIme() {
        return this.endTime;
    }

    public String getPriority() {
        return this.prority;
    }

    public String toString() {
        return "{ " +
                "opis = " + description + '\'' +
                ", godzina rozpoczęcia: " + startTime +
                ", godzina zakończenia: " + endTime +
                ", priorytet: " + prority + '\'' +
                " }";
    }

    public Spotkanie(String description, LocalTime startTime, LocalTime endTime, String prority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.prority = prority;
    }
}
