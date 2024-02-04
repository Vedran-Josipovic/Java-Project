package app.prod.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a meeting with specified details.
 */
public class Meeting extends Entity {
    private LocalDateTime meetingStart;
    private LocalDateTime meetingEnd;
    private Location location;
    private Set<Contact> participants;
    private String notes;

    /**
     * Constructs a new Meeting instance with detailed information.
     *
     * @param id           The unique identifier for the meeting.
     * @param name         The name or title of the meeting.
     * @param meetingStart The start time of the meeting.
     * @param meetingEnd   The end time of the meeting.
     * @param location     The location of the meeting, represented by the {@code Location} interface.
     * @param participants A set of contacts participating in the meeting.
     * @param notes        Additional notes or comments about the meeting.
     */
    public Meeting(Long id, String name, LocalDateTime meetingStart, LocalDateTime meetingEnd, Location location, Set<Contact> participants, String notes) {
        super(id, name);
        this.meetingStart = meetingStart;
        this.meetingEnd = meetingEnd;
        this.location = location;
        this.participants = participants;
        this.notes = notes;
    }

    public LocalDateTime getMeetingStart() {
        return meetingStart;
    }

    public void setMeetingStart(LocalDateTime meetingStart) {
        this.meetingStart = meetingStart;
    }

    public LocalDateTime getMeetingEnd() {
        return meetingEnd;
    }

    public void setMeetingEnd(LocalDateTime meetingEnd) {
        this.meetingEnd = meetingEnd;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Contact> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Contact> participants) {
        this.participants = participants;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(getMeetingStart(), meeting.getMeetingStart()) && Objects.equals(getMeetingEnd(), meeting.getMeetingEnd()) && Objects.equals(getLocation(), meeting.getLocation()) && Objects.equals(getParticipants(), meeting.getParticipants()) && Objects.equals(getNotes(), meeting.getNotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMeetingStart(), getMeetingEnd(), getLocation(), getParticipants(), getNotes());
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingStart=" + meetingStart +
                ", meetingEnd=" + meetingEnd +
                ", location=" + location +
                ", participants=" + participants +
                ", notes='" + notes + '\'' +
                "} " + super.toString();
    }
}
