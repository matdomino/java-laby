import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean status = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Witaj w programie! Typy operacji: 1 - silnia, 2 - suma elementów zakresu, 3 - wyjście");

        while(status) {
            System.out.print("Podaj numer operacji: ");
            String operation = scanner.nextLine();

            switch(operation) {
                case "1":
                    System.out.print("Podaj wartość dla silni: ");
                    String inputSilnia = scanner.nextLine();
                    long resultSilnia = Calculations.silnia(Integer.parseInt(inputSilnia));

                    System.out.println("Silnia z " + inputSilnia + " to: " + resultSilnia);
                    break;
                case "2":
                    System.out.print("Podaj początek zakresu: ");
                    String rangeStart = scanner.nextLine();
                    System.out.print("Podaj koniec zakresu: ");
                    String rangeStop = scanner.nextLine();

                    long resultSum = Calculations.rangeSum(Integer.parseInt(rangeStart), Integer.parseInt(rangeStop));

                    System.out.println("Suma liczb z zakresu (" + rangeStart + ", " + rangeStop + "), to: " + resultSum);
                    break;
                case "3":
                    status = false;
            }
        }
    }
}
