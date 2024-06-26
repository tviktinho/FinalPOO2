package entidades.livro;

import java.io.IOException;

import application.GerenciadorBDFacadeSingleton;
import application.IndexController;
import entidades.mediator.BibliotecaMediator;
import entidades.usuario.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LivroProcuraController  {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtEditora;
    @FXML private TextField txtAutor;
    @FXML private ListView<String> listViewLivros;
    @FXML private Button btnCancelar;
    @FXML private Button btnAlugar;
    @FXML private Button btnProcurar;
    @FXML private Button btnEditLivro;
    @FXML private Button btnVoltar;
    @FXML private Label labelAlugar;
    
    private String userName;
    private int userId;
    String message;

    public void setUserTxtData(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
    }
    
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

            Livro livro = GerenciadorBDFacadeSingleton.getInstancia().findLivroByTitulo(titulo);
            Usuario usuario = GerenciadorBDFacadeSingleton.getInstancia().findUsuarioById(userId);
            
            if (livro != null && usuario != null) {
                BibliotecaMediator mediator = new BibliotecaMediator();
                if (mediator.alugarLivro(usuario, livro)) {
                    atualizarLista();
                    labelAlugar.setText("Livro alugado com sucesso.");
                    
                    notificarIndexController(livro);
                } else {
                	labelAlugar.setText("Falha ao alugar o livro.");
                    
                }
            } else {
            	labelAlugar.setText("Livro ou usuário não encontrado.");
                
            }
        } else {
        	labelAlugar.setText("Selecione um livro para alugar.");
            
        }
    }
    
    private void notificarIndexController(Livro livro) {
        try {
            // Você precisa garantir que esse FXMLLoader aponte para o IndexController corretamente
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/index.fxml"));
            loader.load();  // Carrega os recursos mas não mostra a cena
            IndexController controller = loader.getController();
            controller.update(livro);  // Atualiza a informação no IndexController sem mudar a cena
            message = controller.getMessage();  // Pega a mensagem de sucesso ou erro
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar e atualizar o IndexController.");
        }
    }


    @FXML
    public void onBtnCancelarClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/index.fxml"));
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));

            IndexController controller = loader.getController();            
            
            controller.setMessage(message);
            controller.setUserTxtData(userName, userId);
            
            GerenciadorBDFacadeSingleton.getInstancia().registerObserver(controller); // Re-registra o observador

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a cena: /gui/index.fxml");
        }
    }
    
    @FXML
    private void onBtnEditarClick() {
        String selectedBookInfo = listViewLivros.getSelectionModel().getSelectedItem();
        if (selectedBookInfo != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/editarLivro.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));

                EditarLivroController editarController = loader.getController();
                editarController.initData(selectedBookInfo);  // Passar dados do livro para o controlador de edição

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao abrir a tela de edição de livro.");
            }
        } else {
            System.out.println("Selecione um livro para editar.");
        }
    }
    
    
    @FXML
    private void onBtnDeletarClick() {
        String selectedBookInfo = listViewLivros.getSelectionModel().getSelectedItem();
        if (selectedBookInfo != null) {
            String[] parts = selectedBookInfo.split(" - ");
            String titulo = parts[0];

            Livro livro = GerenciadorBDFacadeSingleton.getInstancia().findLivroByTitulo(titulo);
            if (livro != null) {
                GerenciadorBDFacadeSingleton.getInstancia().removerLivro(livro);  // Assume que você tenha um método para remover livros
                listViewLivros.getItems().remove(selectedBookInfo);
                System.out.println("Livro removido com sucesso.");
            } else {
                System.out.println("Erro ao tentar remover o livro.");
            }
        } else {
            System.out.println("Selecione um livro para deletar.");
        }
    }
    
    @FXML
	private void handleGoToEditarLivro() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/livro/editarlivro.fxml"));
	        Stage stage = (Stage) btnEditLivro.getScene().getWindow();
	        Scene scene = new Scene(loader.load());
	        
	        EditarLivroController controller = loader.getController();
			controller.setUserTxtData(userName, userId); // Passa os dados do usuário
			
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("Erro ao carregar a cena: /gui/livro/editarlivro.fxml");
	    }
	}
    

    private void atualizarLista() {
        listViewLivros.getItems().clear();
        String filtroTitulo = txtTitulo.getText().toLowerCase();
        String filtroEditora = txtEditora.getText().toLowerCase();
        String filtroAutor = txtAutor.getText().toLowerCase();

        GerenciadorBDFacadeSingleton.getInstancia().getLivros().stream()
            .filter(livro -> livro.getTitulo().toLowerCase().contains(filtroTitulo) && 
                             livro.getEditora().toLowerCase().contains(filtroEditora) && 
                             livro.getAutor().toLowerCase().contains(filtroAutor))
            .forEach(livro -> listViewLivros.getItems().add(livro.toString())); // Melhorei para mostrar mais informações do livro
    }
}