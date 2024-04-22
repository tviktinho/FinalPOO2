package entidades.usuario;

import java.io.IOException;

import application.GerenciadorBDFacadeSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;


public class CadastroController {

    @FXML
    private TextField txtNome;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtTelefone;
    
    @FXML
    private PasswordField pwdSenha;
    
    @FXML
    private PasswordField pwdConfirmarSenha;
    
    @FXML
    private Label lblMensagem;
    
    @FXML
    private Button btnMostrar;
    
    @FXML
    private Button btnCadastrar;

    @FXML
    protected void onBtnCadastrarClick() {
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = pwdSenha.getText();
        String telefone = txtTelefone.getText();
        String confirmarSenha = pwdConfirmarSenha.getText();
        

        if(validarDados(nome, email, senha, confirmarSenha)) {
            Usuario novoUsuario = new Usuario(nome, email, senha, telefone, GerenciadorBDFacadeSingleton.getInstancia().getNovoIdUsuario());
            GerenciadorBDFacadeSingleton.getInstancia().salvarUsuario(novoUsuario);
            lblMensagem.setText("Cadastro realizado com sucesso!");
        } else {
            lblMensagem.setText("Erro ao cadastrar. Verifique os dados e tente novamente.");
        }
    }

    private boolean validarDados(String nome, String email, String senha, String confirmarSenha) {
        // Implemente a lógica de validação aqui
        // Exemplo simples de validação:
        return nome != null && !nome.isEmpty() &&
               email != null && email.contains("@") &&
               senha != null && !senha.isEmpty() &&
               senha.equals(confirmarSenha);
    }
    
    @FXML
    private void handleVoltar() {
        try {
            Stage stage = (Stage) txtNome.getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/login.fxml")));
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
