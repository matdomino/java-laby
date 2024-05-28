import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListaOfert {
    private ArrayList<Budynek> offers = new ArrayList<>();

    public void addHouseOffer(String street, int houseNum, String city, String postCode, double area, int price, LocalDate expires, double landArea) {
        this.offers.add(new Dom(street, houseNum, city, postCode, area, price, expires, landArea));
    }

    public void addFlatOffer(String street, int houseNum, String city, String postCode, double area, int price, LocalDate expires, int flatNum, int floorNum) {
        this.offers.add(new Mieszkanie(street, houseNum, city, postCode, area, price, expires, flatNum, floorNum));
    }

    public ArrayList<Budynek> filterOffers(Predicate<Budynek> predicate) {
        return this.offers.stream().filter(predicate).collect(Collectors.toCollection(ArrayList::new));
    }
}
