package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
public class IndexController {

    @FXML
    private void handleCadastrarUsuario(ActionEvent event) {
    	Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        loadScene("/gui/usuario/cadastrousuario.fxml", stage);
    }

    @FXML
    private void handleProcurarUsuario(ActionEvent event) {
    	Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        loadScene("/gui/usuario/procurarusuario.fxml", stage);
    }

    @FXML
    private void handleCadastrarLivro(ActionEvent event) {
    	Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        loadScene("/gui/livro/cadastrolivro.fxml", stage);
    }

    @FXML
    private void handleProcurarLivro(ActionEvent event) {
    	Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        loadScene("/gui/livro/procurarlivro.fxml", stage);
    }

    @FXML
    private void handleCadastrarEditora(ActionEvent event) {
    	Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        loadScene("/gui/editora/cadastroeditora.fxml", stage);
    }

    @FXML
    private void handleProcurarEditora(ActionEvent event) {
    	Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        loadScene("/gui/editora/procurareditora.fxml", stage);
    }

    private void loadScene(String fxmlFile, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a cena: " + fxmlFile);
        }
    }




}
