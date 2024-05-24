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
        System.out.println("2. Dodaj zadanie ");
        System.out.println("3. Usuń spotkanie");
        System.out.println("4. Usuń zadanie");
        System.out.println("5. Wyświetl spotkania");
        System.out.println("6. Wyświetl zadania");
        System.out.println("7. Wyświetl spotkania o danym priorytecie");
        System.out.println("8. Wyświetl zadania o danym statusie.");
        System.out.println("9. Wyświetl spotkania o danym priorytecie zaczynające się nie poźniej od podanej godziny.");
        System.out.println("10. Wyświetl zadania o danym statusie nie kończące się poźniej od podanej godziny.");
        System.out.println("11. Dodaj testowe spotkania i zadania.");
        System.out.println("12. Wyjdź z programu.");
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

    private static String setStatus(Scanner scanner) {
        while(true) {
            System.out.print("Podaj status: ");
            String status = scanner.nextLine();

            if (status.equals("planowane") || status.equals("potwierdzone") || status.equals("realizowane") || status.equals("wykonane")) {
                return status;
            } else {
                System.out.println("Niepoprawny priorytet");
            }
        }
    }

    private static void printTasks(ArrayList<Wydarzenie> events) {
        for (Wydarzenie event : events) {
            System.out.println(event);
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

        callendar.addEvent(dayIndex, Spotkanie.class, description, startTime, endTime, priority);
        System.out.println("Dodano spotkanie.");
    }

    public static void addTask(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        System.out.print("Podaj opis: ");
        String description = scanner.nextLine();
        LocalTime startTime = setStartDate(scanner);
        System.out.print("Podaj czas zakończenia: ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        String input = scanner.nextLine();
        LocalTime endTime = LocalTime.parse(input, formatter);
        String status = setStatus(scanner);

        callendar.addEvent(dayIndex, Zadanie.class, description, startTime, endTime, status);
        System.out.println("Dodano zadanie.");
    }

    public static void getMeetings(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);

        Predicate<Wydarzenie> meetingsFilter = event -> event instanceof Spotkanie;

        ArrayList<Wydarzenie> filteredMeetings = callendar.filterEvents(dayIndex, meetingsFilter);

        System.out.println("Spotkania: ");
        printTasks(filteredMeetings);
    }

    public static void getTasks(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);

        Predicate<Wydarzenie> meetingsFilter = event -> event instanceof Zadanie;

        ArrayList<Wydarzenie> filteredTasks = callendar.filterEvents(dayIndex, meetingsFilter);

        System.out.println("Zadania: ");
        printTasks(filteredTasks);
    }

    public static void deleteMeeting(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        System.out.print("Podaj numer spotkania: ");
        int elemIndex = Integer.parseInt(scanner.nextLine()) - 1;

        callendar.deleteEvent(dayIndex, Spotkanie.class, elemIndex);
        System.out.println("Przekazano spotaknie do likwidacji.");
    }

    public static void deleteTask(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        System.out.print("Podaj numer zadania: ");
        int elemIndex = Integer.parseInt(scanner.nextLine()) - 1;

        callendar.deleteEvent(dayIndex, Zadanie.class, elemIndex);
        System.out.println("Przekazano zadanie do likwidacji.");
    }

    public static void getMeetinsWithPriority(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        String priority = setPriority(scanner);

        Predicate<Wydarzenie> priorityFilter = event ->
                event instanceof Spotkanie &&
                        ((Spotkanie) event).getPriority().equals(priority);
        ArrayList<Wydarzenie> filteredMeetings = callendar.filterEvents(dayIndex, priorityFilter);

        System.out.println("Spotkania: ");
        printTasks(filteredMeetings);
    }

    public static void getTasksWithStatus(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        String status = setStatus(scanner);

        Predicate<Wydarzenie> priorityFilter = event ->
                event instanceof Zadanie &&
                        ((Zadanie) event).getStatus().equals(status);
        ArrayList<Wydarzenie> filteredTasks = callendar.filterEvents(dayIndex, priorityFilter);

        System.out.println("Zadania: ");
        printTasks(filteredTasks);
    }

    public static void isStartingAfterWithPriority(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        String priority = setPriority(scanner);
        LocalTime startTime = setDate(scanner);

        Predicate<Wydarzenie> priorityFilter = event ->
                event instanceof Spotkanie &&
                        ((Spotkanie) event).getPriority().equals(priority) &&
                        (event.getStartTime().isAfter(startTime) || event.getStartTime().equals(startTime));
        ArrayList<Wydarzenie> filteredMeetings = callendar.filterEvents(dayIndex, priorityFilter);

        System.out.println("Spotkania: ");
        printTasks(filteredMeetings);
    }

    public static void isTaskEndingEarlierWithStatus(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        String status = setStatus(scanner);
        LocalTime endTime = setDate(scanner);

        Predicate<Wydarzenie> priorityFilter = event ->
                event instanceof Zadanie &&
                        ((Zadanie) event).getStatus().equals(status) &&
                        (event.getStartTime().isBefore(endTime) || event.getStartTime().equals(endTime));
        ArrayList<Wydarzenie> filteredTasks = callendar.filterEvents(dayIndex, priorityFilter);

        System.out.println("Zadania: ");
        printTasks(filteredTasks);
    }

    public static void addTestMeetings(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);

        callendar.addEvent(dayIndex, Spotkanie.class, "Spotkanie 1", LocalTime.parse("08:15"), LocalTime.parse("09:00"), "normalny");
        callendar.addEvent(dayIndex, Zadanie.class, "Zadanie 1", LocalTime.parse("09:15"), LocalTime.parse("10:00"), "planowane");
        callendar.addEvent(dayIndex, Spotkanie.class, "Spotkanie 2", LocalTime.parse("10:15"), LocalTime.parse("11:00"), "normalny");
        callendar.addEvent(dayIndex, Zadanie.class, "Zadanie 2", LocalTime.parse("11:15"), LocalTime.parse("12:00"), "potwierdzone");
        callendar.addEvent(dayIndex, Spotkanie.class, "Spotkanie 3", LocalTime.parse("12:15"), LocalTime.parse("13:00"), "wysoki");
        callendar.addEvent(dayIndex, Zadanie.class, "Zadanie 3", LocalTime.parse("13:15"), LocalTime.parse("14:00"), "realizowane");
        callendar.addEvent(dayIndex, Spotkanie.class, "Spotkanie 4", LocalTime.parse("14:15"), LocalTime.parse("15:00"), "najwyższy");
        callendar.addEvent(dayIndex, Zadanie.class, "Zadanie 4", LocalTime.parse("14:15"), LocalTime.parse("15:00"), "wykonane");
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
                case "2" -> addTask(callendar, scanner);
                case "3" -> deleteMeeting(callendar, scanner);
                case "4" -> deleteTask(callendar, scanner);
                case "5" -> getMeetings(callendar,scanner);
                case "6" -> getTasks(callendar, scanner);
                case "7" -> getMeetinsWithPriority(callendar, scanner);
                case "8" -> getTasksWithStatus(callendar, scanner);
                case "9" -> isStartingAfterWithPriority(callendar, scanner);
                case "10" -> isTaskEndingEarlierWithStatus(callendar, scanner);
                case "11" -> addTestMeetings(callendar, scanner);
                case "12" -> status = false;
                default -> System.out.println("Niepoprawna operacja");
            }
        }
    }
}