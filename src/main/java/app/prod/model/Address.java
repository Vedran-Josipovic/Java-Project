package app.prod.model;

import java.util.Objects;

/**
 * Represents a physical address as a location.
 * Implements the {@link Location} interface to provide detailed location information.
 */
public class Address implements Location {
    private String street, houseNumber, city;

    /**
     * Constructs a new Address with the specified street, house number, and city.
     *
     * @param street      The street of the address.
     * @param houseNumber The house number of the address.
     * @param city        The city of the address.
     */
    public Address(String street, String houseNumber, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    /**
     * Returns a string representation of the full address, combining street, house number, and city.
     *
     * @return A string representation of the address.
     */
    @Override
    public String getFullLocationDetails() {
        return getStreet() + " " + getHouseNumber() + ", " + getCity();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address that = (Address) o;
        return Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getHouseNumber(), that.getHouseNumber()) && Objects.equals(getCity(), that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getHouseNumber(), getCity());
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
