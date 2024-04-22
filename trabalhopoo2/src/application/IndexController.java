package application;

import java.io.IOException;
import java.util.Optional;

import entidades.editora.EditoraController;
import entidades.editora.EditoraProcuraController;
import entidades.livro.EditarLivroController;
import entidades.livro.Livro;
import entidades.livro.LivroController;
import entidades.livro.LivroProcuraController;
import entidades.usuario.ProcurarController;
import entidades.usuario.UsuarioEditController;
import interfaces.LivroObserver;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class IndexController implements LivroObserver {

	@FXML private Button btnSearchUser;
    @FXML private Button btnEditUser;
    @FXML private Button btnDeleteUser;
    @FXML private Button btnCadLivro;
    @FXML private Button btnSearchLivro;
    @FXML private Button btnEditLivro;
    @FXML private Button btnDeleteLivro;
    @FXML private Button btnCadEdit;
    @FXML private Button btnSearchEdit;
    @FXML private Button btnEditEdit;
    @FXML private Button btnDeleteEdit;
    @FXML private TextField txtUserName;
    @FXML private TextField txtUserId;
    @FXML private Button btnLogout;
    @FXML private TextArea txtNotification;

	private String userName = null;
	private int userId = -1;
	private String message = "0 Notifications";

	@FXML
	public void initialize() {
		registerAsObserver();
	}

	@Override
	public void update(Livro livro) {
		String message_notification = "O livro '" + livro.getTitulo() + "' agora tem " + livro.getQuantidade()
				+ " unidades disponíveis.";
		System.out.println("Attempting to update notification: " + message_notification);
		// Garantindo que a atualização da UI ocorra na JavaFX Application Thread
		setMessage(message_notification);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private void updateNotification(String message) {
		if (txtNotification != null) {
			txtNotification.setText(message);
			System.out.println("Notification updated: " + message);
		} else {
			System.out.println("txtNotification is null");
		}
	}

	@FXML
	private void handleLogout() {
		logoutUser();
		redirectToLoginPage();
	}

	private void logoutUser() {
		// Aqui você pode limpar qualquer informação de sessão ou contexto do usuário
		userName = null; // Limpa o nome do usuário
		userId = -1; // Reseta o ID do usuário
	}

	private void redirectToLoginPage() {
		try {
			Stage stage = new Stage(); // Cria uma nova instância de Stage para a tela de login
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/login.fxml")));
			stage.setScene(scene);
			stage.show();

			Stage currentStage = (Stage) btnLogout.getScene().getWindow();
			currentStage.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a tela de login.");
		}
	}

	public void setUserTxtData(String userName, int userId) {
		this.userName = userName;
		this.userId = userId;
		txtUserName.setText(userName);
		txtUserId.setText(String.valueOf(userId));
		updateNotification(message);
	}

	@FXML
	private void handleGoToProcurarUsuario() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/usuario/procurarusuario.fxml"));
			Stage stage = (Stage) btnSearchUser.getScene().getWindow();
			Scene scene = new Scene(loader.load());

			ProcurarController controller = loader.getController();
			controller.setUserTxtData(userName, userId); // Passa os dados do usuário

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a cena: /gui/livro/procurarlivro.fxml");
		}
	}

	@FXML
	private void handleGoToEditarUsuario() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/usuario/editarusuario.fxml"));
			Stage stage = (Stage) btnEditUser.getScene().getWindow();
			Scene scene = new Scene(loader.load());

			UsuarioEditController controller = loader.getController();
			controller.setUserTxtData(userName, userId); // Passa os dados do usuário

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a cena: /gui/livro/procurarlivro.fxml");
		}
	}
	

	@FXML
	private void handleGoToCadastrarLivro() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/livro/cadastrolivro.fxml"));
			Stage stage = (Stage) btnCadLivro.getScene().getWindow();
			Scene scene = new Scene(loader.load());

			LivroController controller = loader.getController();
			controller.setUserTxtData(userName, userId); // Passa os dados do usuário

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a cena: /gui/livro/procurarlivro.fxml");
		}
	}

	@FXML
	private void handleGoToProcurarLivro() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/livro/procurarlivro.fxml"));
			Stage stage = (Stage) btnSearchLivro.getScene().getWindow();
			Scene scene = new Scene(loader.load());

			LivroProcuraController controller = loader.getController();
			controller.setUserTxtData(userName, userId); // Passa os dados do usuário

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a cena: /gui/livro/procurarlivro.fxml");
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

	@FXML
	private void handleGoToDeletarUsuario() {
	    String selectedUser = txtUserName.getText(); // Aqui você deve ajustar para obter o nome do usuário selecionado na UI
	    if (confirmDeletion("Deseja realmente deletar o usuário '" + selectedUser + "'?")) {
	        // Lógica para deletar o usuário
	        System.out.println("Usuário deletado com sucesso.");
	    }
	}
	
	@FXML
	private void handleGoToDeletarLivro() {
	    String selectedBook = "Nome do Livro"; // Isso deve ser substituído pela seleção atual na sua UI
	    if (confirmDeletion("Deseja realmente deletar o livro '" + selectedBook + "'?")) {
	        // Lógica para deletar o livro
	        System.out.println("Livro deletado com sucesso.");
	    }
	}

	

	@FXML
	private void handleGoToCadastrarEditora() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/editora/cadastroeditora.fxml"));
			Stage stage = (Stage) btnCadEdit.getScene().getWindow();
			Scene scene = new Scene(loader.load());

			EditoraController controller = loader.getController();
			controller.setUserTxtData(userName, userId); // Passa os dados do usuário

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a cena: /gui/livro/procurarlivro.fxml");
		}
	}

	@FXML
	private void handleGoToProcurarEditora() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/editora/procurareditora.fxml"));
			Stage stage = (Stage) btnSearchEdit.getScene().getWindow();
			Scene scene = new Scene(loader.load());

			EditoraProcuraController controller = loader.getController();
			controller.setUserTxtData(userName, userId); // Passa os dados do usuário

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a cena: /gui/livro/procurarlivro.fxml");
		}
	}
	
	@FXML
	private void handleGoToDeletarEditora() {
	    String selectedEditor = "Nome da Editora"; // Isso deve ser substituído pela seleção atual na sua UI
	    if (confirmDeletion("Deseja realmente deletar a editora '" + selectedEditor + "'?")) {
	        // Lógica para deletar a editora
	        System.out.println("Editora deletada com sucesso.");
	    }
	}

	@FXML
	private void handleGoToEditarEditora() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/editora/editareditora.fxml"));
			Stage stage = (Stage) btnEditEdit.getScene().getWindow();
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a cena: /gui/editora/editareditora.fxml");
		}
	}
	
	public void registerAsObserver() {
		GerenciadorBDFacade.getInstancia().registerObserver(this);
	}
	
	private boolean confirmDeletion(String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Exclusão");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}