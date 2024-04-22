package interfaces;
import entidades.editora.Editora;

public interface EditoraFactory {
    Editora createEditora(String nome);
}
