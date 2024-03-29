package app.prod.main;

import app.prod.enumeration.Status;
import app.prod.exception.entityInitializationException;
import app.prod.model.*;
import app.prod.utility.DatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("App started.");

        Address clientAddress = new Address("1234 Main St", "Suite 101", "Springfield");
        Address employeeAddress = new Address("42 View St", "12B", "Springfield");

        Client client = new Client(1L, "Acme Corp", "acme@example.com", clientAddress, "Acme Corporation", new ArrayList<>());
        Employee employee = new Employee(1L, "John Smith", "john.smith@example.com", employeeAddress, "Engineer", new HashSet<>(), new HashSet<>(), new ArrayList<>());

        Set<Task> tasks = new HashSet<>();

        Project project = new Project(1L, "New Website", LocalDate.now(), LocalDate.now().plusDays(60), Status.IN_PROGRESS, client, tasks, new ArrayList<>());

        Location meetingLocation = new VirtualLocation("https://meet.example.com/12345", "Example Meet");
        Set<Contact> participants = new HashSet<>();
        participants.add(client);
        participants.add(employee);
        Meeting meeting = new Meeting(1L, "Project Kickoff", LocalDateTime.now(), LocalDateTime.now().plusHours(1), meetingLocation, participants, "Discuss project scope and milestones");

        try {
            Task task = new Task.Builder()
                    .withId(1L)
                    .withName("Design Phase")
                    .withDescription("Design the main architecture")
                    .withDeadline(LocalDate.now().plusDays(30))
                    .withStatus(Status.IN_PROGRESS)
                    .build();
            // Add the created task to the project's task list
            tasks.add(task);
            // Also add the task to the employee's task set
            employee.getTasks().add(task);

        } catch (entityInitializationException e) {
            logger.error("Task initialization failed: " + e.getMessage(), e);
        }


        System.out.println("Project: " + project.getName());
        System.out.println("Client: " + client.getName());
        System.out.println("Tasks:");
        tasks.forEach(t -> System.out.println(" - " + t.getName() + " due by " + t.getDeadline()));

        System.out.println("Upcoming Meeting: " + meeting.getName() + " at " + meetingLocation.getFullLocationDetails());


        System.out.println("Connecting to database");

        try (Connection connection = DatabaseUtils.connectToDatabase()) {
            logger.info("Connected to database!");
            System.out.println(connection.isClosed());
        } catch (SQLException | IOException ex) {
            String message = "An error occurred while accessing database!";
            logger.error(message, ex);
            System.out.println(message);
        }

        List<Address> addresses = DatabaseUtils.getAddresses();
        addresses.forEach(System.out::println);
        logger.info("App finished executing.");


    }
}
