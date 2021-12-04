package entities;

import java.util.Objects;

public class Address {
    private final String city;
    private final String street;
    private final int house;
    private final int floor;

    public Address(String city, String street, int house, int floor) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floor = floor;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getFloor() {
        return floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(house, address.house) &&
                Objects.equals(floor, address.floor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, floor);
    }
}