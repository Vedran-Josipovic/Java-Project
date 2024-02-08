package javafx.prod.address;

import app.prod.model.Address;
import app.prod.utility.DatabaseUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class AddressSearchController {
    @FXML
    private TextField addressStreetTextField;
    @FXML
    private TextField addressHouseNumberTextField;
    @FXML
    private TextField addressCityTextField;
    @FXML
    private TableView<Address> addressTableView;

    @FXML
    private TableColumn<Address, String> addressStreetTableColumn;
    @FXML
    private TableColumn<Address, String> addressHouseNumberTableColumn;
    @FXML
    private TableColumn<Address, String> addressCityTableColumn;


    public void initialize() {
        addressStreetTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getStreet()));
        addressHouseNumberTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getHouseNumber()));
        addressCityTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getCity()));
    }

    public void addressSearch(){
        String addressStreet = addressStreetTextField.getText();
        String addressHouseNumber = addressHouseNumberTextField.getText();
        String addressCity = addressCityTextField.getText();

        Address address = new Address(addressStreet,addressHouseNumber,addressCity);

        List<Address> filteredAddresses = DatabaseUtils.getAddressesByFilters(address);

        addressTableView.setItems(FXCollections.observableArrayList(filteredAddresses));
    }




}
