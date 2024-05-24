import java.time.LocalTime;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Kalendarz {
    ArrayList<ArrayList<Wydarzenie>> Week = new ArrayList<>();

    public void addEvent(int day, Class<?> eventType, String description, LocalTime startTime, LocalTime endTime, String eventInfo) {
        if (eventType.equals(Spotkanie.class)) {
            Spotkanie meeting = new Spotkanie(description, startTime, endTime, eventInfo);
            Week.get(day).add(meeting);
        } else {
            Zadanie task = new Zadanie(description, startTime, endTime, eventInfo);
            Week.get(day).add(task);
        }
    }

    public void deleteEvent(int day, Class<?> eventType, int elemIndex) {
        ArrayList<Wydarzenie> events = Week.get(day);

        if (eventType.equals(Zadanie.class)) {
            ArrayList<Zadanie> zadania = new ArrayList<>();
            for (Wydarzenie event : events) {
                if (event instanceof Zadanie) {
                    zadania.add((Zadanie) event);
                }
            }
            if (elemIndex >= 0 && elemIndex < zadania.size()) {
                events.remove(zadania.get(elemIndex));
            }
        } else if (eventType.equals(Spotkanie.class)) {
            ArrayList<Spotkanie> spotkania = new ArrayList<>();
            for (Wydarzenie event : events) {
                if (event instanceof Spotkanie) {
                    spotkania.add((Spotkanie) event);
                }
            }
            if (elemIndex >= 0 && elemIndex < spotkania.size()) {
                events.remove(spotkania.get(elemIndex));
            }
        }
    }

    public ArrayList <Wydarzenie> filterEvents(int day, Predicate<Wydarzenie> condition) {
        ArrayList<Wydarzenie> meetings = Week.get(day);
        ArrayList<Wydarzenie> filteredEvents = new ArrayList<>();
        for (Wydarzenie event : meetings) {
            if (condition.test(event)) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    public Kalendarz() {
        for (int i = 0; i < 7; i++) {
            Week.add(new ArrayList<>());
        }
    }
}
