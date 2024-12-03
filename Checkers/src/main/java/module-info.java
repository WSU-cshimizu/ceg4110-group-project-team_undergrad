module org.undergrad.checkers {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.undergrad.checkers.gui to javafx.fxml;
    exports org.undergrad.checkers.gui;
    exports org.undergrad.checkers.game;
    opens org.undergrad.checkers.game to javafx.fxml;
}