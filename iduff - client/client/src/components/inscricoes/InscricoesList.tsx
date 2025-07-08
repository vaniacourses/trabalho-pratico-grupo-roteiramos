import React, { useState } from "react";
import {
  ClipboardList,
  Search,
  Filter,
  Calendar,
  MapPin,
  User,
} from "lucide-react";
import type { Inscricao, Solicitacao } from "../../types";
import {
  TipoUsuario,
  StatusInscricao,
  StatusSolicitacao,
  StatusUsuario,
} from "../../types";
import { useAuth } from "../../context/useAuth";

const InscricoesList: React.FC = () => {
  const { user } = useAuth();
  const [activeTab, setActiveTab] = useState<"inscricoes" | "solicitacoes">(
    "inscricoes"
  );
  const [searchTerm, setSearchTerm] = useState("");
  const [filterStatus, setFilterStatus] = useState<string>("");

  // Mock data - in real app this would come from API
  const [inscricoes] = useState<Inscricao[]>([
    {
      id: "1",
      data: "2024-01-15",
      periodo: "2024.1",
      status: StatusInscricao.ATIVA,
      aluno: {
        id: "1",
        nome: "João Silva",
        login: "joao.silva",
        email: "joao.silva@id.uff.br",
        cpf: "123.456.789-01",
        telefone: "(21) 99999-9999",
        tipo: TipoUsuario.ALUNO,
        status: StatusUsuario.ATIVO,
        dataRegistro: new Date(),
        matricula: "2023001",
      },
      turma: {
        id: "1",
        nome: "Turma A",
        limiteVagas: 40,
        local: "Lab 1",
        disciplina: {
          id: "1",
          codigo: "CC101",
          nome: "Programação I",
          cargaHoraria: 60,
          ementa: "Introdução à programação",
        },
        professor: {
          id: "2",
          nome: "Prof. Maria Santos",
          login: "maria.santos",
          email: "maria.santos@id.uff.br",
          cpf: "987.654.321-00",
          telefone: "(21) 88888-8888",
          tipo: TipoUsuario.PROFESSOR,
          status: StatusUsuario.ATIVO,
          dataRegistro: new Date(),
          departamento: "Ciência da Computação",
        },
        horarios: [],
      },
      solicitacao: {
        id: "1",
        data: "2024-01-10",
        status: StatusSolicitacao.APROVADA,
        tipo: "INSCRICAO_DISCIPLINA",
        aluno: {
          id: "1",
          nome: "João Silva",
          login: "joao.silva",
          email: "joao.silva@id.uff.br",
          cpf: "123.456.789-01",
          telefone: "(21) 99999-9999",
          tipo: TipoUsuario.ALUNO,
          status: StatusUsuario.ATIVO,
          dataRegistro: new Date(),
          matricula: "2023001",
        },
        turma: {
          id: "1",
          nome: "Turma A",
          limiteVagas: 40,
          local: "Lab 1",
          disciplina: {
            id: "1",
            codigo: "CC101",
            nome: "Programação I",
            cargaHoraria: 60,
            ementa: "Introdução à programação",
          },
          professor: {
            id: "2",
            nome: "Prof. Maria Santos",
            login: "maria.santos",
            email: "maria.santos@id.uff.br",
            cpf: "987.654.321-00",
            telefone: "(21) 88888-8888",
            tipo: TipoUsuario.PROFESSOR,
            status: StatusUsuario.ATIVO,
            dataRegistro: new Date(),
            departamento: "Ciência da Computação",
          },
          horarios: [],
        },
      },
    },
  ]);

  const [solicitacoes] = useState<Solicitacao[]>([
    {
      id: "2",
      data: "2024-01-20",
      status: StatusSolicitacao.PENDENTE,
      tipo: "INSCRICAO_DISCIPLINA",
      aluno: {
        id: "1",
        nome: "João Silva",
        login: "joao.silva",
        email: "joao.silva@id.uff.br",
        cpf: "123.456.789-01",
        telefone: "(21) 99999-9999",
        tipo: TipoUsuario.ALUNO,
        status: StatusUsuario.ATIVO,
        dataRegistro: new Date(),
        matricula: "2023001",
      },
      turma: {
        id: "2",
        nome: "Turma B",
        limiteVagas: 30,
        local: "Sala 201",
        disciplina: {
          id: "2",
          codigo: "MAT101",
          nome: "Cálculo I",
          cargaHoraria: 80,
          ementa: "Limites e derivadas",
        },
        professor: {
          id: "3",
          nome: "Prof. Carlos Matemático",
          login: "carlos.math",
          email: "carlos.math@id.uff.br",
          cpf: "456.789.123-45",
          telefone: "(21) 77777-7777",
          tipo: TipoUsuario.PROFESSOR,
          status: StatusUsuario.ATIVO,
          dataRegistro: new Date(),
          departamento: "Matemática",
        },
        horarios: [],
      },
    },
  ]);

  const getStatusBadge = (status: StatusInscricao | StatusSolicitacao) => {
    const statusConfig = {
      [StatusInscricao.ATIVA]: {
        label: "Ativa",
        class: "bg-green-100 text-green-800",
      },
      [StatusInscricao.TRANCADA]: {
        label: "Trancada",
        class: "bg-yellow-100 text-yellow-800",
      },
      [StatusInscricao.CANCELADA]: {
        label: "Cancelada",
        class: "bg-red-100 text-red-800",
      },
      [StatusInscricao.CONCLUIDA]: {
        label: "Concluída",
        class: "bg-blue-100 text-blue-800",
      },
      [StatusSolicitacao.PENDENTE]: {
        label: "Pendente",
        class: "bg-yellow-100 text-yellow-800",
      },
      [StatusSolicitacao.APROVADA]: {
        label: "Aprovada",
        class: "bg-green-100 text-green-800",
      },
      [StatusSolicitacao.REPROVADA]: {
        label: "Reprovada",
        class: "bg-red-100 text-red-800",
      },
    };

    const config = statusConfig[status as keyof typeof statusConfig];
    return (
      <span
        className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium ${config.class}`}
      >
        {config.label}
      </span>
    );
  };

  const filteredInscricoes = inscricoes.filter((inscricao) => {
    const matchesSearch =
      inscricao.turma.disciplina.nome
        .toLowerCase()
        .includes(searchTerm.toLowerCase()) ||
      inscricao.turma.disciplina.codigo
        .toLowerCase()
        .includes(searchTerm.toLowerCase());
    const matchesStatus = !filterStatus || inscricao.status === filterStatus;
    return matchesSearch && matchesStatus;
  });

  const filteredSolicitacoes = solicitacoes.filter((solicitacao) => {
    const matchesSearch =
      solicitacao.turma.disciplina.nome
        .toLowerCase()
        .includes(searchTerm.toLowerCase()) ||
      solicitacao.turma.disciplina.codigo
        .toLowerCase()
        .includes(searchTerm.toLowerCase());
    const matchesStatus = !filterStatus || solicitacao.status === filterStatus;
    return matchesSearch && matchesStatus;
  });

  return (
    <div className="min-h-screen bg-gray-50 p-4 lg:p-8">
      <div className="max-w-7xl mx-auto">
        <div className="mb-8">
          <div className="flex flex-col lg:flex-row lg:items-center lg:justify-between">
            <div className="flex items-center mb-4 lg:mb-0">
              <ClipboardList className="h-8 w-8 text-blue-600 mr-3" />
              <div>
                <h1 className="text-2xl lg:text-3xl font-bold text-gray-900">
                  {user?.tipo === TipoUsuario.ALUNO
                    ? "Minhas Inscrições"
                    : "Gerenciar Inscrições"}
                </h1>
                <p className="text-gray-600 mt-1">
                  {user?.tipo === TipoUsuario.ALUNO
                    ? "Acompanhe suas inscrições e solicitações"
                    : "Aprovar e gerenciar inscrições de alunos"}
                </p>
              </div>
            </div>
            {user?.tipo === TipoUsuario.ALUNO && (
              <button className="btn-primary">Nova Inscrição</button>
            )}
          </div>
        </div>

        <div className="bg-white rounded-lg shadow-sm border border-gray-200">
          <div className="border-b border-gray-200">
            <nav className="flex space-x-8 px-6">
              <button
                className={`py-4 px-1 border-b-2 font-medium text-sm ${
                  activeTab === "inscricoes"
                    ? "border-blue-500 text-blue-600"
                    : "border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300"
                }`}
                onClick={() => setActiveTab("inscricoes")}
              >
                Inscrições Confirmadas
              </button>
              <button
                className={`py-4 px-1 border-b-2 font-medium text-sm ${
                  activeTab === "solicitacoes"
                    ? "border-blue-500 text-blue-600"
                    : "border-transparent text-gray-500 hover:text-gray-700 hover:border-gray-300"
                }`}
                onClick={() => setActiveTab("solicitacoes")}
              >
                Solicitações Pendentes
              </button>
            </nav>
          </div>

          <div className="p-6">
            <div className="flex flex-col lg:flex-row lg:items-center lg:justify-between mb-6 space-y-4 lg:space-y-0">
              <div className="relative max-w-md w-full">
                <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" />
                <input
                  type="text"
                  placeholder="Buscar disciplinas..."
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                  className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                />
              </div>

              <div className="flex items-center space-x-2">
                <Filter className="h-4 w-4 text-gray-400" />
                <select
                  value={filterStatus}
                  onChange={(e) => setFilterStatus(e.target.value)}
                  className="form-input w-48"
                >
                  <option value="">Todos os status</option>
                  {activeTab === "inscricoes" ? (
                    <>
                      <option value={StatusInscricao.ATIVA}>Ativa</option>
                      <option value={StatusInscricao.TRANCADA}>Trancada</option>
                      <option value={StatusInscricao.CANCELADA}>
                        Cancelada
                      </option>
                      <option value={StatusInscricao.CONCLUIDA}>
                        Concluída
                      </option>
                    </>
                  ) : (
                    <>
                      <option value={StatusSolicitacao.PENDENTE}>
                        Pendente
                      </option>
                      <option value={StatusSolicitacao.APROVADA}>
                        Aprovada
                      </option>
                      <option value={StatusSolicitacao.REPROVADA}>
                        Reprovada
                      </option>
                    </>
                  )}
                </select>
              </div>
            </div>

            {activeTab === "inscricoes" ? (
              <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                {filteredInscricoes.map((inscricao) => (
                  <div key={inscricao.id} className="card">
                    <div className="flex items-start justify-between mb-4">
                      <div className="flex-1 min-w-0">
                        <h3 className="text-lg font-semibold text-gray-900 truncate">
                          {inscricao.turma.disciplina.nome}
                        </h3>
                        <p className="text-sm text-gray-500">
                          {inscricao.turma.disciplina.codigo}
                        </p>
                      </div>
                      {getStatusBadge(inscricao.status)}
                    </div>

                    <div className="space-y-3">
                      <div className="flex items-center text-sm text-gray-600">
                        <User className="h-4 w-4 mr-2 flex-shrink-0" />
                        <span className="truncate">
                          {inscricao.turma.professor.nome}
                        </span>
                      </div>
                      <div className="flex items-center text-sm text-gray-600">
                        <MapPin className="h-4 w-4 mr-2 flex-shrink-0" />
                        <span>{inscricao.turma.local}</span>
                      </div>
                      <div className="flex items-center text-sm text-gray-600">
                        <Calendar className="h-4 w-4 mr-2 flex-shrink-0" />
                        <span>Período: {inscricao.periodo}</span>
                      </div>
                    </div>

                    <div className="flex flex-col sm:flex-row gap-2 mt-6">
                      <button className="btn-secondary flex-1">
                        Ver Detalhes
                      </button>
                      {user?.tipo === TipoUsuario.ALUNO &&
                        inscricao.status === StatusInscricao.ATIVA && (
                          <button className="btn-danger">Cancelar</button>
                        )}
                    </div>
                  </div>
                ))}
              </div>
            ) : (
              <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                {filteredSolicitacoes.map((solicitacao) => (
                  <div key={solicitacao.id} className="card">
                    <div className="flex items-start justify-between mb-4">
                      <div className="flex-1 min-w-0">
                        <h3 className="text-lg font-semibold text-gray-900 truncate">
                          {solicitacao.turma.disciplina.nome}
                        </h3>
                        <p className="text-sm text-gray-500">
                          {solicitacao.turma.disciplina.codigo}
                        </p>
                      </div>
                      {getStatusBadge(solicitacao.status)}
                    </div>

                    <div className="space-y-3">
                      <div className="flex items-center text-sm text-gray-600">
                        <User className="h-4 w-4 mr-2 flex-shrink-0" />
                        <span className="truncate">
                          {solicitacao.turma.professor.nome}
                        </span>
                      </div>
                      <div className="flex items-center text-sm text-gray-600">
                        <MapPin className="h-4 w-4 mr-2 flex-shrink-0" />
                        <span>{solicitacao.turma.local}</span>
                      </div>
                      <div className="flex items-center text-sm text-gray-600">
                        <Calendar className="h-4 w-4 mr-2 flex-shrink-0" />
                        <span>
                          Solicitado em:{" "}
                          {new Date(solicitacao.data).toLocaleDateString(
                            "pt-BR"
                          )}
                        </span>
                      </div>
                    </div>

                    {solicitacao.motivoRejeicao && (
                      <div className="mt-4 p-3 bg-red-50 border border-red-200 rounded-md">
                        <p className="text-sm text-red-800">
                          <strong>Motivo da rejeição:</strong>{" "}
                          {solicitacao.motivoRejeicao}
                        </p>
                      </div>
                    )}

                    <div className="mt-6">
                      {user?.tipo === TipoUsuario.COORDENADOR &&
                        solicitacao.status === StatusSolicitacao.PENDENTE && (
                          <div className="flex gap-2">
                            <button className="btn-primary flex-1">
                              Aprovar
                            </button>
                            <button className="btn-danger flex-1">
                              Rejeitar
                            </button>
                          </div>
                        )}
                      {user?.tipo === TipoUsuario.ALUNO && (
                        <button className="btn-secondary w-full">
                          Ver Detalhes
                        </button>
                      )}
                    </div>
                  </div>
                ))}
              </div>
            )}

            {(activeTab === "inscricoes"
              ? filteredInscricoes
              : filteredSolicitacoes
            ).length === 0 && (
              <div className="text-center py-12">
                <ClipboardList className="mx-auto h-12 w-12 text-gray-400" />
                <h3 className="mt-4 text-lg font-semibold text-gray-900">
                  Nenhum registro encontrado
                </h3>
                <p className="mt-2 text-gray-600">
                  {activeTab === "inscricoes"
                    ? "Você ainda não possui inscrições confirmadas."
                    : "Não há solicitações pendentes no momento."}
                </p>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default InscricoesList;
