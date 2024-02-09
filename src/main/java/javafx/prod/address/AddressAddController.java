package javafx.prod.address;


import app.prod.exception.ValidationException;
import app.prod.model.Address;
import app.prod.utility.DatabaseUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddressAddController {
    @FXML
    private TextField addressStreetTextField;
    @FXML
    private TextField addressHouseNumberTextField;
    @FXML
    private TextField addressCityTextField;

    public void addAddress() {
        try {
            validateInputs();

            String street = addressStreetTextField.getText().trim();
            String houseNumber = addressHouseNumberTextField.getText().trim();
            String city = addressCityTextField.getText().trim();

            Address newAddress = new Address(street, houseNumber, city);
            DatabaseUtils.saveAddress(newAddress);
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Address added successfully.");
        } catch (ValidationException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", e.getMessage());
        }
    }

    private void validateInputs() throws ValidationException {
        if (addressStreetTextField.getText().isEmpty() || addressHouseNumberTextField.getText().isEmpty() || addressCityTextField.getText().isEmpty()) {
            throw new ValidationException("Please fill in all fields.");
        }
    }

    private void clearForm() {
        addressStreetTextField.clear();
        addressHouseNumberTextField.clear();
        addressCityTextField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
