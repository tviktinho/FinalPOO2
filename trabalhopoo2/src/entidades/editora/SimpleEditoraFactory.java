package entidades.editora;

import interfaces.EditoraFactory;

public class SimpleEditoraFactory implements EditoraFactory {
    @Override
    public Editora createEditora(String nome) {
        return new Editora(nome);
    }
}
