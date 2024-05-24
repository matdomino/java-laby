import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;


public class Main {
    public static void greeting() {
        System.out.println("Witaj w systemie do rejestracji spotkań.");
        System.out.println("Dostępne operacje: ");
        System.out.println("1. Dodaj spotkanie ");
        System.out.println("2. Usuń spotkanie ");
        System.out.println("3. Wyświetl wyszstkie spotkania");
        System.out.println("4. Wyświetl spotkania z danym priorytetem");
        System.out.println("5. Wyświetl spotkania zaczynające się od danej godziny");
        System.out.println("6. Wyświetl spotkania odbywające się w danym czasie");
        System.out.println("7. Wyświetl spotkania zaczynające się od danej godziny z danym priorytetem");
        System.out.println("8. Dodanie testowych spotkan");
        System.out.println("9. Wyjdź z programu");
    }

    private static int setDayIndex(Scanner scanner) {
        while (true) {
            System.out.print("Podaj dzień tygodnia: ");
            int dayIndex = Integer.parseInt(scanner.nextLine());

            if (dayIndex >= 0 && dayIndex <= 7) {
                return dayIndex - 1;
            } else {
                System.out.println("Niepoprawny numer dnia.");
            }
        }
    }

    private static LocalTime setDate(Scanner scanner) {
        System.out.print("Podaj godzinę: ");
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

        return LocalTime.parse(input, formatter);
    }

    private static LocalTime setStartDate(Scanner scanner) {
        while (true) {
            System.out.print("Podaj czas rozpoczęcia: ");
            String input = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
            LocalTime startTime = LocalTime.parse(input, formatter);

            if (startTime.isAfter(Spotkanie.MINIMAL_START_TIME)) {
                return startTime;
            } else {
                System.out.println("Godzina rozpoczęcia nie może być mniejsza niż start pracy.");
            }
        }
    }

    private static String setPriority(Scanner scanner) {
        while(true) {
            System.out.print("Podaj priorytet: ");
            String priority = scanner.nextLine();

            if (priority.equals("normalny") || priority.equals("wysoki") || priority.equals("najwyższy")) {
                return priority;
            } else {
                System.out.println("Niepoprawny priorytet");
            }
        }
    }

    private static void printMeetins(ArrayList<Spotkanie> meetings) {
        for (Spotkanie spotkanie : meetings) {
            System.out.println(spotkanie);
        }
    }

    public static void addMeeting(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        System.out.print("Podaj opis: ");
        String description = scanner.nextLine();
        LocalTime startTime = setStartDate(scanner);
        System.out.print("Podaj czas zakończenia: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        String input = scanner.nextLine();
        LocalTime endTime = LocalTime.parse(input, formatter);
        String priority = setPriority(scanner);

        callendar.addMeeting(dayIndex, description, startTime, endTime, priority);
        System.out.println("Dodano spotkanie.");
    }

    public static void getMeetings(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);

        Predicate<Spotkanie> noFilter = spotkanie -> true;
        ArrayList<Spotkanie> filteredMeetings = callendar.filterMeetings(dayIndex, noFilter);

        System.out.println("Spotkania: ");
        printMeetins(filteredMeetings);
    }

    public static void deleteMeeting(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        System.out.print("Podaj numer spotkania: ");
        int elemIndex = Integer.parseInt(scanner.nextLine()) - 1;

        callendar.deleteMeeting(dayIndex, elemIndex);
        System.out.println("Przekazano spotaknie do likwidacji.");
    }

    public static void getMeetinsWithPriority(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        String priority = setPriority(scanner);

        Predicate<Spotkanie> priorityFilter = spotkanie -> spotkanie.getPriority().equals(priority);
        ArrayList<Spotkanie> filteredMeetings = callendar.filterMeetings(dayIndex, priorityFilter);

        System.out.println("Spotkania: ");
        printMeetins(filteredMeetings);
    }

    public static void StartsNotEralier(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        LocalTime startTime = setDate(scanner);

        Predicate<Spotkanie> timeFilter = spotkanie -> spotkanie.getStartTime().isAfter(startTime);
        ArrayList<Spotkanie> filteredMeetings = callendar.filterMeetings(dayIndex, timeFilter);

        System.out.println("Spotkania: ");
        printMeetins(filteredMeetings);
    }

    public static void IsTakingPlace(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        LocalTime startTime = setDate(scanner);
        LocalTime endTime = setDate(scanner);

        Predicate<Spotkanie> timeFilter = spotkanie ->
                spotkanie.getStartTime().isAfter(startTime) &&
                        spotkanie.getEndTIme().isBefore(endTime);

        ArrayList<Spotkanie> filteredMeetings = callendar.filterMeetings(dayIndex, timeFilter);

        System.out.println("Spotkania: ");
        printMeetins(filteredMeetings);
    }

    public static void isStartingAfterWithPriority(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        String priority = setPriority(scanner);
        LocalTime startTime = setDate(scanner);

        Predicate<Spotkanie> priorityFilter = spotkanie -> spotkanie.getPriority().equals(priority) &&
                spotkanie.getStartTime().isAfter(startTime);
        ArrayList<Spotkanie> filteredMeetings = callendar.filterMeetings(dayIndex, priorityFilter);

        System.out.println("Spotkania: ");
        printMeetins(filteredMeetings);
    }

    public static void addTestMeetings(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);

        callendar.addMeeting(dayIndex, "Spotkanie 1", LocalTime.parse("08:15"), LocalTime.parse("09:00"), "normalny");
        callendar.addMeeting(dayIndex, "Spotkanie 2", LocalTime.parse("09:15"), LocalTime.parse("10:00"), "wysoki");
        callendar.addMeeting(dayIndex, "Spotkanie 3", LocalTime.parse("10:15"), LocalTime.parse("11:00"), "normalny");
        callendar.addMeeting(dayIndex, "Spotkanie 4", LocalTime.parse("11:15"), LocalTime.parse("12:00"), "najwyższy");
        callendar.addMeeting(dayIndex, "Spotkanie 5", LocalTime.parse("12:15"), LocalTime.parse("13:00"), "wysoki");
        callendar.addMeeting(dayIndex, "Spotkanie 6", LocalTime.parse("13:15"), LocalTime.parse("14:00"), "normalny");
        callendar.addMeeting(dayIndex, "Spotkanie 7", LocalTime.parse("14:15"), LocalTime.parse("15:00"), "najwyższy");
        System.out.println("Utworzono spotkania.");
    }

    public static void main(String[] args) {
        Kalendarz callendar = new Kalendarz();
        boolean status = true;

        Scanner scanner = new Scanner(System.in);
        greeting();

        while(status) {
            System.out.print("Podaj numer operacji: ");
            String operation = scanner.nextLine();

            switch (operation) {
                case "1" -> addMeeting(callendar, scanner);
                case "2" -> deleteMeeting(callendar, scanner);
                case "3" -> getMeetings(callendar,scanner);
                case "4" -> getMeetinsWithPriority(callendar, scanner);
                case "5" -> StartsNotEralier(callendar, scanner);
                case "6" -> IsTakingPlace(callendar, scanner);
                case "7" -> isStartingAfterWithPriority(callendar, scanner);
                case "8" -> addTestMeetings(callendar, scanner);
                case "9" -> status = false;
                default -> System.out.println("Niepoprawna operacja");
            }
        }
    }
}