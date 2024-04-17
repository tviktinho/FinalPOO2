package entidades.usuario;

public class Usuario{
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private int id;

    public Usuario(String nome, String email, String senha, String telefone, int id) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.id = id;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String toString() {
        return nome + " - " + email + " - " + telefone;
    }
    
	public int getId() {
		return id;
	}
	
}

