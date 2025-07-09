package com.iduff.iduff_server.service;

import com.iduff.iduff_server.dto.DadosUsuario;
import com.iduff.iduff_server.dto.DadosPerfil;
import com.iduff.iduff_server.entity.Usuario;
import com.iduff.iduff_server.enums.TipoUsuario;
import java.util.List;

public interface IUsuarioService {
    Usuario criarUsuario(DadosUsuario dados);

    Usuario autenticarUsuario(String login, String senha);

    Usuario atualizarPerfil(String usuarioId, DadosPerfil dados);

    Usuario obterPerfilUsuario(String usuarioId);

    List<Usuario> listarUsuarios(TipoUsuario tipo);

    void desativarUsuario(String usuarioId);
}
