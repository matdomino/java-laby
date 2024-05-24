import java.time.LocalTime;

public final class Zadanie extends Wydarzenie {
    private String status;

    public String getStatus() {
        return this.status;
    }

    public String toString() {
        return "{ " +
                "opis = " + description + '\'' +
                ", godzina rozpoczęcia: " + startTime +
                ", godzina zakończenia: " + endTime +
                ", priorytet: " + status + '\'' +
                " }";
    }


    public Zadanie(String description, LocalTime startTime, LocalTime endTime, String status) {
        super(description, startTime, endTime);
        this.status = status;
    }
}
