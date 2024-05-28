import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    public static void greeting() {
        System.out.println("Witaj w systemie sprzedaży nieruchomości.");
        System.out.println("Dostępne operacje: ");
        System.out.println("1. Dodaj ofertę sprzedaży domu");
        System.out.println("2. Dodaj ofertę sprzedaży mieszkania");
        System.out.println("3. Wyświetl wszystkie aktualne oferty sprzedaży domów");
        System.out.println("4. Wyświetl wszystkie aktualne oferty sprzedaży mieszkań");
        System.out.println("5. Wyświetl wszystkie aktualne ofety sprzedaży domów w podanej miejscowości, o minimalnej powierzchni.");
        System.out.println("6. Wyświetl wszystkie aktualne oferty sprzedaży mieszkań w podanej miescowości, o maksymalnej cenie i minimalnym piętrze.");
        System.out.println("7. Dodaj przykładowe dane.");
        System.out.println("8. Wyjdź z programu.");
    }

    public static void printOffers(ArrayList<Budynek> offers) {
        System.out.println("Oferty: ");
        for (Budynek offer : offers) {
            System.out.println(offer);
        }
    }

    public static int inputInt(String prompt, Scanner scanner) {
        int result = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.printf(prompt);
                result = Integer.parseInt(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawne dane. ");
            }
        }

        return result;
    }

    public static double inputDouble(String prompt, Scanner scanner) {
        double result = 0.0;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.printf(prompt);
                result = Double.parseDouble(scanner.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Podaj poprawne dane.");
            }
        }

        return result;
    }

    public static String inputString(String prompt, Scanner scanner) {
        String result = "";
        boolean isValid = false;

        while (!isValid) {
            System.out.printf(prompt);
            result = scanner.nextLine().trim();
            if (!result.isEmpty()) {
                isValid = true;
            } else {
                System.out.println("Pole nie może być puste.");
            }
        }

        return result;
    }

    public static LocalDate inputDate(String prompt, Scanner scanner) {
        LocalDate result = null;
        boolean isValid = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        while (!isValid) {
            System.out.printf(prompt);
            String input = scanner.nextLine().trim();
            try {
                result = LocalDate.parse(input, formatter);
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Podaj poprawną date - dd-MM-yyyy.");
            }
        }

        return result;
    }

    public static void addTestData(ListaOfert listaOfert) {
        listaOfert.addHouseOffer("Grunwaldzka", 1, "Gdańsk", "80-000", 55, 800000, LocalDate.parse("2024-06-13"), 1000);
        listaOfert.addHouseOffer("Kościuszki", 10, "Gdańsk", "80-000", 45, 700000, LocalDate.parse("2024-06-15"), 2000);
        listaOfert.addHouseOffer("Kliniczna", 12, "Gdańsk", "80-000", 105, 600000, LocalDate.parse("2024-06-18"), 500);
        listaOfert.addHouseOffer("Wojska Polskiego", 2, "Gdańsk", "80-000", 75, 1000000, LocalDate.parse("2024-06-23"), 700);
        listaOfert.addHouseOffer("Grunwaldzka", 104, "Gdańsk", "80-000", 85, 850000, LocalDate.parse("2024-06-30"), 600);
        listaOfert.addFlatOffer("Grunwaldzka", 20, "Gdańsk", "80-000", 55, 650000, LocalDate.parse("2024-07-05"), 5, 2);
        listaOfert.addFlatOffer("Mickiewicza", 10, "Gdańsk", "80-000", 35, 450000, LocalDate.parse("2024-07-08"), 1, 1);
        listaOfert.addFlatOffer("Grunwaldzka", 70, "Gdańsk", "80-000", 75, 750000, LocalDate.parse("2024-07-10"), 6, 0);
        listaOfert.addFlatOffer("Kościuszki", 18, "Gdańsk", "80-000", 52, 550000, LocalDate.parse("2024-07-12"), 13, 3);
        listaOfert.addFlatOffer("Kliniczna", 5, "Gdańsk", "80-000", 51, 500000, LocalDate.parse("2024-07-13"), 7, 1);
        listaOfert.addFlatOffer("Grunwaldzka", 162, "Gdańsk", "80-000", 58, 780000, LocalDate.parse("2024-07-15"), 10, 2);
        listaOfert.addFlatOffer("Reja", 61, "Gdańsk", "80-000", 60, 700000, LocalDate.parse("2024-07-18"), 15, 0);
        System.out.println("Utworzono oferty.");
    }

    public static void addHouseOffer(ListaOfert listaOfert, Scanner scanner) {
        String street = inputString("Podaj ulicę: ", scanner);
        int houseNum = inputInt("Podaj numer domu: ", scanner);
        String city = inputString("Podaj miejscowość: ", scanner);
        String postCode = inputString("Podaj kod pocztowy: ", scanner);
        double area = inputDouble("Podaj powierzchnię domu: ", scanner);
        int price = inputInt("Podaj cenę: ", scanner);
        LocalDate expires = inputDate("Podaj datę zakończenia - dd-mm-rrrr: ", scanner);
        double landArea = inputDouble("Podaj powierzchnię działki: ", scanner);

        listaOfert.addHouseOffer(street, houseNum, city, postCode, area, price, expires, landArea);
        System.out.println("Pomyślnie dodano ofertę domu.");
    }

    public static void addFlatOffer(ListaOfert listaOfert, Scanner scanner) {
        String street = inputString("Podaj ulicę: ", scanner);
        int houseNum = inputInt("Podaj numer domu: ", scanner);
        String city = inputString("Podaj miejscowość: ", scanner);
        String postCode = inputString("Podaj kod pocztowy: ", scanner);
        double area = inputDouble("Podaj powierzchnię mieszkania: ", scanner);
        int price = inputInt("Podaj cenę: ", scanner);
        LocalDate expires = inputDate("Podaj datę zakończenia - dd-mm-rrrr: ", scanner);
        int flatNum = inputInt("Podaj numer mieszkania: ", scanner);
        int floorNum = inputInt("Podaj piętro: ", scanner);

        listaOfert.addFlatOffer(street, houseNum, city, postCode, area, price, expires, flatNum, floorNum);
        System.out.println("Pomyślnie dodano ofertę mieszkania.");
    }

    public static void getHouseOffers(ListaOfert listaOfert) {
        ArrayList<Budynek> offers =  listaOfert.filterOffers(offer -> offer instanceof Dom
                && offer.getExpireDate().isAfter(LocalDate.now()) || offer.getExpireDate().isEqual(LocalDate.now()));

        printOffers(offers);
    }

    public static void getFlatOffers(ListaOfert listaOfert) {

        ArrayList<Budynek> offers =  listaOfert.filterOffers(offer -> offer instanceof Mieszkanie
                && offer.getExpireDate().isAfter(LocalDate.now()) || offer.getExpireDate().isEqual(LocalDate.now()));

        printOffers(offers);
    }

    public static void getHouseOffersCityMinArea(ListaOfert listaOfert, Scanner scanner) {
        String city = inputString("Podaj miejscowość: ", scanner);
        double area = inputDouble("Podaj powierzchnię: ", scanner);

        ArrayList<Budynek> offers =  listaOfert.filterOffers(offer -> offer instanceof Dom
                && offer.getCity().equals(city)
                && offer.getArea() >= area
                && offer.getExpireDate().isAfter(LocalDate.now()) || offer.getExpireDate().isEqual(LocalDate.now()));
        printOffers(offers);
    }

    public static void getFlatsOffersCityMaxPriceMinFloor(ListaOfert listaOfert, Scanner scanner) {
        String city = inputString("Podaj miejscowość: ", scanner);
        int price = inputInt("Podaj cenę: ", scanner);
        int floor = inputInt("Podaj piętro: ", scanner);

        ArrayList<Budynek> offers = listaOfert.filterOffers(offer -> {
            if (offer instanceof Mieszkanie mieszkanie) {
                return mieszkanie.getCity().equals(city)
                        && mieszkanie.getPrice() <= price
                        && mieszkanie.getFloorNum() >= floor
                        && (mieszkanie.getExpireDate().isAfter(LocalDate.now()) || mieszkanie.getExpireDate().isEqual(LocalDate.now()));
            }
            return false;
        });
        printOffers(offers);
    }

    public static void main(String[] args) {
        ListaOfert listaOfert = new ListaOfert();
        boolean status = true;

        Scanner scanner = new Scanner(System.in);
        greeting();

        while(status) {
            System.out.print("Podaj numer operacji: ");
            String operation = scanner.nextLine();

            switch (operation) {
                case "1" -> addHouseOffer(listaOfert, scanner);
                case "2" -> addFlatOffer(listaOfert, scanner);
                case "3" -> getHouseOffers(listaOfert);
                case "4" -> getFlatOffers(listaOfert);
                case "5" -> getHouseOffersCityMinArea(listaOfert, scanner);
                case "6" -> getFlatsOffersCityMaxPriceMinFloor(listaOfert, scanner);
                case "7" -> addTestData(listaOfert);
                case "8" -> status = false;
                default -> System.out.println("Niepoprawna operacja");
            }
        }
    }
}