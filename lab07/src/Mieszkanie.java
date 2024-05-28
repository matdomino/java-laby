import java.time.LocalDate;

final class Mieszkanie extends Budynek{
    private int flatNum;
    private int floorNum;

    public int getFloorNum() {
        return this.floorNum;
    }

    public String toString() {
        return "{ " +
                "ulica: " + this.street + '\'' +
                ", numer domu: " + this.houseNum + '\''  +
                ", numer mieszkania: " + this.flatNum + '\''  +
                ", miejscowość: " + this.city + '\''  +
                ", kod pocztowy: " + this.postCode + '\'' +
                ", powierzchnia: " + this.area + "mk" + '\'' +
                ", numer piętra: " + this.floorNum + '\'' +
                ", cena: " + this.price + "zł" + '\'' +
                ", data zakończenia: " + this.expires + '\'' +
                " }";
    }

    public Mieszkanie(String street, int houseNum, String city, String postCode, double area, int price, LocalDate expires, int flatNum, int floorNum) {
        super(street, houseNum, city, postCode, area, price, expires);
        this.flatNum = flatNum;
        this.floorNum = floorNum;
    }
}
