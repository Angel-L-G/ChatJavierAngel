module es.iespuertodelacruz.jcg.gestorcuentasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens es.iespuertodelacruz.jcg.gestorcuentasfx to javafx.fxml;
    opens es.iespuertodelacruz.jcg.gestorcuentasfx.controller to javafx.fxml;
    exports es.iespuertodelacruz.jcg.gestorcuentasfx;
}
