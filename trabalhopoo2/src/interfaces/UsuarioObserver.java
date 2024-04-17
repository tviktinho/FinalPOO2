package interfaces;

import entidades.usuario.Usuario;

public interface UsuarioObserver {
    void update(Usuario usuario);
}