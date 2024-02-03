package app.prod.model;

/**
 * Defines the contract for location details within the system.
 */
public interface Location {
    /**
     * Retrieves the full details of the location as a {@code String}.
     * The format and content of the returned string depend on the specific implementation
     * and the type of location being represented (e.g., physical address, online meeting URL).
     *
     * @return A string representation of the location's full details.
     */
    String getFullLocationDetails();
}
