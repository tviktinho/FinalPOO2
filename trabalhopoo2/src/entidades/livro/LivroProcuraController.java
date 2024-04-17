package entidades.livro;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import application.GerenciadorBD;

public class LivroProcuraController {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtEditora;
    @FXML private TextField txtAutor;
    @FXML private ListView<String> listViewLivros;
    @FXML private Button btnCancelar;

    @FXML
    private void onBuscarClick() {
        atualizarLista();
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
    
    

    private void atualizarLista() {
        listViewLivros.getItems().clear();
        String filtroTitulo = txtTitulo.getText().toLowerCase();
        String filtroEditora = txtEditora.getText().toLowerCase();
        String filtroAutor = txtAutor.getText().toLowerCase();

        GerenciadorBD.getInstancia().getLivros().stream()
            .filter(livro -> livro.getTitulo().toLowerCase().contains(filtroTitulo) && 
                             livro.getEditora().toLowerCase().contains(filtroEditora) && 
                             livro.getAutor().toLowerCase().contains(filtroAutor))
            .forEach(livro -> listViewLivros.getItems().add(livro.toString()));
    }
}

