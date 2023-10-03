module com.frigvid.rspa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.frigvid.rspa to javafx.fxml;
    exports com.frigvid.rspa;
    exports com.frigvid.rspa.ui;
    opens com.frigvid.rspa.ui to javafx.fxml;
    exports com.frigvid.rspa.ui.fragments;
    opens com.frigvid.rspa.ui.fragments to javafx.fxml;
}