import java.time.LocalTime;

public class Spotkanie {
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private String prority;
    public static final LocalTime MINIMAL_START_TIME = LocalTime.of(8,0);

    public Spotkanie isGivenPrority(String prority) {
        if (this.prority.equals(prority)) {
            return this;
        } else {
            return null;
        }
    }

    public String returnInfo() {
        return "Spotkanie {" +
                "opis = " + description + '\'' +
                ", godzina rozpoczęcia =" + startTime +
                ", godzina zakończenia =" + endTime +
                ", priorytet ='" + prority + '\'' +
                '}';
    }

    public Spotkanie isStartingAfter(LocalTime time) {
        if (this.startTime.isAfter(time)) {
            return this;
        } else {
            return null;
        }
    }

    public Spotkanie(String description, LocalTime startTime, LocalTime endTime, String prority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.prority = prority;
    }
}
