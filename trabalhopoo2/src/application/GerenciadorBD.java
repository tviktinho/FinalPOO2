package application;

import java.util.ArrayList;
import java.util.List;

import entidades.editora.Editora;
import entidades.livro.Livro;
import entidades.usuario.Usuario;

public class GerenciadorBD {
	private static GerenciadorBD instancia;
	private IndexController indexController;

	// Simulando um banco de dados com uma estrutura de dados simples
	private List<Usuario> usuarios;
	private List<Livro> livros;
	private List<Editora> editoras;
	

	private GerenciadorBD() {
		// Inicializa a lista que simula o banco de dados
		this.usuarios = new ArrayList<>();
		this.livros = new ArrayList<>();
		this.editoras = new ArrayList<>();
		popularDadosIniciais();
	}
	

	private void popularDadosIniciais() {
		// Usuários
		usuarios.add(new Usuario("Alice Silva", "alice@example.com", "38263826", "senha123", usuarios.size()));
		usuarios.add(new Usuario("Bob Martins", "bob@example.com", "38244826", "senha456", usuarios.size()));
		usuarios.add(new Usuario("1", "1@", "1", "1", usuarios.size()));

		// Livros
		livros.add(new Livro("J.R.R. Tolkien", "O Senhor dos Anéis", "Editora A", 1200, "Fantasia", 5));
		livros.add(new Livro("Robert C. Martin", "Código Limpo", "Editora B", 464, "Programação", 3));
		livros.add(new Livro("Autor", "Livro", "Editora C", 300, "Geral", 10));

		// Editoras
		editoras.add(new Editora("Editora A"));
		editoras.add(new Editora("Editora B"));
		editoras.add(new Editora("3"));
	}

	// Método para adicionar observador aos livros
	public void registerObserver(IndexController observer) {
	    this.indexController = observer;
	    for (Livro livro : livros) {
	        livro.addObserver(observer);
	    }
	}

	public boolean validarUsuario(String username, String password) {

		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equalsIgnoreCase(username) && usuario.getSenha().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public static synchronized GerenciadorBD getInstancia() {
		if (instancia == null) {
			instancia = new GerenciadorBD();
		}
		return instancia;
	}

	public int getIdUsuario(String username) {
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equalsIgnoreCase(username)) {
				return usuario.getId();
			}
		}
		return -1;
	}

	public int getNovoIdUsuario() {
		return usuarios.size() + 1;
	}

	public void salvarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public void salvarLivro(Livro livro) {
		
	}

	public void adicionarLivro(Livro livro) {
		this.livros.add(livro);
	}
	
	public void atualizarLivro(Livro livro) {
	    // Aqui você pode adicionar lógica para atualizar o livro na sua coleção ou banco de dados
	    int index = livros.indexOf(livro);
	    if (index != -1) {
	        livros.set(index, livro);
	    }
	}
	
	public void removerLivro(Livro livro) {
		int index = livros.indexOf(livro);
	    if (index != -1) {
	        livros.remove(index);
	    }

	}

	public void adicionarEditora(Editora editora) {
		this.editoras.add(editora);
	}

	public boolean emailExiste(String email) {
		return usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public Usuario findUsuarioById(int id) {
		return usuarios.stream().filter(usuario -> usuario.getId() == id).findFirst().orElse(null);
	}

	public Livro findLivroByTitulo(String titulo) {
		return livros.stream().filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo)).findFirst().orElse(null);
	}
	
}
