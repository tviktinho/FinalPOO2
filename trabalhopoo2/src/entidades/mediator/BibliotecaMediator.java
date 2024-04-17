package entidades.mediator;

import entidades.livro.Livro;
import entidades.usuario.Usuario;
import interfaces.AluguelLivroMediator;

public class BibliotecaMediator implements AluguelLivroMediator {

	@Override
	public boolean alugarLivro(Usuario usuario, Livro livro) {
		if (livro.getQuantidade() > 0) {
            livro.setQuantidade(livro.getQuantidade() - 1);
            System.out.println("Livro alugado com sucesso por: " + usuario.getNome());
            return true;
        } else {
            System.out.println("Livro não está disponível para aluguel.");
            return false;
        }
	}
}