import java.time.LocalTime;

sealed abstract class Wydarzenie permits Spotkanie, Zadanie {
    protected String description;
    protected LocalTime startTime;
    protected LocalTime endTime;

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public LocalTime getEndTIme() {
        return this.endTime;
    }

    public Wydarzenie(String description, LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
