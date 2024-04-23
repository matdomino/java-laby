import java.time.LocalTime;
import java.util.ArrayList;

public class Kalendarz {
    ArrayList<ArrayList<Spotkanie>> Week = new ArrayList<>();

    public void addMeeting(int day, String description, LocalTime startTime, LocalTime endTime, String prority) {
        Spotkanie meeting = new Spotkanie(description, startTime, endTime, prority);

        Week.get(day).add(meeting);
    }

    public void deleteMeeting(int day, int elemIndex) {
        if (day >= 0) {
            ArrayList<Spotkanie> meetings = Week.get(day);
            if (elemIndex < meetings.size()) {
                meetings.remove(elemIndex);
            }
        }
    }

    public ArrayList<String> getMeetings(int day) {
        ArrayList<Spotkanie> meetings = Week.get(day);
        ArrayList<String> meetingsInfo = new ArrayList<>();
        for (Spotkanie spotkanie : meetings) {
            meetingsInfo.add(spotkanie.returnInfo());
        }

        return meetingsInfo;
    }

    public Kalendarz() {
        for (int i = 0; i < 7; i++) {
            Week.add(new ArrayList<>());
        }
    }
}
