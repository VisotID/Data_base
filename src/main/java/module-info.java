module com.inessa.data_base {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.inessa.data_base to javafx.fxml;
    exports com.inessa.data_base;
}