package app.prod;

import java.util.Objects;

/**
 * Represents a generic contact entity with email and physical address details.
 * This abstract class provides a foundation for defining contact types within the system,
 * incorporating both communication and location aspects.
 */
public abstract class Contact extends Entity {
    private String email;
    private Address address; // Changed to use Address object for structured address representation.

    /**
     * Constructs a new Contact instance.
     *
     * @param id      The unique identifier for the contact.
     * @param name    The name of the contact.
     * @param email   The email address of the contact.
     * @param address The physical address of the contact as an {@link Address} object.
     */
    public Contact(Long id, String name, String email, Address address) {
        super(id, name);
        this.email = email;
        this.address = address;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        if (!super.equals(o)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(email, contact.email) && Objects.equals(address, contact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, address);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", address=" + address +
                "} " + super.toString();
    }
}
