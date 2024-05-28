import java.time.LocalDate;

sealed abstract class Budynek permits Dom, Mieszkanie {
    protected String street;
    protected int houseNum;
    protected String city;
    protected String postCode;
    protected double area;
    protected int price;
    protected LocalDate expires;

    public LocalDate getExpireDate() {
        return this.expires;
    }

    public Double getArea() {
        return this.area;
    }

    public String getCity() {
        return this.city;
    }

    public int getPrice() {
        return this.price;
    }

    public Budynek(String street, int houseNum, String city, String postCode, double area, int price, LocalDate expires) {
        this.street = street;
        this.houseNum = houseNum;
        this.city = city;
        this.postCode = postCode;
        this.area = area;
        this.price = price;
        this.expires = expires;
    }
}
