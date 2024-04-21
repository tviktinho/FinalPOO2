package entidades.usuario;


import java.io.IOException;

import application.GerenciadorBD;
import application.IndexController;
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
    
    
    private String userName = null;
    private int userId = -1;
    
	public void setUserTxtData(String userName, int userId) {
		this.userName = userName;
		this.userId = userId;
	}
    
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/index.fxml"));
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            IndexController controller = loader.getController();
            controller.setUserTxtData(userName, userId); 

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a cena: /gui/livro/procurarlivro.fxml");
        }
	}
	
	
    


}
