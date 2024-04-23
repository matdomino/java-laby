import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void greeting() {
        System.out.println("Witaj w systemie do rejestracji spotkań.");
        System.out.println("Dostępne operacje: ");
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

        ArrayList<String> meetings = callendar.getMeetings(dayIndex);
        System.out.println("Spotkania: ");
        System.out.println(meetings);
    }

    public static void deleteMeeting(Kalendarz callendar, Scanner scanner) {
        int dayIndex = setDayIndex(scanner);
        System.out.print("Podaj numer spotkania: ");
        int elemIndex = Integer.parseInt(scanner.nextLine()) - 1;

        callendar.deleteMeeting(dayIndex, elemIndex);
        System.out.println("Przekazano spotaknie do likwidacji.");
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
                case "2" -> getMeetings(callendar, scanner);
                case "3" -> deleteMeeting(callendar, scanner);
                case "5" -> status = false;
                default -> System.out.println("Niepoprawna operacja");
            }
        }
    }
}