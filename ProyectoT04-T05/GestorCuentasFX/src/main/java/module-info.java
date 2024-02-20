module es.iespuertodelacruz.jcg.gestorcuentasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires org.apache.commons.net;

    opens es.iespuertodelacruz.jcg.gestorcuentasfx to javafx.fxml;
    opens es.iespuertodelacruz.jcg.gestorcuentasfx.controller to javafx.fxml;
    exports es.iespuertodelacruz.jcg.gestorcuentasfx;
}
