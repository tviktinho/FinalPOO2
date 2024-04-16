package entidades.livro;

public class Livro {
    private String editora;
    private String titulo;
    private String autor;
    private int numeroPaginas;
    private String categoria;
    private int quantidade;

    public Livro(String editora, String titulo, String autor, int numeroPaginas, String categoria, int quantidade) {
        this.editora = editora;
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    // Getters e setters
    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " - " + editora + " - " + categoria + " - Páginas: " + numeroPaginas + " - Quantidade: " + quantidade;
    }
}
