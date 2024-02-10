package app.prod.utility;

import app.prod.enumeration.TransactionType;
import app.prod.exception.NoSuchTransactionTypeException;
import app.prod.exception.TransactionAmountException;
import app.prod.exception.entityInitializationException;
import app.prod.model.Address;
import app.prod.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
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
            logger.info("Address saved successfully.");
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

    public static void saveTransaction(Transaction transaction) {
        try (Connection connection = connectToDatabase()) {
            String insertTransactionSql = "INSERT INTO TRANSACTION (NAME, TRANSACTION_TYPE_ID, AMOUNT, DESCRIPTION, DATE) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTransactionSql);
            preparedStatement.setString(1, transaction.getName());
            preparedStatement.setLong(2, transaction.getTransactionType().getId());
            preparedStatement.setBigDecimal(3, transaction.getAmount());
            preparedStatement.setString(4, transaction.getDescription());
            preparedStatement.setDate(5, Date.valueOf(transaction.getDate()));
            preparedStatement.execute();
            logger.info("Transaction saved successfully.");
        } catch (SQLException | IOException ex) {
            String message = "An error occurred while saving transaction to database!";
            logger.error(message, ex);
            System.out.println(message);
        }
    }

    public static List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection connection = connectToDatabase()) {
            String sqlQuery = "SELECT * FROM TRANSACTION";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            transactions = mapResultSetToTransactionList(resultSet);
        } catch (SQLException | IOException e) {
            String message = "An error occurred while connecting to the database!";
            logger.error(message, e);
            System.out.println(message);
        }
        return transactions;
    }

    private static List<Transaction> mapResultSetToTransactionList(ResultSet resultSet) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("ID");
            String name = resultSet.getString("NAME");
            Long transactionTypeId = resultSet.getLong("TRANSACTION_TYPE_ID");
            BigDecimal amount = resultSet.getBigDecimal("AMOUNT");
            String description = resultSet.getString("DESCRIPTION");
            LocalDate date = resultSet.getDate("DATE").toLocalDate();

            TransactionType transactionType = TransactionType.INCOME;
            try {
                transactionType = TransactionType.findById(transactionTypeId);
            } catch (NoSuchTransactionTypeException ex) {
                logger.error("ID in database doesn't match known transaction type ID. Assigned as income by default.", ex);
            }

            try {
                transactions.add(new Transaction.Builder()
                        .withId(id)
                        .withName(name)
                        .withTransactionType(transactionType)
                        .withAmount(amount)
                        .withDescription(description)
                        .withDate(date)
                        .build());
            } catch (TransactionAmountException ex) {
                logger.error("Transaction with ID " + id + " could not be added due to an invalid amount: ", ex.getMessage());
            } catch (entityInitializationException ex) {
                logger.error("Transaction with ID " + id + " could not be added due to missing or invalid initialization data: ", ex.getMessage());
            }
        }
        return transactions;
    }

    public static List<Transaction> getTransactionsByFilters(Transaction transactionFilter, BigDecimal minAmount, BigDecimal maxAmount) {
        List<Transaction> transactions = new ArrayList<>();
        Map<Integer, Object> queryParams = new HashMap<>();
        int paramOrdinalNumber = 1;


        try (Connection connection = connectToDatabase()) {
            StringBuilder baseSqlQuery = new StringBuilder("SELECT * FROM TRANSACTION WHERE 1=1");

            if (Optional.ofNullable(transactionFilter.getId()).isPresent()) {
                baseSqlQuery.append(" AND ID = ?");
                queryParams.put(paramOrdinalNumber++, transactionFilter.getId());
            }

            if (Optional.ofNullable(transactionFilter.getName()).filter(s -> !s.isEmpty()).isPresent()) {
                baseSqlQuery.append(" AND LOWER(NAME) LIKE ?");
                queryParams.put(paramOrdinalNumber++, "%" + transactionFilter.getName().toLowerCase() + "%");
            }

            if (Optional.ofNullable(transactionFilter.getTransactionType()).isPresent()) {
                baseSqlQuery.append(" AND TRANSACTION_TYPE_ID = ?");
                queryParams.put(paramOrdinalNumber++, transactionFilter.getTransactionType().getId());
            }

            if (Optional.ofNullable(transactionFilter.getDate()).isPresent()) {
                baseSqlQuery.append(" AND DATE = ?");
                queryParams.put(paramOrdinalNumber++, Date.valueOf(transactionFilter.getDate()));
            }


            //Možda popraviti poslije
            if (minAmount != null && maxAmount != null) {
                baseSqlQuery.append(" AND AMOUNT BETWEEN ? AND ?");
                queryParams.put(paramOrdinalNumber++, minAmount);
                queryParams.put(paramOrdinalNumber++, maxAmount);
            } else if (minAmount != null) {
                baseSqlQuery.append(" AND AMOUNT >= ?");
                queryParams.put(paramOrdinalNumber++, minAmount);
            } else if (maxAmount != null) {
                baseSqlQuery.append(" AND AMOUNT <= ?");
                queryParams.put(paramOrdinalNumber++, maxAmount);
            }


            PreparedStatement preparedStatement = connection.prepareStatement(baseSqlQuery.toString());
            logger.info(preparedStatement.toString());

            for (Integer paramNumber : queryParams.keySet()) {
                if (queryParams.get(paramNumber) instanceof String stringQueryParam) {
                    preparedStatement.setString(paramNumber, stringQueryParam);
                } else if (queryParams.get(paramNumber) instanceof Long longQueryParam) {
                    preparedStatement.setLong(paramNumber, longQueryParam);
                } else if (queryParams.get(paramNumber) instanceof Date dateQueryParam) {
                    preparedStatement.setDate(paramNumber, dateQueryParam);
                } else if (queryParams.get(paramNumber) instanceof BigDecimal bigDecimal) {
                    preparedStatement.setBigDecimal(paramNumber, bigDecimal);
                }
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info(resultSet.toString());
            transactions = mapResultSetToTransactionList(resultSet);
            logger.info(transactions.toString());
        } catch (SQLException | IOException ex) {
            String message = "An error occurred while retrieving filtered transactions from the database!";
            logger.error(message, ex);
            System.out.println(message);
        }
        return transactions;
    }
}
