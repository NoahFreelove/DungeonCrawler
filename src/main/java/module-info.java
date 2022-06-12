module com.dungeoncrawler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.dungeoncrawler to javafx.fxml;
    exports com.dungeoncrawler;
    exports com.JEngine.Core.Position;
    opens com.JEngine.Core.Position to javafx.fxml;
    exports com.dungeoncrawler.Entities;
    opens com.dungeoncrawler.Entities to javafx.fxml;
    exports com.dungeoncrawler.Entities.Stairs;
    opens com.dungeoncrawler.Entities.Stairs to javafx.fxml;
    exports com.JEngine.Utility.ImageProcessingAndEffects;
    opens com.JEngine.Utility.ImageProcessingAndEffects to javafx.fxml;
}