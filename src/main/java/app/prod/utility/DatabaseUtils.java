package app.prod.utility;

import app.prod.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DatabaseUtils {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtils.class);
    private static final String DATABASE_FILE = "conf/database.properties";

    //Promijeniti na private kad ne budem koristio u mainu
    public static Connection connectToDatabase() throws SQLException, IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(DATABASE_FILE));
        String databaseUrl = properties.getProperty("databaseUrl");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(databaseUrl, username, password);
    }

    public static List<Address> getAddresses() {
        List<Address> addresses = new ArrayList<>();
        try (Connection connection = connectToDatabase()) {
            String sqlQuery = "SELECT * FROM ADDRESS";
            Statement statement = connection.createStatement();
            statement.execute(sqlQuery);
            ResultSet resultSet = statement.getResultSet();
            addresses = mapResultSetToAddressList(resultSet);
        } catch (SQLException | IOException e) {
            String message = "An error occurred while connecting to database!";
            logger.error(message, e);
            System.out.println(message);
        }
        return addresses;
    }

    private static List<Address> mapResultSetToAddressList(ResultSet resultSet) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        while (resultSet.next()) {
            String street = resultSet.getString("STREET");
            String houseNumber = resultSet.getString("HOUSE_NUMBER");
            String city = resultSet.getString("CITY");
            addresses.add(new Address(street, houseNumber, city));
        }
        return addresses;
    }

    public static void saveAddress(Address newAddress) {
        try (Connection connection = connectToDatabase()) {
            String insertAddressSql = "INSERT INTO ADDRESS(STREET, HOUSE_NUMBER, CITY) VALUES(?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql);
            preparedStatement.setString(1, newAddress.getStreet());
            preparedStatement.setString(2, newAddress.getHouseNumber());
            preparedStatement.setString(3, newAddress.getCity());
            preparedStatement.execute();
        } catch (SQLException | IOException ex) {
            String message = "An error occurred while saving address to database!";
            logger.error(message, ex);
            System.out.println(message);
        }
    }

    public static List<Address> getAddressesByFilters(Address addressFilter) {
        List<Address> addresses = new ArrayList<>();
        Map<Integer, Object> queryParams = new HashMap<>();
        int paramOrdinalNumber = 1;

        try (Connection connection = connectToDatabase()) {
            StringBuilder baseSqlQuery = new StringBuilder("SELECT * FROM ADDRESS WHERE 1=1");

            if (Optional.ofNullable(addressFilter.getStreet()).filter(s -> !s.isEmpty()).isPresent()) {
                baseSqlQuery.append(" AND LOWER(STREET) LIKE ?");
                queryParams.put(paramOrdinalNumber++, "%" + addressFilter.getStreet().toLowerCase() + "%");
            }
            if (Optional.ofNullable(addressFilter.getHouseNumber()).filter(s -> !s.isEmpty()).isPresent()) {
                baseSqlQuery.append(" AND LOWER(HOUSE_NUMBER) LIKE ?");
                queryParams.put(paramOrdinalNumber++, addressFilter.getHouseNumber());
            }
            if (Optional.ofNullable(addressFilter.getCity()).filter(s -> !s.isEmpty()).isPresent()) {
                baseSqlQuery.append(" AND LOWER(CITY) LIKE ?");
                queryParams.put(paramOrdinalNumber++, "%" + addressFilter.getCity().toLowerCase() + "%");
            }

            PreparedStatement preparedStatement = connection.prepareStatement(baseSqlQuery.toString());

            for (Integer paramNumber : queryParams.keySet()) {
                if (queryParams.get(paramNumber) instanceof String stringQueryParam) {
                    preparedStatement.setString(paramNumber, stringQueryParam);
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            addresses = mapResultSetToAddressList(resultSet);
        } catch (SQLException | IOException ex) {
            String message = "An error occurred while retrieving filtered addresses from the database!";
            logger.error(message, ex);
            System.out.println(message);
        }
        return addresses;
    }
}
