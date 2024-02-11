module cmpt213.asn4.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    opens cmpt213.asn4.tictactoe to javafx.fxml;
    exports cmpt213.asn4.tictactoe.ui;
}
