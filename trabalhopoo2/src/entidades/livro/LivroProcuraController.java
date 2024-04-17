package entidades.livro;

import java.io.IOException;

import application.GerenciadorBD;
import application.IndexController;
import entidades.mediator.BibliotecaMediator;
import entidades.usuario.Usuario;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LivroProcuraController {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtEditora;
    @FXML private TextField txtAutor;
    @FXML private ListView<String> listViewLivros;
    @FXML private Button btnCancelar;
    @FXML private Button btnAlugar;
    @FXML private Button btnProcurar;

    private String userName;
    private int userId;
    private IndexController indexController;

    public void setUserTxtData(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
    }

    public void setObserver(IndexController indexController) {
        this.indexController = indexController;
    }

    @FXML
    private void onBuscarClick() {
        atualizarLista();
    }

    @FXML
    private void onBtnAlugar() {
        String selectedBookInfo = listViewLivros.getSelectionModel().getSelectedItem();
        if (selectedBookInfo != null) {
            String[] parts = selectedBookInfo.split(" - ");
            String titulo = parts[0];

            Livro livro = GerenciadorBD.getInstancia().findLivroByTitulo(titulo);
            Usuario usuario = GerenciadorBD.getInstancia().findUsuarioById(userId);
            
            if (livro != null && usuario != null) {
                BibliotecaMediator mediator = new BibliotecaMediator();
                if (mediator.alugarLivro(usuario, livro)) {
                    atualizarLista();
                    if (indexController != null) {
                        Platform.runLater(() -> indexController.update(livro));  // Assegura que a atualização ocorra na UI Thread
                    }
                    System.out.println("Livro alugado com sucesso.");
                } else {
                    System.out.println("Falha ao alugar o livro.");
                }
            } else {
                System.out.println("Livro ou usuário não encontrado.");
            }
        } else {
            System.out.println("Selecione um livro para alugar.");
        }
    }

    @FXML
   	private void onBtnCancelarClick() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/index.fxml"));
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
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

    private void atualizarLista() {
        listViewLivros.getItems().clear();
        String filtroTitulo = txtTitulo.getText().toLowerCase();
        String filtroEditora = txtEditora.getText().toLowerCase();
        String filtroAutor = txtAutor.getText().toLowerCase();

        GerenciadorBD.getInstancia().getLivros().stream()
            .filter(livro -> livro.getTitulo().toLowerCase().contains(filtroTitulo) && 
                             livro.getEditora().toLowerCase().contains(filtroEditora) && 
                             livro.getAutor().toLowerCase().contains(filtroAutor))
            .forEach(livro -> listViewLivros.getItems().add(livro.toString())); // Melhorei para mostrar mais informações do livro
    }
}
