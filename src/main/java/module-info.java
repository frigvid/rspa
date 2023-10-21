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
	exports com.frigvid.rspa.history;
	opens com.frigvid.rspa.history to javafx.fxml;
    exports com.frigvid.rspa.figure.shape;
    opens com.frigvid.rspa.figure.shape to javafx.fxml;
}