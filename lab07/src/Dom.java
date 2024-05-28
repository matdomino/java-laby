import java.time.LocalDate;

final class Dom extends Budynek {
    private double landArea;

    public String toString() {
        return "{ " +
                "ulica: " + this.street + '\'' +
                ", numer domu: " + this.houseNum + '\'' +
                ", miejscowość: " + this.city + '\'' +
                ", kod pocztowy: " + this.postCode + '\'' +
                ", powierzchnia: " + this.area + "mk" + '\'' +
                ", cena: " + this.price + "zł" + '\'' +
                ", powierzchnia działki: " + this.landArea + "mk" + '\'' +
                ", data zakończenia: " + this.expires + '\'' +
                " }";
    }

    public Dom(String street, int houseNum, String city, String postCode, double area, int price, LocalDate expires, double landArea) {
        super(street, houseNum, city, postCode, area, price, expires);
        this.landArea = landArea;
    }
}
