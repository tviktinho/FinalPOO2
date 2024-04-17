package entidades.usuario;


import java.io.IOException;

import application.GerenciadorBD;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    
    @FXML
	private void onBtnCancelarClick() {
		try {
			Stage stage = (Stage) btnCancelar.getScene().getWindow();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/index.fxml")));
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a cena: /gui/index.fxml");
		}
		
	}
	
	
    


}
