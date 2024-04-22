package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblStatus;
    @FXML
    private Button btnLogin;
    
    private int idUser;

    @FXML
    private void handleLogin() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (GerenciadorBD.getInstancia().validarUsuario(username, password)) {
            System.out.println("Login bem-sucedido, configurando observador...");
            this.idUser = GerenciadorBD.getInstancia().getIdUsuario(username);
            openIndexView(this.idUser);
        } else {
            System.out.println("Falha no login.");
        }
    }


    private void openIndexView(int idUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/index.fxml"));
            Stage stage = (Stage) txtUsername.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            IndexController controller = loader.getController();
            System.out.println("IndexController instanciado, configurando dados do usuário...");
            controller.setUserTxtData(txtUsername.getText(), idUser);

            // Registra o observador assim que o controlador é acessível
            GerenciadorBD.getInstancia().registerObserver(controller);
            System.out.println("Observador registrado.");

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
