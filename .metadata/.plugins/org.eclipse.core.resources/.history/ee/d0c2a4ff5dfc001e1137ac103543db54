package entidades.editora;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import javafx.scene.Node;
import application.GerenciadorBD;

public class EditoraController {

    @FXML
    private TextField txtNome;
    
    @FXML
    private Label lblMensagem;
    
    @FXML
    protected void onCadastrarClick() {
        String nome = txtNome.getText().trim();

        if (!nome.isEmpty()) {
            Editora novaEditora = new Editora(nome);
            GerenciadorBD.getInstancia().adicionarEditora(novaEditora);
            lblMensagem.setText("Cadastro realizado com sucesso!");
            txtNome.clear(); // Limpa o campo após o cadastro
        } else {
            lblMensagem.setText("Erro ao cadastrar. O nome não pode estar vazio.");
        }
    }

    @FXML
    protected void onCancelarClick() {
        txtNome.clear();
    }
}