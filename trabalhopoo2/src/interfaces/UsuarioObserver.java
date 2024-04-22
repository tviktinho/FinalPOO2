package interfaces;

import entidades.usuario.Usuario;

public interface UsuarioObserver extends Observer<Usuario>{
    void update(Usuario usuario);
}