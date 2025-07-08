import React, { useState } from "react";
import { User, Plus, Search, Edit, Trash2, Filter } from "lucide-react";
import type { Usuario } from "../../types";
import { TipoUsuario, StatusUsuario } from "../../types";

const UsuariosList: React.FC = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [filterTipo, setFilterTipo] = useState<string>("");
  const [filterStatus, setFilterStatus] = useState<string>("");

  // Mock data - in real app this would come from API
  const [usuarios] = useState<Usuario[]>([
    {
      id: "1",
      nome: "João Silva",
      login: "joao.silva",
      email: "joao.silva@id.uff.br",
      cpf: "123.456.789-01",
      telefone: "(21) 99999-9999",
      tipo: TipoUsuario.ALUNO,
      role: TipoUsuario.ALUNO,
      status: StatusUsuario.ATIVO,
      dataRegistro: new Date("2024-01-15"),
      curso: {
        id: "1",
        nome: "Ciência da Computação",
        codigo: "CC",
        descricao: "Curso de graduação em Ciência da Computação",
        coordenadorId: "3",
        disciplinasIds: ["1", "2", "3"],
        criadoEm: new Date("2020-01-01"),
        atualizadoEm: new Date("2020-01-01"),
      },
    },
    {
      id: "2",
      nome: "Maria Santos",
      login: "maria.santos",
      email: "maria.santos@id.uff.br",
      cpf: "987.654.321-00",
      telefone: "(21) 88888-8888",
      tipo: TipoUsuario.PROFESSOR,
      role: TipoUsuario.PROFESSOR,
      status: StatusUsuario.ATIVO,
      dataRegistro: new Date("2023-08-20"),
    },
    {
      id: "3",
      nome: "Carlos Oliveira",
      login: "carlos.oliveira",
      email: "carlos.oliveira@id.uff.br",
      cpf: "456.789.123-45",
      telefone: "(21) 77777-7777",
      tipo: TipoUsuario.COORDENADOR,
      role: TipoUsuario.COORDENADOR,
      status: StatusUsuario.ATIVO,
      dataRegistro: new Date("2023-03-10"),
    },
  ]);

  const filteredUsuarios = usuarios.filter((usuario) => {
    const matchesSearch =
      usuario.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
      usuario.email.toLowerCase().includes(searchTerm.toLowerCase());
    const matchesTipo = !filterTipo || usuario.tipo === filterTipo;
    const matchesStatus = !filterStatus || usuario.status === filterStatus;

    return matchesSearch && matchesTipo && matchesStatus;
  });

  const getTipoLabel = (tipo: TipoUsuario) => {
    const labels = {
      [TipoUsuario.ALUNO]: "Aluno",
      [TipoUsuario.PROFESSOR]: "Professor",
      [TipoUsuario.COORDENADOR]: "Coordenador",
      [TipoUsuario.ADMINISTRADOR]: "Administrador",
    };
    return labels[tipo] || tipo;
  };

  const getStatusLabel = (status: StatusUsuario) => {
    const labels = {
      [StatusUsuario.ATIVO]: "Ativo",
      [StatusUsuario.INATIVO]: "Inativo",
      [StatusUsuario.SUSPENSO]: "Suspenso",
    };
    return labels[status];
  };

  const getStatusClass = (status: StatusUsuario) => {
    const classes = {
      [StatusUsuario.ATIVO]: "status-active",
      [StatusUsuario.INATIVO]: "status-inactive",
      [StatusUsuario.SUSPENSO]: "status-suspended",
    };
    return classes[status];
  };

  return (
    <div className="page">
      <div className="page-header">
        <div className="page-header-content">
          <div className="page-title-section">
            <User className="page-icon" />
            <div>
              <h1 className="page-title">Usuários</h1>
              <p className="page-subtitle">Gerenciar usuários do sistema</p>
            </div>
          </div>
          <button className="btn btn-primary">
            <Plus className="btn-icon" />
            Novo Usuário
          </button>
        </div>
      </div>

      <div className="page-content">
        <div className="filters-section">
          <div className="search-box">
            <Search className="search-icon" />
            <input
              type="text"
              placeholder="Buscar usuários..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="search-input"
            />
          </div>

          <div className="filters">
            <div className="filter-group">
              <Filter className="filter-icon" />
              <select
                value={filterTipo}
                onChange={(e) => setFilterTipo(e.target.value)}
                className="filter-select"
              >
                <option value="">Todos os tipos</option>
                <option value={TipoUsuario.ALUNO}>Aluno</option>
                <option value={TipoUsuario.PROFESSOR}>Professor</option>
                <option value={TipoUsuario.COORDENADOR}>Coordenador</option>
              </select>
            </div>

            <div className="filter-group">
              <select
                value={filterStatus}
                onChange={(e) => setFilterStatus(e.target.value)}
                className="filter-select"
              >
                <option value="">Todos os status</option>
                <option value={StatusUsuario.ATIVO}>Ativo</option>
                <option value={StatusUsuario.INATIVO}>Inativo</option>
                <option value={StatusUsuario.SUSPENSO}>Suspenso</option>
              </select>
            </div>
          </div>
        </div>

        <div className="table-container">
          <table className="data-table">
            <thead>
              <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Tipo</th>
                <th>Status</th>
                <th>Curso</th>
                <th>Data Registro</th>
                <th>Ações</th>
              </tr>
            </thead>
            <tbody>
              {filteredUsuarios.map((usuario) => (
                <tr key={usuario.id}>
                  <td>
                    <div className="user-info">
                      <div className="user-avatar">
                        {usuario.nome.charAt(0).toUpperCase()}
                      </div>
                      <div>
                        <div className="user-name">{usuario.nome}</div>
                        <div className="user-phone">{usuario.telefone}</div>
                      </div>
                    </div>
                  </td>
                  <td>{usuario.email}</td>
                  <td>
                    <span
                      className={`badge badge-${usuario.tipo.toLowerCase()}`}
                    >
                      {getTipoLabel(usuario.tipo)}
                    </span>
                  </td>
                  <td>
                    <span
                      className={`status ${getStatusClass(usuario.status)}`}
                    >
                      {getStatusLabel(usuario.status)}
                    </span>
                  </td>
                  <td>{usuario.curso?.nome || "-"}</td>
                  <td>{usuario.dataRegistro.toLocaleDateString("pt-BR")}</td>
                  <td>
                    <div className="actions">
                      <button
                        className="action-btn action-btn-edit"
                        title="Editar"
                      >
                        <Edit size={16} />
                      </button>
                      <button
                        className="action-btn action-btn-delete"
                        title="Excluir"
                      >
                        <Trash2 size={16} />
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {filteredUsuarios.length === 0 && (
            <div className="empty-state">
              <User className="empty-icon" />
              <h3>Nenhum usuário encontrado</h3>
              <p>Tente ajustar os filtros ou criar um novo usuário.</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default UsuariosList;
