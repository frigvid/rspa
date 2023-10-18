module com.frigvid.rspa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    exports com.frigvid.rspa.test;
    opens com.frigvid.rspa to javafx.fxml;
    exports com.frigvid.rspa;
    exports com.frigvid.rspa.ui;
    opens com.frigvid.rspa.ui to javafx.fxml;
    exports com.frigvid.rspa.ui.fragment;
    opens com.frigvid.rspa.ui.fragment to javafx.fxml;
}