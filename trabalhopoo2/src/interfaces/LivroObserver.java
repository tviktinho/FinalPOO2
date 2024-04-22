package interfaces;

import entidades.livro.Livro;

public interface LivroObserver extends Observer<Livro>{
    void update(Livro livro);
}
