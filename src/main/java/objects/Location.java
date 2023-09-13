package objects;

public class Location {

    public Location(){};
    public Location(String state, String city, int zipCode, String streetName, int addressNumber, int unitNumber) {
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.addressNumber = addressNumber;
        this.unitNumber = unitNumber;
    }
    private String state;
    private String city;
    private int zipCode;
    private String streetName;
    private int addressNumber;

    private int unitNumber = 0;

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public int getZipCode() {
        return zipCode;
    }
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
    public int getAddressNumber() {
        return addressNumber;
    }
    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    @Override
    public String toString() {
        return "Location{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipCode=" + zipCode +
                ", streetName='" + streetName + '\'' +
                ", addressNumber=" + addressNumber +
                ", unitNumber=" + unitNumber +
                '}';
    }
}
