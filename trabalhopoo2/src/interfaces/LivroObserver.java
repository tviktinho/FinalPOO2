package interfaces;

import entidades.livro.Livro;

public interface LivroObserver {
    void update(Livro livro);
}
