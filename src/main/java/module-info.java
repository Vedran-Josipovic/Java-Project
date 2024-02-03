module hr.javafx.production.architectureapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;


    opens javafx.prod to javafx.fxml;
    exports javafx.prod;
}
