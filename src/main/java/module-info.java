module com.dungeoncrawler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.dungeoncrawler to javafx.fxml;
    exports com.dungeoncrawler;
    exports com.JEngine.Core.Position;
    opens com.JEngine.Core.Position to javafx.fxml;
}