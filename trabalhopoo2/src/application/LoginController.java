package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblStatus;

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (GerenciadorBD.getInstancia().validarUsuario(username, password)) {
            lblStatus.setText("Login bem-sucedido!");
            openIndexView();
        } else {
            lblStatus.setText("Falha no login. Usuário ou senha incorretos.");
        }
    }

    private void openIndexView() {
        try {
            // Carrega a vista de index
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/index.fxml"));
            Stage stage = (Stage) txtUsername.getScene().getWindow(); // Pega o stage atual
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            lblStatus.setText("Falha ao carregar a tela de índice.");
        }
    }
    
    @FXML
    private void handleGoToCadastro() {
        try {
            Stage stage = (Stage) lblStatus.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/usuario/cadastrousuario.fxml")));
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


