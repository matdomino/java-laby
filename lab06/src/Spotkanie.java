import java.time.LocalTime;

public final class Spotkanie extends Wydarzenie {
    private String prority;
    public static final LocalTime MINIMAL_START_TIME = LocalTime.of(8,0);

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
        super(description, startTime, endTime);
        this.prority = prority;
    }
}
