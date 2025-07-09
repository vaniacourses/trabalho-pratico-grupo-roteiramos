package com.iduff.iduff_server.service.impl;

import com.iduff.iduff_server.service.IUsuarioService;
import com.iduff.iduff_server.dto.DadosUsuario;
import com.iduff.iduff_server.dto.DadosPerfil;
import com.iduff.iduff_server.entity.Usuario;
import com.iduff.iduff_server.entity.Aluno;
import com.iduff.iduff_server.entity.Professor;
import com.iduff.iduff_server.entity.Coordenador;
import com.iduff.iduff_server.enums.TipoUsuario;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    // In-memory storage for demo purposes
    private final Map<String, Usuario> usuarios = new HashMap<>();
    private final Map<String, Usuario> usuariosPorLogin = new HashMap<>();

    @Override
    public Usuario criarUsuario(DadosUsuario dados) {
        Usuario usuario;

        switch (dados.getTipo()) {
            case ALUNO:
                usuario = new Aluno(dados.getNome(), dados.getLogin(), dados.getSenha(), dados.getMatricula());
                break;
            case PROFESSOR:
                usuario = new Professor(dados.getNome(), dados.getLogin(), dados.getSenha(), dados.getDepartamento());
                break;
            case COORDENADOR:
                usuario = new Coordenador(dados.getNome(), dados.getLogin(), dados.getSenha(), null);
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido");
        }

        usuarios.put(usuario.getId(), usuario);
        usuariosPorLogin.put(usuario.getLogin(), usuario);

        return usuario;
    }

    @Override
    public Usuario autenticarUsuario(String login, String senha) {
        Usuario usuario = usuariosPorLogin.get(login);

        if (usuario != null && usuario.validarSenha(senha)) {
            return usuario;
        }

        return null;
    }

    @Override
    public Usuario atualizarPerfil(String usuarioId, DadosPerfil dados) {
        Usuario usuario = usuarios.get(usuarioId);

        if (usuario != null) {
            usuario.atualizarInformacoesBasicas(dados.getNome());

            if (usuario instanceof Professor && dados.getDepartamento() != null) {
                ((Professor) usuario).setDepartamento(dados.getDepartamento());
            }

            if (usuario instanceof Aluno && dados.getMatricula() != null) {
                ((Aluno) usuario).setMatricula(dados.getMatricula());
            }
        }

        return usuario;
    }

    @Override
    public Usuario obterPerfilUsuario(String usuarioId) {
        return usuarios.get(usuarioId);
    }

    @Override
    public List<Usuario> listarUsuarios(TipoUsuario tipo) {
        List<Usuario> resultado = new ArrayList<>();

        for (Usuario usuario : usuarios.values()) {
            if (tipo == null || usuario.getTipo() == tipo) {
                resultado.add(usuario);
            }
        }

        return resultado;
    }

    @Override
    public void desativarUsuario(String usuarioId) {
        Usuario usuario = usuarios.get(usuarioId);

        if (usuario != null) {
            usuariosPorLogin.remove(usuario.getLogin());
            usuarios.remove(usuarioId);
        }
    }
}
