package entidades.livro;


import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import application.GerenciadorBD;

public class LivroProcuraController {

    @FXML private TextField txtTitulo;
    @FXML private TextField txtEditora;
    @FXML private TextField txtAutor;
    @FXML private ListView<String> listViewLivros;

    @FXML
    private void onBuscarClick() {
        atualizarLista();
    }

    @FXML
    private void onCancelar() {
        txtTitulo.clear();
        txtEditora.clear();
        txtAutor.clear();
        listViewLivros.getItems().clear();
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
            .forEach(livro -> listViewLivros.getItems().add(livro.toString()));
    }
}

