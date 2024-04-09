import java.util.Objects;
import java.util.Scanner;

public class Silnia {
    public static long silnia(int num) {
        long result = 1;

        for (int i = 1; i <= num; i++) {
            result *= i;
        }

        return result;
    }

    public static void main(String[] args) {
        boolean status = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Witaj w programie do wyliczania silni!");
        System.out.println("Wpisz \"exit\", aby wyjść z programu.");

        while(status) {
            System.out.print("Podaj liczbę: ");
            String input = scanner.nextLine();

            if (Objects.equals(input, "exit")){
                status = false;
            } else {
                long result = silnia(Integer.parseInt(input));

                System.out.println("Silnia z " + input + " to: " + result);
            }
        }
    }
}