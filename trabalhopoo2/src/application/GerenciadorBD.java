package application;

import java.util.ArrayList;
import java.util.List;

import entidades.editora.Editora;
import entidades.livro.Livro;
import entidades.usuario.Usuario;

public class GerenciadorBD {
    private static GerenciadorBD instancia;

    // Simulando um banco de dados com uma estrutura de dados simples
    private List<Usuario> usuarios;
    private List<Livro> livros;
    private List<Editora> editoras;
    private GerenciadorBD() {
        // Inicializa a lista que simula o banco de dados
        this.usuarios = new ArrayList<>();
        this.livros = new ArrayList<>();
        this.editoras = new ArrayList<>();
    }
    
    public boolean validarUsuario(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(username) && usuario.getSenha().equals(password)) {
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

    public void salvarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }
    
    public void adicionarLivro(Livro livro) {
        this.livros.add(livro);
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
}
