// Enums as const objects
export const TipoUsuario = {
  ALUNO: "ALUNO",
  PROFESSOR: "PROFESSOR",
  COORDENADOR: "COORDENADOR",
  ADMINISTRADOR: "ADMINISTRADOR",
} as const;

export type TipoUsuario = (typeof TipoUsuario)[keyof typeof TipoUsuario];

// Alias for backward compatibility
export const UserRole = TipoUsuario;
export type UserRole = TipoUsuario;

export const StatusUsuario = {
  ATIVO: "ATIVO",
  INATIVO: "INATIVO",
  SUSPENSO: "SUSPENSO",
} as const;

export type StatusUsuario = (typeof StatusUsuario)[keyof typeof StatusUsuario];

export const StatusSolicitacao = {
  PENDENTE: "PENDENTE",
  APROVADA: "APROVADA",
  REPROVADA: "REPROVADA",
  CANCELADA: "CANCELADA",
} as const;

export type StatusSolicitacao =
  (typeof StatusSolicitacao)[keyof typeof StatusSolicitacao];

export const TipoSolicitacao = {
  INSCRICAO_DISCIPLINA: "INSCRICAO_DISCIPLINA",
  TRANCAMENTO: "TRANCAMENTO",
  CANCELAMENTO_INSCRICAO: "CANCELAMENTO_INSCRICAO",
  MUDANCA_TURMA: "MUDANCA_TURMA",
} as const;

export type TipoSolicitacao =
  (typeof TipoSolicitacao)[keyof typeof TipoSolicitacao];

export const StatusInscricao = {
  ATIVA: "ATIVA",
  TRANCADA: "TRANCADA",
  CANCELADA: "CANCELADA",
  CONCLUIDA: "CONCLUIDA",
} as const;

export type StatusInscricao =
  (typeof StatusInscricao)[keyof typeof StatusInscricao];

export const StatusPlano = {
  ATIVO: "ATIVO",
  INATIVO: "INATIVO",
  CONCLUIDO: "CONCLUIDO",
  TRANCADO: "TRANCADO",
} as const;

export type StatusPlano = (typeof StatusPlano)[keyof typeof StatusPlano];

export const DiaSemana = {
  SEGUNDA: "SEGUNDA",
  TERCA: "TERCA",
  QUARTA: "QUARTA",
  QUINTA: "QUINTA",
  SEXTA: "SEXTA",
  SABADO: "SABADO",
  DOMINGO: "DOMINGO",
} as const;

export type DiaSemana = (typeof DiaSemana)[keyof typeof DiaSemana];

export const TipoComprovante = {
  HISTORICO_ESCOLAR: "HISTORICO_ESCOLAR",
  COMPROVANTE_INSCRICAO: "COMPROVANTE_INSCRICAO",
  COMPROVANTE_MATRICULA: "COMPROVANTE_MATRICULA",
  DECLARACAO_CONCLUSAO: "DECLARACAO_CONCLUSAO",
} as const;

export type TipoComprovante =
  (typeof TipoComprovante)[keyof typeof TipoComprovante];

// Base interfaces
export interface Usuario {
  id: string;
  nome: string;
  login: string;
  email: string;
  cpf: string;
  telefone: string;
  tipo: TipoUsuario;
  role?: TipoUsuario; // Alias for role-based access - optional for backward compatibility
  status: StatusUsuario;
  dataRegistro: Date;
  curso?: Curso;
}

export interface Aluno extends Usuario {
  matricula: string;
  tipo: "ALUNO";
}

export interface Professor extends Usuario {
  departamento: string;
  tipo: "PROFESSOR";
}

export interface Coordenador extends Usuario {
  cursoCoordenado?: Curso;
  tipo: "COORDENADOR";
}

export interface Administrador extends Usuario {
  tipo: "ADMINISTRADOR";
}

export interface Disciplina {
  id: string;
  codigo: string;
  nome: string;
  cargaHoraria: number;
  ementa: string;
  preRequisitos?: Disciplina[];
}

export interface Curso {
  id: string;
  nome: string;
  codigo: string;
  descricao: string;
  coordenadorId: string;
  disciplinasIds: string[];
  criadoEm: Date;
  atualizadoEm: Date;
}

export interface Horario {
  diaSemana: DiaSemana;
  horaInicio: string;
  horaFim: string;
}

export interface Turma {
  id: string;
  nome: string;
  limiteVagas: number;
  local: string;
  disciplina: Disciplina;
  professor: Professor;
  horarios: Horario[];
}

export interface Solicitacao {
  id: string;
  data: string;
  status: StatusSolicitacao;
  tipo: TipoSolicitacao;
  motivoRejeicao?: string;
  aluno: Aluno;
  turma: Turma;
}

export interface Inscricao {
  id: string;
  data: string;
  periodo: string;
  status: StatusInscricao;
  aluno: Aluno;
  turma: Turma;
  solicitacao: Solicitacao;
}

export interface Nota {
  id: string;
  valor: number;
  dataLancamento: string;
  observacoes?: string;
  inscricao: Inscricao;
}

export interface PlanoDeEstudos {
  id: string;
  status: StatusPlano;
  aluno: Aluno;
  disciplinas: Disciplina[];
}

export interface Comprovante {
  id: string;
  dataEmissao: string;
  tipo: TipoComprovante;
  conteudo: string;
  inscricao?: Inscricao;
  aluno?: Aluno;
}

export interface DisciplinaCursada {
  id: string;
  dataConclusao: string;
  disciplina: Disciplina;
  turma: Turma;
  nota: Nota;
  aluno: Aluno;
}

// DTOs for forms
export interface DadosUsuario {
  nome: string;
  login: string;
  senha: string;
  tipo: TipoUsuario;
  matricula?: string;
  departamento?: string;
  cursoId?: string;
}

export interface DadosPerfil {
  nome: string;
  departamento?: string;
  matricula?: string;
}

export interface DadosDisciplina {
  codigo: string;
  nome: string;
  cargaHoraria: number;
  ementa: string;
}

export interface DadosCurso {
  nome: string;
  codigo: string;
}

// Authentication types
export interface LoginCredentials {
  login: string;
  senha: string;
}

export interface AuthResponse {
  usuario: Usuario;
  autenticado: boolean;
  message?: string;
}

// API Response types
export interface ApiResponse<T> {
  data?: T;
  message?: string;
  error?: string;
}

// UI State types
export interface LoadingState {
  isLoading: boolean;
  error?: string;
}

export interface PaginationState {
  page: number;
  pageSize: number;
  total: number;
}

// Dashboard statistics
export interface DashboardStats {
  totalAlunos: number;
  totalDisciplinas: number;
  totalInscricoes: number;
  solicitacoesPendentes: number;
}
