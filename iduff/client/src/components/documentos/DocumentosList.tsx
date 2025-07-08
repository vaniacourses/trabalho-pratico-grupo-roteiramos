import React, { useState } from "react";
import {
  Download,
  FileText,
  Search,
  Calendar,
  User,
  Filter,
} from "lucide-react";
import type { Comprovante } from "../../types";
import { TipoComprovante } from "../../types";
import { useAuth } from "../../context/useAuth";

// Mock data
const mockDocumentos: Comprovante[] = [
  {
    id: "1",
    dataEmissao: "2024-01-15",
    tipo: TipoComprovante.COMPROVANTE_MATRICULA,
    conteudo: "Comprovante de matrícula do semestre 2024.1",
    aluno: {
      id: "1",
      nome: "João Silva",
      matricula: "202401001",
      login: "joao.silva",
      email: "joao.silva@id.uff.br",
      cpf: "123.456.789-00",
      telefone: "(21) 99999-9999",
      tipo: "ALUNO",
      role: "ALUNO",
      status: "ATIVO",
      dataRegistro: new Date("2024-01-10"),
    },
  },
  {
    id: "2",
    dataEmissao: "2024-02-20",
    tipo: TipoComprovante.HISTORICO_ESCOLAR,
    conteudo: "Histórico escolar completo até o semestre 2024.1",
    aluno: {
      id: "1",
      nome: "João Silva",
      matricula: "202401001",
      login: "joao.silva",
      email: "joao.silva@id.uff.br",
      cpf: "123.456.789-00",
      telefone: "(21) 99999-9999",
      tipo: "ALUNO",
      role: "ALUNO",
      status: "ATIVO",
      dataRegistro: new Date("2024-01-10"),
    },
  },
  {
    id: "3",
    dataEmissao: "2024-03-10",
    tipo: TipoComprovante.COMPROVANTE_INSCRICAO,
    conteudo: "Comprovante de inscrição na disciplina Algoritmos",
    aluno: {
      id: "3",
      nome: "Ana Costa",
      matricula: "202401003",
      login: "ana.costa",
      email: "ana.costa@id.uff.br",
      cpf: "111.222.333-44",
      telefone: "(21) 88888-8888",
      tipo: "ALUNO",
      role: "ALUNO",
      status: "ATIVO",
      dataRegistro: new Date("2024-01-10"),
    },
  },
];

const tipoComprovanteLabels = {
  [TipoComprovante.HISTORICO_ESCOLAR]: "Histórico Escolar",
  [TipoComprovante.COMPROVANTE_INSCRICAO]: "Comprovante de Inscrição",
  [TipoComprovante.COMPROVANTE_MATRICULA]: "Comprovante de Matrícula",
  [TipoComprovante.DECLARACAO_CONCLUSAO]: "Declaração de Conclusão",
};

const DocumentosList: React.FC = () => {
  const { user } = useAuth();
  const [searchTerm, setSearchTerm] = useState("");
  const [tipoFilter, setTipoFilter] = useState<TipoComprovante | "TODOS">(
    "TODOS"
  );
  const [documentos] = useState<Comprovante[]>(mockDocumentos);

  const filteredDocumentos = documentos.filter((doc) => {
    const matchesSearch =
      doc.conteudo.toLowerCase().includes(searchTerm.toLowerCase()) ||
      doc.aluno?.nome.toLowerCase().includes(searchTerm.toLowerCase()) ||
      tipoComprovanteLabels[doc.tipo]
        .toLowerCase()
        .includes(searchTerm.toLowerCase());

    const matchesType = tipoFilter === "TODOS" || doc.tipo === tipoFilter;

    // If user is a student, only show their documents
    const matchesUser = user?.tipo !== "ALUNO" || doc.aluno?.id === user?.id;

    return matchesSearch && matchesType && matchesUser;
  });

  const handleDownload = (documento: Comprovante) => {
    console.log("Download documento:", documento);
    // TODO: Implement document download
  };

  const isCoordinatorOrAdmin =
    user?.role === "COORDENADOR" || user?.role === "ADMINISTRADOR";

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Documentos</h1>
          <p className="text-gray-600">
            {user?.tipo === "ALUNO"
              ? "Visualize e baixe seus comprovantes"
              : "Gerencie comprovantes dos alunos"}
          </p>
        </div>
      </div>

      {/* Filters */}
      <div className="flex flex-col sm:flex-row gap-4">
        <div className="relative flex-1">
          <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
          <input
            type="text"
            placeholder="Buscar documentos..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="input pl-10 w-full"
          />
        </div>

        <div className="relative">
          <Filter className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
          <select
            value={tipoFilter}
            onChange={(e) =>
              setTipoFilter(e.target.value as TipoComprovante | "TODOS")
            }
            className="input pl-10 pr-4 min-w-[200px]"
          >
            <option value="TODOS">Todos os tipos</option>
            {Object.entries(tipoComprovanteLabels).map(([key, label]) => (
              <option key={key} value={key}>
                {label}
              </option>
            ))}
          </select>
        </div>
      </div>

      {/* Documents List */}
      <div className="space-y-4">
        {filteredDocumentos.map((documento) => (
          <div key={documento.id} className="card">
            <div className="p-6">
              <div className="flex items-start justify-between">
                <div className="flex-1">
                  <div className="flex items-center gap-3 mb-2">
                    <FileText className="w-5 h-5 text-primary-600" />
                    <h3 className="text-lg font-semibold text-gray-900">
                      {tipoComprovanteLabels[documento.tipo]}
                    </h3>
                    <span className="px-2 py-1 text-xs font-medium bg-primary-100 text-primary-700 rounded-full">
                      {documento.tipo}
                    </span>
                  </div>

                  <p className="text-gray-600 mb-4">{documento.conteudo}</p>

                  <div className="flex items-center gap-6 text-sm text-gray-500">
                    <div className="flex items-center gap-1">
                      <Calendar className="w-4 h-4" />
                      <span>
                        Emitido em{" "}
                        {new Date(documento.dataEmissao).toLocaleDateString(
                          "pt-BR"
                        )}
                      </span>
                    </div>
                    {isCoordinatorOrAdmin && documento.aluno && (
                      <div className="flex items-center gap-1">
                        <User className="w-4 h-4" />
                        <span>
                          {documento.aluno.nome} - {documento.aluno.matricula}
                        </span>
                      </div>
                    )}
                  </div>
                </div>

                <button
                  onClick={() => handleDownload(documento)}
                  className="btn btn-outline flex items-center gap-2"
                  title="Baixar documento"
                >
                  <Download className="w-4 h-4" />
                  Baixar
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Empty State */}
      {filteredDocumentos.length === 0 && (
        <div className="text-center py-12">
          <FileText className="w-12 h-12 text-gray-300 mx-auto mb-4" />
          <h3 className="text-lg font-medium text-gray-900 mb-2">
            {searchTerm || tipoFilter !== "TODOS"
              ? "Nenhum documento encontrado"
              : "Nenhum documento disponível"}
          </h3>
          <p className="text-gray-500">
            {searchTerm || tipoFilter !== "TODOS"
              ? "Tente ajustar os filtros de busca"
              : "Documentos aparecerão aqui quando estiverem disponíveis"}
          </p>
        </div>
      )}
    </div>
  );
};

export default DocumentosList;
