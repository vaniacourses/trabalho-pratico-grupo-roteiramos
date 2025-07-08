import React, { useState } from "react";
import { Plus, Search, Edit, Trash2, Users, BookOpen } from "lucide-react";
import type { Curso } from "../../types";
import { UserRole } from "../../types";
import { useAuth } from "../../context/useAuth";

// Mock data
const mockCursos: Curso[] = [
  {
    id: "1",
    nome: "Ciência da Computação",
    codigo: "CC",
    descricao: "Curso de graduação em Ciência da Computação",
    coordenadorId: "2",
    disciplinasIds: ["1", "2", "3"],
    criadoEm: new Date("2024-01-15"),
    atualizadoEm: new Date("2024-01-15"),
  },
  {
    id: "2",
    nome: "Sistemas de Informação",
    codigo: "SI",
    descricao: "Curso de graduação em Sistemas de Informação",
    coordenadorId: "2",
    disciplinasIds: ["4", "5"],
    criadoEm: new Date("2024-02-10"),
    atualizadoEm: new Date("2024-02-10"),
  },
  {
    id: "3",
    nome: "Engenharia de Software",
    codigo: "ES",
    descricao: "Curso de pós-graduação em Engenharia de Software",
    coordenadorId: "3",
    disciplinasIds: ["6"],
    criadoEm: new Date("2024-03-05"),
    atualizadoEm: new Date("2024-03-05"),
  },
];

const CursosList: React.FC = () => {
  const { user } = useAuth();
  const [searchTerm, setSearchTerm] = useState("");
  const [cursos] = useState<Curso[]>(mockCursos);

  const filteredCursos = cursos.filter(
    (curso) =>
      curso.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
      curso.descricao.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const canManageCursos =
    (user?.role || user?.tipo) === UserRole.ADMINISTRADOR ||
    (user?.role || user?.tipo) === UserRole.COORDENADOR;

  const handleEdit = (curso: Curso) => {
    console.log("Edit curso:", curso);
    // TODO: Implement edit modal
  };

  const handleDelete = (curso: Curso) => {
    console.log("Delete curso:", curso);
    // TODO: Implement delete confirmation
  };

  const handleAddCurso = () => {
    console.log("Add new curso");
    // TODO: Implement add modal
  };

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Cursos</h1>
          <p className="text-gray-600">Gerencie os cursos disponíveis</p>
        </div>
        {canManageCursos && (
          <button
            onClick={handleAddCurso}
            className="btn btn-primary flex items-center gap-2"
          >
            <Plus className="w-4 h-4" />
            Novo Curso
          </button>
        )}
      </div>

      {/* Search */}
      <div className="relative">
        <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
        <input
          type="text"
          placeholder="Buscar cursos..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="input pl-10 w-full max-w-md"
        />
      </div>

      {/* Courses Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {filteredCursos.map((curso) => (
          <div key={curso.id} className="card">
            <div className="p-6">
              <div className="flex items-start justify-between mb-4">
                <div className="flex-1">
                  <h3 className="text-lg font-semibold text-gray-900 mb-2">
                    {curso.nome}
                  </h3>
                  <p className="text-gray-600 text-sm mb-4">
                    {curso.descricao}
                  </p>
                </div>
                {canManageCursos && (
                  <div className="flex gap-2">
                    <button
                      onClick={() => handleEdit(curso)}
                      className="p-2 text-gray-400 hover:text-primary-600 hover:bg-primary-50 rounded-lg transition-colors"
                      title="Editar curso"
                    >
                      <Edit className="w-4 h-4" />
                    </button>
                    <button
                      onClick={() => handleDelete(curso)}
                      className="p-2 text-gray-400 hover:text-red-600 hover:bg-red-50 rounded-lg transition-colors"
                      title="Excluir curso"
                    >
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </div>
                )}
              </div>

              <div className="flex items-center gap-4 text-sm text-gray-500">
                <div className="flex items-center gap-1">
                  <BookOpen className="w-4 h-4" />
                  <span>{curso.disciplinasIds.length} disciplinas</span>
                </div>
                <div className="flex items-center gap-1">
                  <Users className="w-4 h-4" />
                  <span>Coordenador</span>
                </div>
              </div>

              <div className="mt-4 pt-4 border-t border-gray-100">
                <div className="text-xs text-gray-400">
                  Criado em {curso.criadoEm.toLocaleDateString("pt-BR")}
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Empty State */}
      {filteredCursos.length === 0 && (
        <div className="text-center py-12">
          <BookOpen className="w-12 h-12 text-gray-300 mx-auto mb-4" />
          <h3 className="text-lg font-medium text-gray-900 mb-2">
            {searchTerm ? "Nenhum curso encontrado" : "Nenhum curso cadastrado"}
          </h3>
          <p className="text-gray-500">
            {searchTerm
              ? "Tente ajustar os termos de busca"
              : "Comece criando o primeiro curso"}
          </p>
        </div>
      )}
    </div>
  );
};

export default CursosList;
