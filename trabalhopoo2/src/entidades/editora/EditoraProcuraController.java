package entidades.editora;

import java.io.IOException;

import application.GerenciadorBDFacadeSingleton;
import application.IndexController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditoraProcuraController {
	@FXML
	private TextField txtNomeBusca;
	@FXML
	private ListView<String> listViewEditoras;
	@FXML
	private Button btnCancelar;
	
    private String userName;
    private int userId;
    
    
	public void setUserTxtData(String userName, int userId) {
		this.userName = userName;
		this.userId = userId;
	}

	@FXML
	private void onProcurarClick() {
		String filtro = txtNomeBusca.getText().trim().toLowerCase();
		listViewEditoras.getItems().clear();
		for (Editora editora : GerenciadorBDFacadeSingleton.getInstancia().getEditoras()) {
			if (editora.getNome().toLowerCase().contains(filtro)) {
				listViewEditoras.getItems().add(editora.getNome());
			}
		}
	}

    @FXML
	public void onBtnCancelarClick() {
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
            System.out.println("Erro ao carregar a cena: /gui/livro/procurareditora.fxml");
        }
	}

}
