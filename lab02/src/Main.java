import java.util.Scanner;

public class Main {
    public static void greeting() {
        System.out.println("Witaj w programie służącym do wykonywania operacji na walcu.");
        System.out.println("1. Wyświetl parametry walca.");
        System.out.println("2. Ustaw parametry walca.");
        System.out.println("3. Wyświetl pola powierzchni walca.");
        System.out.println("4. Wyświetl objętość walca.");
        System.out.println("5. Wyjdź z programu.");
    }

    public static String chooseOperation(Scanner scanner) {
        System.out.print("Podaj numer operacji: ");
        String operation = scanner.nextLine();

        return operation;
    }

    public static void showParameters(Walec walec) {
        System.out.println("Promień podstawy: " + walec.getBaseRadius());
        System.out.println("Wysokość: " + walec.getHeight());
    }

    public static void setParameters(Walec walec, Scanner scanner) {
        System.out.print("Podaj promień podstawy: ");
        String radius = scanner.nextLine();
        System.out.print("Podaj wysokość walca: ");
        String height = scanner.nextLine();

        try {
            float radiusFloat = Float.parseFloat(radius);
            float heightFloat = Float.parseFloat(height);

            walec.setBaseRadius(radiusFloat);
            walec.setHeight(heightFloat);
        } catch (NumberFormatException e) {
            System.out.println("Niepoprawne parametry.");
        }
    }

    public static void printAreas(Walec walec) {
        System.out.println("Pole podstawy walca: " + walec.baseArea());
        System.out.println("Pole boczne walca: " + walec.lateralArea());
        System.out.println("Pole całkowite walca: " + walec.totalArea());
    }

    public static void printVolume(Walec walec) {
        System.out.println("Objętość walca: " + walec.volume());
    }

    public static void main(String[] args) {
        Walec walecMain = new Walec();
        boolean status = true;
        Scanner scanner = new Scanner(System.in);
        greeting();

        while(status) {
            String operation = chooseOperation(scanner);
            switch (operation) {
                case "1":
                    showParameters(walecMain);
                    break;
                case "2":
                    setParameters(walecMain, scanner);
                    break;
                case "3":
                    printAreas(walecMain);
                    break;
                case "4":
                    printVolume(walecMain);
                    break;
                case "5":
                    status = false;
                default:
                    System.out.println("Niepoprawny numer operacji.");
                    break;
            }
        }
    }
}