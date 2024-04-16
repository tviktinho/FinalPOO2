package entidades.livro;

import java.io.IOException;

import application.GerenciadorBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LivroController {
    @FXML private TextField txtEditora;
    @FXML private TextField txtTitulo;
    @FXML private TextField txtAutor;
    @FXML private TextField txtNumeroPaginas;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtQuantidade;
    @FXML private Button btnPesquisar;
    @FXML private Label lblMensagem;

    @FXML
    protected void onBtnCadastrarClick() {
        String editora = txtEditora.getText();
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String numeroPaginas = txtNumeroPaginas.getText();
        String categoria = txtCategoria.getText();
        String quantidade = txtQuantidade.getText();

        if(validarDados(editora, titulo, autor, numeroPaginas, categoria, quantidade)) {
            int numPaginas = Integer.parseInt(numeroPaginas);
            int quant = Integer.parseInt(quantidade);
            Livro novoLivro = new Livro(editora, titulo, autor, numPaginas, categoria, quant);
            GerenciadorBD.getInstancia().adicionarLivro(novoLivro);
            lblMensagem.setText("Cadastro realizado com sucesso!");
        } else {
            lblMensagem.setText("Erro ao cadastrar. Verifique os dados e tente novamente.");
        }
    }

    private boolean validarDados(String editora, String titulo, String autor, String numeroPaginas, String categoria, String quantidade) {
        // Implementação da lógica de validação
        return !editora.isEmpty() && !titulo.isEmpty() && !autor.isEmpty() &&
               !numeroPaginas.isEmpty() && numeroPaginas.matches("\\d+") && // verifica se é um número
               !categoria.isEmpty() && !quantidade.isEmpty() && quantidade.matches("\\d+"); // verifica se é um número
    }
    
    public void onPesquisarLivrosClick(ActionEvent event) {
        try {
            // Carrega a tela de pesquisa de livros
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/livro/procurarlivro.fxml"));
            Parent root = loader.load();
            
            // Pega a cena e o palco atual para abrir a nova cena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Pesquisar Livros");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Imprime a pilha de exceções se algo der errado
        }
    }

    @FXML
    private void onCancelar() {
        // Lógica para cancelar o cadastro
    }
}

