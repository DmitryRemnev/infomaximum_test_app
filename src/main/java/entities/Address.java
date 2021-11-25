package entities;

import java.util.Objects;

public class Address implements Comparable<Address> {
    private final String city;
    private final String street;
    private final String house;
    private final String floor;

    public Address(String city, String street, String house, String floor) {
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

    public String getHouse() {
        return house;
    }

    public String getFloor() {
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

    @Override
    public int compareTo(Address o) {
        int result = this.city.compareTo(o.city);

        if (result == 0) {
            result = this.street.compareTo(o.street);
        }
        if (result == 0) {
            result = this.house.compareTo(o.house);
        }
        if (result == 0) {
            result = this.floor.compareTo(o.floor);
        }

        return result;
    }
}