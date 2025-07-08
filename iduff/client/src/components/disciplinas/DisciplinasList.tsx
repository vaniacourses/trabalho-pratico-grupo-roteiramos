import React, { useState } from "react";
import {
  BookOpen,
  Plus,
  Search,
  Edit,
  Trash2,
  Users,
  Clock,
  Filter,
} from "lucide-react";
import type { Disciplina } from "../../types";
import { TipoUsuario } from "../../types";
import { useAuth } from "../../context/useAuth";

const DisciplinasList: React.FC = () => {
  const { user } = useAuth();
  const [searchTerm, setSearchTerm] = useState("");

  // Mock data - in real app this would come from API
  const [disciplinas] = useState<Disciplina[]>([
    {
      id: "1",
      codigo: "CC101",
      nome: "Programação I",
      cargaHoraria: 60,
      ementa:
        "Introdução à programação com foco em lógica de programação e estruturas básicas.",
    },
    {
      id: "2",
      codigo: "MAT101",
      nome: "Cálculo I",
      cargaHoraria: 80,
      ementa: "Limites, derivadas e aplicações do cálculo diferencial.",
    },
    {
      id: "3",
      codigo: "FIS101",
      nome: "Física I",
      cargaHoraria: 60,
      ementa: "Mecânica clássica e introdução à física experimental.",
    },
    {
      id: "4",
      codigo: "CC201",
      nome: "Estruturas de Dados",
      cargaHoraria: 80,
      ementa: "Implementação e análise de estruturas de dados fundamentais.",
      preRequisitos: [
        {
          id: "1",
          codigo: "CC101",
          nome: "Programação I",
          cargaHoraria: 60,
          ementa: "",
        },
      ],
    },
  ]);

  const filteredDisciplinas = disciplinas.filter(
    (disciplina) =>
      disciplina.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
      disciplina.codigo.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const canManageDisciplinas = user?.tipo === TipoUsuario.COORDENADOR;

  return (
    <div className="min-h-screen bg-gray-50 p-4 lg:p-8">
      <div className="max-w-7xl mx-auto space-y-6">
        {/* Header */}
        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
          <div className="flex items-center gap-3">
            <div className="w-10 h-10 bg-blue-100 rounded-lg flex items-center justify-center">
              <BookOpen className="w-5 h-5 text-blue-600" />
            </div>
            <div>
              <h1 className="text-2xl lg:text-3xl font-bold text-gray-900">
                Disciplinas
              </h1>
              <p className="text-gray-600 mt-1">
                {canManageDisciplinas
                  ? "Gerenciar disciplinas do curso"
                  : "Suas disciplinas"}
              </p>
            </div>
          </div>
          {canManageDisciplinas && (
            <button className="btn-primary flex items-center gap-2">
              <Plus className="w-4 h-4" />
              Nova Disciplina
            </button>
          )}
        </div>

        {/* Filters */}
        <div className="bg-white rounded-lg shadow-sm border border-gray-200 p-4">
          <div className="flex flex-col sm:flex-row gap-4">
            <div className="flex-1 relative">
              <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 w-5 h-5 text-gray-400" />
              <input
                type="text"
                placeholder="Buscar disciplinas por nome ou código..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              />
            </div>
            <button className="btn-secondary flex items-center gap-2">
              <Filter className="w-4 h-4" />
              Filtros
            </button>
          </div>
        </div>

        {/* Disciplinas Grid */}
        <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-6">
          {filteredDisciplinas.map((disciplina) => (
            <div
              key={disciplina.id}
              className="bg-white rounded-lg shadow-sm border border-gray-200 hover:shadow-md transition-shadow duration-200"
            >
              {/* Card Header */}
              <div className="p-6 pb-4">
                <div className="flex items-start justify-between mb-4">
                  <div className="flex-1">
                    <div className="flex items-center gap-2 mb-2">
                      <span className="px-2 py-1 text-xs font-medium bg-blue-100 text-blue-700 rounded-full">
                        {disciplina.codigo}
                      </span>
                    </div>
                    <h3 className="text-lg font-semibold text-gray-900 mb-1">
                      {disciplina.nome}
                    </h3>
                  </div>
                  {canManageDisciplinas && (
                    <div className="flex items-center gap-1 ml-4">
                      <button
                        className="p-2 text-gray-400 hover:text-blue-600 hover:bg-blue-50 rounded-lg transition-colors"
                        title="Editar disciplina"
                      >
                        <Edit className="w-4 h-4" />
                      </button>
                      <button
                        className="p-2 text-gray-400 hover:text-red-600 hover:bg-red-50 rounded-lg transition-colors"
                        title="Excluir disciplina"
                      >
                        <Trash2 className="w-4 h-4" />
                      </button>
                    </div>
                  )}
                </div>

                {/* Meta Information */}
                <div className="flex items-center gap-4 mb-4">
                  <div className="flex items-center gap-1 text-sm text-gray-600">
                    <Clock className="w-4 h-4" />
                    <span>{disciplina.cargaHoraria}h</span>
                  </div>
                  <div className="flex items-center gap-1 text-sm text-gray-600">
                    <Users className="w-4 h-4" />
                    <span>45 alunos</span>
                  </div>
                </div>

                {/* Ementa */}
                <p className="text-sm text-gray-600 leading-relaxed mb-4">
                  {disciplina.ementa}
                </p>

                {/* Prerequisites */}
                {disciplina.preRequisitos &&
                  disciplina.preRequisitos.length > 0 && (
                    <div className="mb-4">
                      <h4 className="text-sm font-medium text-gray-900 mb-2">
                        Pré-requisitos:
                      </h4>
                      <div className="flex flex-wrap gap-1">
                        {disciplina.preRequisitos.map((preReq) => (
                          <span
                            key={preReq.id}
                            className="px-2 py-1 text-xs bg-gray-100 text-gray-700 rounded-md"
                          >
                            {preReq.codigo}
                          </span>
                        ))}
                      </div>
                    </div>
                  )}
              </div>

              {/* Card Footer */}
              <div className="p-6 pt-0 border-t border-gray-100">
                <div className="flex gap-2">
                  {user?.tipo === TipoUsuario.ALUNO && (
                    <button className="flex-1 py-2 px-3 text-sm font-medium text-blue-600 bg-blue-50 hover:bg-blue-100 rounded-lg transition-colors">
                      Ver Turmas
                    </button>
                  )}
                  {user?.tipo === TipoUsuario.PROFESSOR && (
                    <button className="flex-1 btn-primary text-sm">
                      Gerenciar Turma
                    </button>
                  )}
                  {user?.tipo === TipoUsuario.COORDENADOR && (
                    <>
                      <button className="flex-1 py-2 px-3 text-sm font-medium text-gray-700 bg-gray-100 hover:bg-gray-200 rounded-lg transition-colors">
                        Ver Turmas
                      </button>
                      <button className="flex-1 btn-primary text-sm">
                        Editar
                      </button>
                    </>
                  )}
                </div>
              </div>
            </div>
          ))}
        </div>

        {/* Empty State */}
        {filteredDisciplinas.length === 0 && (
          <div className="text-center py-12">
            <div className="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-4">
              <BookOpen className="w-8 h-8 text-gray-400" />
            </div>
            <h3 className="text-lg font-medium text-gray-900 mb-2">
              Nenhuma disciplina encontrada
            </h3>
            <p className="text-gray-500 mb-6">
              {searchTerm
                ? "Tente ajustar sua busca ou limpar os filtros."
                : "Não há disciplinas cadastradas no momento."}
            </p>
            {canManageDisciplinas && !searchTerm && (
              <button className="btn-primary">
                <Plus className="w-4 h-4 mr-2" />
                Cadastrar Primeira Disciplina
              </button>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default DisciplinasList;
