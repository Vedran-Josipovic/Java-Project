package javafx.prod.transaction;

import app.prod.enumeration.TransactionType;
import app.prod.exception.TransactionAmountException;
import app.prod.exception.ValidationException;
import app.prod.exception.entityInitializationException;
import app.prod.model.Transaction;
import app.prod.utility.DatabaseUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionSearchController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionSearchController.class);
    @FXML
    private TextField transactionIdTextField;
    @FXML
    private TextField transactionNameTextField;
    @FXML
    private ComboBox<String> transactionTypeComboBox;
    @FXML
    private DatePicker transactionDateDatePicker;
    @FXML
    private TextField minAmountTextField;
    @FXML
    private TextField maxAmountTextField;
    @FXML
    private TableView<Transaction> transactionTableView;

    @FXML
    private TableColumn<Transaction, String> transactionIdTableColumn;
    @FXML
    private TableColumn<Transaction, String> transactionNameTableColumn;
    @FXML
    private TableColumn<Transaction, String> transactionTypeTableColumn;
    @FXML
    private TableColumn<Transaction, String> transactionAmountTableColumn;
    @FXML
    private TableColumn<Transaction, String> transactionDateTableColumn;
    @FXML
    private TableColumn<Transaction, String> transactionDescriptionTableColumn;


    private ObservableList<String> transactionTypeNames = FXCollections.observableArrayList();

    public void initialize() {
        ArrayList<String> transactionNames = new ArrayList<>();
        for (TransactionType t : TransactionType.values()) {
            transactionNames.add(t.getName());
        }

        transactionTypeNames.addAll(transactionNames);

        transactionTypeComboBox.setItems(transactionTypeNames);
        transactionTypeComboBox.getItems().add(0, "All"); // Add "All" option for no filter
        transactionTypeComboBox.getSelectionModel().selectFirst(); // Select "All" by default
    }

    public void transactionSearch() {
        Transaction filter = createTransactionFilter();
        List<Transaction> filteredTransactions = DatabaseUtils.getTransactionsByFilters(filter, parseBigDecimal(minAmountTextField.getText()), parseBigDecimal(maxAmountTextField.getText()));
        transactionTableView.setItems(FXCollections.observableArrayList(filteredTransactions));
    }

    private Transaction createTransactionFilter() {
        // Assuming Transaction class has setters for these fields or a builder pattern
        Transaction filter = new Transaction();
        if (!transactionNameTextField.getText().isEmpty()) {
            filter.setName(transactionNameTextField.getText());
        }
        if (transactionTypeComboBox.getValue() != null && !transactionTypeComboBox.getValue().equals("All")) {
            filter.setTransactionType(TransactionType.valueOf(transactionTypeComboBox.getValue())); // Adjust based on how types are handled
        }
        if (transactionDateDatePicker.getValue() != null) {
            filter.setDate(transactionDateDatePicker.getValue());
        }
        // Amounts are handled separately as they're not part of the Transaction object in this context
        return filter;
    }

    private BigDecimal parseBigDecimal(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null; // No input given
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            logger.error("Invalid number format: " + value, e);
            return null; // Invalid input handled as no filter
        }
    }

}
