package interfaces;

import entidades.livro.Livro;
import entidades.usuario.Usuario;

public interface AluguelLivroMediator {
    boolean alugarLivro(Usuario usuario, Livro livro);
}
