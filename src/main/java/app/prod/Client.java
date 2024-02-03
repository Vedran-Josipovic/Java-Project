package app.prod;

import java.util.Objects;
import java.util.Set;

/**
 * Represents a client in the system, incorporating company-related information and associated projects.
 * Extends the {@code Contact} class to include basic contact information along with a specific company name and projects.
 */
public class Client extends Contact {
    private String companyName;
    private Set<Project> projects;

    /**
     * Constructs a new Client instance with detailed information including a physical address.
     *
     * @param id          The unique identifier for the client.
     * @param name        The name of the contact person or entity.
     * @param email       The email address of the client.
     * @param address     The {@code Address} object representing the client's physical address.
     * @param companyName The name of the company associated with the client.
     * @param projects    A set of projects linked to the client.
     */
    public Client(Long id, String name, String email, Address address, String companyName, Set<Project> projects) {
        super(id, name, email, address);
        this.companyName = companyName;
        this.projects = projects;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(getCompanyName(), client.getCompanyName()) && Objects.equals(getProjects(), client.getProjects());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCompanyName(), getProjects());
    }

    @Override
    public String toString() {
        return "Client{" +
                "companyName='" + companyName + '\'' +
                ", projects=" + projects +
                "} " + super.toString();
    }
}
