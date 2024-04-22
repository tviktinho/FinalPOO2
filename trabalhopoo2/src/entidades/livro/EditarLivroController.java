package entidades.livro;

import java.io.IOException;

import application.GerenciadorBDFacade;
import application.IndexController;
import entidades.usuario.ProcurarController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditarLivroController {
    @FXML
    private TextField txtTitulo;
    @FXML
    private TextField txtAutor;
    @FXML
    private TextField txtEditora;
    @FXML
    private TextField txtPaginas;
    @FXML
    private TextField txtQuantidade;
    @FXML
    private ChoiceBox<String> txtCategoria;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Button btnVoltar;

    private Livro livroAtual;
    
    private String userName;
    private int userId;
    String message;
    
    public void setUserTxtData(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
    }
    
	public void setMessage(String message) {
		this.message = message;
	}
	
	private IndexController indexController;

	public void setIndexController(IndexController controller) {
	    this.indexController = controller;
	}

    
    public void initData(String livroInfo) {
        String[] parts = livroInfo.split(" - ");
        // Assume que a string está formatada corretamente
        String titulo = parts[0];

        // Encontrar o livro no "banco de dados"
        livroAtual = GerenciadorBDFacade.getInstancia().findLivroByTitulo(titulo);
        if (livroAtual != null) {
            txtTitulo.setText(livroAtual.getTitulo());
            txtEditora.setText(livroAtual.getEditora());
            txtAutor.setText(livroAtual.getAutor());
            txtPaginas.setText(String.valueOf(livroAtual.getNumeroPaginas()));
            txtQuantidade.setText(String.valueOf(livroAtual.getQuantidade()));
            txtCategoria.setValue(livroAtual.getCategoria());
        }
    }
    

    public void setLivro(Livro livro) {
        this.livroAtual = livro;
        updateFields();
    }

    private void updateFields() {
        if (livroAtual != null) {
            txtTitulo.setText(livroAtual.getTitulo());
            txtAutor.setText(livroAtual.getAutor());
            txtEditora.setText(livroAtual.getEditora());
            txtPaginas.setText(String.valueOf(livroAtual.getNumeroPaginas()));
            txtQuantidade.setText(String.valueOf(livroAtual.getQuantidade()));
            txtCategoria.setValue(livroAtual.getCategoria());
        }
    }

    @FXML
    private void handleSalvar() {
        if (livroAtual != null) {
            livroAtual.setTitulo(txtTitulo.getText());
            livroAtual.setAutor(txtAutor.getText());
            livroAtual.setEditora(txtEditora.getText());
            livroAtual.setNumeroPaginas(Integer.parseInt(txtPaginas.getText()));
            livroAtual.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            livroAtual.setCategoria(txtCategoria.getValue());

            GerenciadorBDFacade.getInstancia().atualizarLivro(livroAtual); // Assume que existe um método para atualizar livros
            System.out.println("Livro atualizado com sucesso!");
            handleCancelar();
        }
    }

    @FXML
    private void handleCancelar() {
    	 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/index.fxml"));
             Stage stage = (Stage) btnCancelar.getScene().getWindow();
             stage.setScene(new Scene(loader.load()));

             IndexController controller = loader.getController();            
             
             controller.setMessage(message);
             controller.setUserTxtData(userName, userId);
             
             GerenciadorBDFacade.getInstancia().registerObserver(controller); // Re-registra o observador
             stage.show();
             
         } catch (IOException e) {
             e.printStackTrace();
             System.out.println("Erro ao carregar a cena: /gui/index.fxml");
         }
    }
    
    @FXML
    private void handleVoltar() {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/usuario/procurarusuario.fxml"));
			Stage stage = (Stage) btnVoltar.getScene().getWindow();
			Scene scene = new Scene(loader.load());

			ProcurarController controller = loader.getController();
			controller.setUserTxtData(userName, userId); // Passa os dados do usuário

			stage.setScene(scene);
			stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a cena: /gui/livro/editarlivro.fxml");
        }
    }

    
    
    
    
}