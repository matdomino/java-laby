import java.util.Scanner;

import static java.lang.Double.NaN;

public class Main {
    public static void greeting() {
        System.out.println("Witaj w systemie ewidencji ocen cząstkowych.");
        System.out.println("1. Dodaj nowa ocenę.");
        System.out.println("2. Wylicz średnią ocen");
        System.out.println("3. Wyświetl najwyższą ocenę.");
        System.out.println("4. Wyświetl najniższą ocenę.");
        System.out.println("5. Wyjdź z programu.");
    }

    public static void printHighest(GradeList list) {
        double highest = list.getHighestGrade();

        if (!Double.isNaN(highest)) {
            System.out.println("Najwyższa ocena " + highest);
        } else {
            System.out.println("Brak ocen w ewidencji.");
        }
    }

    public static void printLowest(GradeList list) {
        double lowest = list.getLowestGrade();

        if (!Double.isNaN(lowest)) {
            System.out.println("Najniższa ocena " + lowest);
        } else {
            System.out.println("Brak ocen w ewidencji.");
        }
    }

    public static void printMean(GradeList list) {
        double mean = list.calculateMean();

        if (!Double.isNaN(mean)) {
            System.out.println("Średnia ocen: " + mean);
        } else {
            System.out.println("Brak ocen w ewidencji.");
        }
    }

    public static void addGrade(GradeList list, Scanner scanner) {
        System.out.print("Podaj ocenę:");
        String grade = scanner.nextLine();

        try {
            double gradeDouble = Double.parseDouble(grade);
            if (gradeDouble >= 2 && gradeDouble <= 5) {
                list.addGrade(gradeDouble);
                System.out.println("Dodano ocenę do ewidencji.");
            } else {
                System.out.println("Ocena musi być między 2 a 5.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Niepoprawna ocena.");
        }
    }

    public static void main(String[] args) {
        GradeList gradeListMain = new GradeList();
        boolean status = true;
        Scanner scanner = new Scanner(System.in);
        greeting();

        while(status) {
            System.out.print("Podaj numer operacji: ");
            String operation = scanner.nextLine();

            switch (operation) {
                case "1" -> addGrade(gradeListMain, scanner);
                case "2" -> printMean(gradeListMain);
                case "3" -> printHighest(gradeListMain);
                case "4" -> printLowest(gradeListMain);
                case "5" -> status = false;
                default -> System.out.println("Niepoprawna operacja");
            }
        }
    }
}
