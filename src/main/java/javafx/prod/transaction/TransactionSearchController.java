package javafx.prod.transaction;

import app.prod.enumeration.TransactionType;
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
import java.util.ArrayList;
import java.util.List;

public class TransactionSearchController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionSearchController.class);
    private final ObservableList<String> transactionTypeNames = FXCollections.observableArrayList();
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

    public void initialize() {
        ArrayList<String> transactionNames = new ArrayList<>();
        for (TransactionType t : TransactionType.values()) {
            transactionNames.add(t.getName());
        }

        transactionTypeNames.addAll(transactionNames);

        transactionTypeComboBox.setItems(transactionTypeNames);
        transactionTypeComboBox.getItems().add(0, "All"); // Add "All" option for no filter
        transactionTypeComboBox.getSelectionModel().selectFirst(); // Select "All" by default

        transactionIdTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getId().toString()));
        transactionNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        transactionTypeTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getTransactionType().getName()));
        transactionAmountTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAmount().toString()));
        transactionDateTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDate().toString()));
        transactionDescriptionTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDescription()));
    }

    public void transactionSearch() {
        Long id = parseLongSafely(transactionIdTextField.getText());
        if (id == null && !transactionIdTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid Transaction ID");
            return;
        }
        BigDecimal minAmount = parseBigDecimalSafely(minAmountTextField.getText());
        if (minAmount == null && !minAmountTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid minimum amount format");
            return;
        }
        BigDecimal maxAmount = parseBigDecimalSafely(maxAmountTextField.getText());
        if (maxAmount == null && !maxAmountTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid maximum amount format");
            return;
        }
        if ((minAmount != null && maxAmount != null) && minAmount.compareTo(maxAmount) > 0) {
            showAlert(Alert.AlertType.ERROR, "Error", "Minimum amount can't be greater than maximum amount");
            return;
        }


        Transaction filter = createTransactionFilter();
        List<Transaction> filteredTransactions = DatabaseUtils.getTransactionsByFilters(filter, minAmount, maxAmount);
        transactionTableView.setItems(FXCollections.observableArrayList(filteredTransactions));
    }

    private Transaction createTransactionFilter() {
        // Assuming Transaction class has setters for these fields or a builder pattern
        Transaction filter = new Transaction();
        if (!transactionIdTextField.getText().isEmpty()) {
            filter.setId(Long.valueOf(transactionIdTextField.getText()));
        }
        if (!transactionNameTextField.getText().isEmpty()) {
            filter.setName(transactionNameTextField.getText());
        }
        if (transactionTypeComboBox.getValue() != null && !transactionTypeComboBox.getValue().equals("All")) {
            if (transactionTypeComboBox.getValue().equals(TransactionType.INCOME.getName())) {
                filter.setTransactionType(TransactionType.INCOME);
            } else if (transactionTypeComboBox.getValue().equals(TransactionType.EXPENSE.getName())) {
                filter.setTransactionType(TransactionType.EXPENSE);
            }
        }
        if (transactionDateDatePicker.getValue() != null) {
            filter.setDate(transactionDateDatePicker.getValue());
        }
        return filter;
    }


    private Long parseLongSafely(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            logger.error("Invalid long format " + input);
            return null;
        }
    }

    private BigDecimal parseBigDecimalSafely(String value) {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            logger.error("Invalid BigDecimal format " + value);
            return null;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText("Error");
        alert.setContentText(content);
        alert.showAndWait();
    }
}
