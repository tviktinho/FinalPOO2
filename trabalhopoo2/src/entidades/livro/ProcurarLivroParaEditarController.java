package entidades.livro;

import application.GerenciadorBD;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ProcurarLivroParaEditarController {
    @FXML
    private TextField txtSearchTitulo;

    @FXML
    private void handleBuscarLivro() {
        String titulo = txtSearchTitulo.getText();
        Livro livro = GerenciadorBD.getInstancia().findLivroByTitulo(titulo);
        if (livro != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/livro/editarlivro.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                EditarLivroController controller = loader.getController();
                controller.setLivro(livro);
                stage.show();
                
                // Close the current search stage
                Stage currentStage = (Stage) txtSearchTitulo.getScene().getWindow();
                currentStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Livro não encontrado!");
        }
    }
}
