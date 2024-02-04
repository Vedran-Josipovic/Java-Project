package app.prod.model;

import app.prod.enumeration.TransactionType;

import java.util.List;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Set;

/**
 * Represents a client in the system, incorporating company-related information and associated projects.
 * Extends the {@code Contact} class to include basic contact information along with a specific company name and projects.
 */
public class Client extends Contact {
    private String companyName;
    private List<Transaction> transactions;

    public Client(Long id, String name, String email, Address address, String companyName, List<Transaction> transactions) {
        super(id, name, email, address);
        this.companyName = companyName;
        this.transactions = transactions;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(getCompanyName(), client.getCompanyName()) && Objects.equals(getTransactions(), client.getTransactions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCompanyName(), getTransactions());
    }

    @Override
    public String toString() {
        return "Client{" +
                "companyName='" + companyName + '\'' +
                ", transactions=" + transactions +
                "} " + super.toString();
    }
}
