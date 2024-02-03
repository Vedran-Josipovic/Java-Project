module hr.javafx.production.architectureapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens javafx.prod to javafx.fxml;
    exports javafx.prod;
}
