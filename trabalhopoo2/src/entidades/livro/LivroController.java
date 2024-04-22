package entidades.livro;

import java.io.IOException;

import application.GerenciadorBDFacade;
import application.IndexController;
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
    @FXML private Button btnCancel;
    @FXML private Label lblMensagem;
   
    private String userName = null;
    private int userId = -1;
    
	public void setUserTxtData(String userName, int userId) {
		this.userName = userName;
		this.userId = userId;
	}

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
            GerenciadorBDFacade.getInstancia().adicionarLivro(novoLivro);
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
    
 // Chamado para fechar a janela sem salvar
    @FXML
	private void onBtnCancelarClick() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/index.fxml"));
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            IndexController controller = loader.getController();
            controller.setUserTxtData(userName, userId); // Passa os dados do usuário

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a cena: /gui/livro/procurarlivro.fxml");
        }
	}
}

