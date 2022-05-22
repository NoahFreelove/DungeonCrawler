module com.dungeoncrawler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.dungeoncrawler to javafx.fxml;
    exports com.dungeoncrawler;
}