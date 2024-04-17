package entidades.usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class UsuarioEditController {

    private List<Usuario> usuarios;
    private int usuarioIndex;
    private Usuario usuarioAtual;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancelar;
    
    private String userName = null;
    private int userId = -1;
    
	public void setUserTxtData(String userName, int userId) {
		this.userName = userName;
		this.userId = userId;
	}

    // Inicializa o controlador com a lista de usuários e o índice do usuário a editar
    public void initData(List<Usuario> usuarios, int usuarioIndex) {
        this.usuarios = usuarios;
        this.usuarioIndex = usuarioIndex;
        if (usuarioIndex >= 0 && usuarioIndex < usuarios.size()) {
            this.usuarioAtual = usuarios.get(usuarioIndex);
            updateUI();
        } else {
            System.out.println("Erro: Índice do usuário inválido.");
        }
    }

    // Atualiza os campos da interface com as informações do usuário
    private void updateUI() {
        if (usuarioAtual != null) {
            txtNome.setText(usuarioAtual.getNome());
            txtEmail.setText(usuarioAtual.getEmail());
            txtTelefone.setText(usuarioAtual.getTelefone());
            // Continue atualizando outros campos
        }
    }

    // Chamado quando o usuário clica em salvar
    @FXML
    private void handleSalvar() {
        if (usuarioAtual != null) {
            usuarioAtual.setNome(txtNome.getText());
            usuarioAtual.setEmail(txtEmail.getText());
            usuarioAtual.setTelefone(txtTelefone.getText());
            // Atualize outras propriedades do usuário conforme necessário

            // Atualiza o usuário na lista
            usuarios.set(usuarioIndex, usuarioAtual);

            // Chama a função para simular a salvamento no "banco de dados"
            salvarUsuario(usuarioAtual);

            // Fechar a janela após salvar
            closeStage();
        } else {
            System.out.println("Erro: Não há usuário para salvar.");
        }
    }

    // Simula salvar o usuário no banco de dados ou serviço
    private void salvarUsuario(Usuario usuario) {
        System.out.println("Usuário '" + usuario.getNome() + "' atualizado com sucesso!");
        // Aqui você adicionaria a lógica para persistir o usuário alterado no banco de dados
    }

    // Chamado para fechar a janela sem salvar
    @FXML
	private void onBtnCancelarClick() {
		try {
			Stage stage = (Stage) btnCancelar.getScene().getWindow();
			Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui/index.fxml")));
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao carregar a cena: /gui/index.fxml");
		}
		
	}

    // Método para fechar a janela atual
    private void closeStage() {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }
}
