package javafx.prod.transaction;

import app.prod.enumeration.TransactionType;
import app.prod.exception.TransactionAmountException;
import app.prod.exception.ValidationException;
import app.prod.exception.entityInitializationException;
import app.prod.model.Transaction;
import app.prod.utility.DatabaseUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionAddController {
    @FXML
    private TextField transactionNameTextField;
    @FXML
    private ComboBox<TransactionType> transactionTypeComboBox;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private DatePicker dateDatePicker;

    public void initialize() {
        transactionTypeComboBox.getItems().setAll(TransactionType.values());
    }

    public void addTransaction() {
        try {
            validateInputs();
            Long id = DatabaseUtils.getTransactions().getLast().getId() + 1L;
            String name = transactionNameTextField.getText().trim();
            TransactionType transactionType = transactionTypeComboBox.getValue();
            BigDecimal amount = new BigDecimal(amountTextField.getText().trim());
            String description = descriptionTextField.getText().trim();
            LocalDate date = dateDatePicker.getValue();

            Transaction transaction = new Transaction.Builder()
                    .withId(id)
                    .withName(name)
                    .withTransactionType(transactionType)
                    .withAmount(amount)
                    .withDescription(description)
                    .withDate(date)
                    .build();

            DatabaseUtils.saveTransaction(transaction);
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Transaction added successfully.");
        } catch (TransactionAmountException | entityInitializationException | ValidationException ex) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", ex.getMessage());
        }
    }

    private void validateInputs() throws ValidationException {
        if (transactionNameTextField.getText().isEmpty() ||
                transactionTypeComboBox.getValue() == null ||
                amountTextField.getText().isEmpty() ||
                descriptionTextField.getText().isEmpty() ||
                dateDatePicker.getValue() == null) {
            throw new ValidationException("Please fill in all fields.");
        }

        try {
            new BigDecimal(amountTextField.getText());
        } catch (NumberFormatException e) {
            throw new ValidationException("Amount must be a valid number.");
        }
    }

    private void clearForm() {
        transactionNameTextField.clear();
        transactionTypeComboBox.getSelectionModel().clearSelection();
        amountTextField.clear();
        descriptionTextField.clear();
        dateDatePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
