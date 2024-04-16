package entidades.editora;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import application.GerenciadorBD;

public class EditoraProcuraController {
    @FXML
    private TextField txtNomeBusca;
    @FXML
    private ListView<String> listViewEditoras;

    @FXML
    private void onProcurarClick() {
        String filtro = txtNomeBusca.getText().trim().toLowerCase();
        listViewEditoras.getItems().clear();
        for (Editora editora : GerenciadorBD.getInstancia().getEditoras()) {
            if (editora.getNome().toLowerCase().contains(filtro)) {
                listViewEditoras.getItems().add(editora.getNome());
            }
        }
    }
}

