package entidades.editora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import javafx.scene.Node;
import application.GerenciadorBDFacadeSingleton;
import application.IndexController;
import interfaces.EditoraFactory;

public class EditoraController {

    @FXML
    private TextField txtNome;
    
    @FXML
    private Label lblMensagem;
    
    @FXML
    private Button btnVoltar;
    
    private String userName = null;
    private int userId = -1;
    
	public void setUserTxtData(String userName, int userId) {
		this.userName = userName;
		this.userId = userId;
	}
    
    @FXML
    protected void onCadastrarClick() {
        String nome = txtNome.getText().trim();

        if (!nome.isEmpty()) {
        	EditoraFactory editoraFactory = new SimpleEditoraFactory();
			Editora novaEditora = editoraFactory.createEditora(nome);
            GerenciadorBDFacadeSingleton.getInstancia().adicionarEditora(novaEditora);
            lblMensagem.setText("Cadastro realizado com sucesso!");
            txtNome.clear(); // Limpa o campo após o cadastro
        } else {
            lblMensagem.setText("Erro ao cadastrar. O nome não pode estar vazio.");
        }
    }
    
  
    public void handleVoltar() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/index.fxml"));
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
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