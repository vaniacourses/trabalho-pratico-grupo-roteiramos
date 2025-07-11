package com.iduff.iduff_server.controller;

import com.iduff.iduff_server.service.IUsuarioService;
import com.iduff.iduff_server.dto.DadosUsuario;
import com.iduff.iduff_server.dto.DadosPerfil;
import com.iduff.iduff_server.entity.Usuario;
import com.iduff.iduff_server.enums.TipoUsuario;
import com.iduff.iduff_server.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody DadosUsuario dados) {
        try {
            Usuario usuario = usuarioService.criarUsuario(dados);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity<Map<String, Object>> autenticarUsuario(@RequestBody LoginRequest credenciais) {
        String login = credenciais.getLogin();
        String senha = credenciais.getSenha();

        Usuario usuario = usuarioService.autenticarUsuario(login, senha);

        Map<String, Object> response = new HashMap<>();
        if (usuario != null) {
            response.put("usuario", usuario);
            response.put("autenticado", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("autenticado", false);
            response.put("message", "Credenciais inválidas");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PutMapping("/{usuarioId}/perfil")
    public ResponseEntity<Usuario> atualizarPerfil(@PathVariable String usuarioId, @RequestBody DadosPerfil dados) {
        try {
            UUID id = UUID.fromString(usuarioId);
            Usuario usuario = usuarioService.atualizarPerfil(id, dados);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> obterPerfilUsuario(@PathVariable String usuarioId) {
        UUID id = UUID.fromString(usuarioId);
        Usuario usuario = usuarioService.obterPerfilUsuario(id);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(@RequestParam(required = false) TipoUsuario tipo) {
        List<Usuario> usuarios = usuarioService.listarUsuarios(tipo);
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> desativarUsuario(@PathVariable String usuarioId) {
        try {
            UUID id = UUID.fromString(usuarioId);
            usuarioService.desativarUsuario(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
