package entidades.usuario;


import java.util.List;
import java.util.stream.Collectors;
import application.GerenciadorBD;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class ProcurarController {

	@FXML
    private TextField txtNomeProcurar;

	@FXML
    private TextField txtEmailProcurar;
	
	@FXML
    private Button btnPesquisar;
	
	@FXML
    private Button btnCancelar;

    @FXML
    private ListView<String> listViewUsuarios;
    
    

    @FXML
    private void onBtnPesquisarClick() {
        String nomeBusca = txtNomeProcurar.getText().trim();

        listViewUsuarios.getItems().clear(); // Limpa a lista antes de adicionar os novos resultados
        GerenciadorBD.getInstancia().getUsuarios().stream()
            .filter(u -> u.getNome().toLowerCase().contains(nomeBusca.toLowerCase()))
            .forEach(u -> listViewUsuarios.getItems().add(u.toString())); // Adiciona a representação string do usuário
    }


}
