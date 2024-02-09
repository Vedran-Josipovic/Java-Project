module hr.javafx.production.architectureapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires java.sql;


    opens javafx.prod to javafx.fxml;
    exports javafx.prod;

    exports javafx.prod.menu to javafx.fxml;
    exports javafx.prod.address to javafx.fxml;
    exports javafx.prod.transaction to javafx.fxml;

    opens javafx.prod.menu to javafx.fxml;
    opens javafx.prod.address to javafx.fxml;
    opens javafx.prod.transaction to javafx.fxml;

}
