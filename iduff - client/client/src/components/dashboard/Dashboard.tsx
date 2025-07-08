import React from "react";
import { useAuth } from "../../context/useAuth";
import { TipoUsuario } from "../../types";
import {
  Users,
  BookOpen,
  ClipboardList,
  FileText,
  GraduationCap,
  TrendingUp,
} from "lucide-react";

const Dashboard: React.FC = () => {
  const { user } = useAuth();

  const getWelcomeMessage = () => {
    const timeOfDay =
      new Date().getHours() < 12
        ? "Bom dia"
        : new Date().getHours() < 18
        ? "Boa tarde"
        : "Boa noite";
    return `${timeOfDay}, ${user?.nome}!`;
  };

  const getStatsCards = () => {
    switch (user?.tipo) {
      case TipoUsuario.ALUNO:
        return [
          {
            title: "Disciplinas Ativas",
            value: "5",
            icon: BookOpen,
            color: "blue",
          },
          {
            title: "Inscrições Pendentes",
            value: "2",
            icon: ClipboardList,
            color: "yellow",
          },
          { title: "Documentos", value: "8", icon: FileText, color: "green" },
          {
            title: "Média Geral",
            value: "8.5",
            icon: TrendingUp,
            color: "purple",
          },
        ];
      case TipoUsuario.PROFESSOR:
        return [
          {
            title: "Disciplinas Lecionadas",
            value: "3",
            icon: BookOpen,
            color: "blue",
          },
          {
            title: "Alunos Inscritos",
            value: "45",
            icon: Users,
            color: "green",
          },
          {
            title: "Documentos Pendentes",
            value: "12",
            icon: FileText,
            color: "yellow",
          },
          {
            title: "Avaliações",
            value: "28",
            icon: TrendingUp,
            color: "purple",
          },
        ];
      case TipoUsuario.COORDENADOR:
        return [
          {
            title: "Total de Alunos",
            value: "150",
            icon: Users,
            color: "blue",
          },
          {
            title: "Cursos Ativos",
            value: "5",
            icon: GraduationCap,
            color: "green",
          },
          {
            title: "Disciplinas",
            value: "25",
            icon: BookOpen,
            color: "purple",
          },
          {
            title: "Inscrições",
            value: "320",
            icon: ClipboardList,
            color: "yellow",
          },
        ];
      default:
        return [];
    }
  };

  const getQuickActions = () => {
    switch (user?.tipo) {
      case TipoUsuario.ALUNO:
        return [
          {
            title: "Nova Inscrição",
            description: "Inscrever-se em disciplinas",
            link: "/inscricoes/nova",
          },
          {
            title: "Ver Notas",
            description: "Consultar notas e frequência",
            link: "/disciplinas",
          },
          {
            title: "Enviar Documento",
            description: "Upload de documentos",
            link: "/documentos/novo",
          },
        ];
      case TipoUsuario.PROFESSOR:
        return [
          {
            title: "Lançar Notas",
            description: "Registrar avaliações",
            link: "/disciplinas",
          },
          {
            title: "Gerenciar Turma",
            description: "Visualizar alunos inscritos",
            link: "/inscricoes",
          },
          {
            title: "Documentos",
            description: "Revisar documentos",
            link: "/documentos",
          },
        ];
      case TipoUsuario.COORDENADOR:
        return [
          {
            title: "Novo Usuário",
            description: "Cadastrar usuário",
            link: "/usuarios/novo",
          },
          {
            title: "Nova Disciplina",
            description: "Criar disciplina",
            link: "/disciplinas/nova",
          },
          {
            title: "Relatórios",
            description: "Visualizar relatórios",
            link: "/relatorios",
          },
        ];
      default:
        return [];
    }
  };

  const recentActivities = [
    {
      description: "Nova inscrição aprovada em Cálculo I",
      time: "2 horas atrás",
    },
    { description: "Documento de histórico enviado", time: "1 dia atrás" },
    { description: "Nota lançada em Física II", time: "2 dias atrás" },
    { description: "Inscrição em Programação aprovada", time: "3 dias atrás" },
  ];

  return (
    <div className="min-h-screen bg-gray-50 p-4 lg:p-8">
      <div className="max-w-7xl mx-auto">
        <div className="mb-8">
          <h1 className="text-2xl lg:text-3xl font-bold text-gray-900">
            {getWelcomeMessage()}
          </h1>
          <p className="text-gray-600 mt-2">
            Bem-vindo ao sistema acadêmico da UFF
          </p>
        </div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          {getStatsCards().map((stat, index) => (
            <div
              key={index}
              className={`bg-white rounded-lg shadow-sm border border-gray-200 p-6 ${
                stat.color === "blue"
                  ? "border-l-4 border-l-blue-500"
                  : stat.color === "green"
                  ? "border-l-4 border-l-green-500"
                  : stat.color === "yellow"
                  ? "border-l-4 border-l-yellow-500"
                  : stat.color === "purple"
                  ? "border-l-4 border-l-purple-500"
                  : "border-l-4 border-l-gray-500"
              }`}
            >
              <div className="flex items-center justify-between">
                <div className="flex-1">
                  <p className="text-sm font-medium text-gray-600">
                    {stat.title}
                  </p>
                  <p className="text-3xl font-bold text-gray-900 mt-2">
                    {stat.value}
                  </p>
                </div>
                <div
                  className={`p-3 rounded-full ${
                    stat.color === "blue"
                      ? "bg-blue-100 text-blue-600"
                      : stat.color === "green"
                      ? "bg-green-100 text-green-600"
                      : stat.color === "yellow"
                      ? "bg-yellow-100 text-yellow-600"
                      : stat.color === "purple"
                      ? "bg-purple-100 text-purple-600"
                      : "bg-gray-100 text-gray-600"
                  }`}
                >
                  <stat.icon className="h-6 w-6" />
                </div>
              </div>
            </div>
          ))}
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
            <h2 className="text-lg font-semibold text-gray-900 mb-6">
              Ações Rápidas
            </h2>
            <div className="grid gap-4">
              {getQuickActions().map((action, index) => (
                <div
                  key={index}
                  className="border border-gray-200 rounded-lg p-4 hover:shadow-md transition-shadow"
                >
                  <h3 className="font-medium text-gray-900">{action.title}</h3>
                  <p className="text-sm text-gray-600 mt-1">
                    {action.description}
                  </p>
                  <button className="btn-primary mt-3">Acessar</button>
                </div>
              ))}
            </div>
          </div>

          <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-6">
            <h2 className="text-lg font-semibold text-gray-900 mb-6">
              Atividades Recentes
            </h2>
            <div className="space-y-4">
              {recentActivities.map((activity, index) => (
                <div
                  key={index}
                  className="flex items-start space-x-3 p-3 hover:bg-gray-50 rounded-lg"
                >
                  <div className="w-2 h-2 bg-blue-500 rounded-full mt-2 flex-shrink-0"></div>
                  <div className="flex-1 min-w-0">
                    <p className="text-sm text-gray-900">
                      {activity.description}
                    </p>
                    <p className="text-xs text-gray-500 mt-1">
                      {activity.time}
                    </p>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
