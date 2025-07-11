package com.iduff.iduff_server.service;

import com.iduff.iduff_server.dto.DadosUsuario;
import com.iduff.iduff_server.dto.DadosPerfil;
import com.iduff.iduff_server.entity.Usuario;
import com.iduff.iduff_server.enums.TipoUsuario;
import java.util.List;
import java.util.UUID;

public interface IUsuarioService {
    Usuario criarUsuario(DadosUsuario dados);

    Usuario autenticarUsuario(String login, String senha);

    Usuario atualizarPerfil(UUID usuarioId, DadosPerfil dados);

    Usuario obterPerfilUsuario(UUID usuarioId);

    List<Usuario> listarUsuarios(TipoUsuario tipo);

    void desativarUsuario(UUID usuarioId);
}
