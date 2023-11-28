module es.iespuertodelacruz.alumno.chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens es.iespuertodelacruz.alumno.chat to javafx.fxml;
    exports es.iespuertodelacruz.alumno.chat;
}
