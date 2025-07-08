import React, { useState } from "react";
import { User, Mail, Phone, Calendar, Edit, Save, X } from "lucide-react";
import { useAuth } from "../../context/useAuth";

const Profile: React.FC = () => {
  const { user } = useAuth();
  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    nome: user?.nome || "",
    email: user?.email || "",
    telefone: user?.telefone || "",
  });

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleCancel = () => {
    setIsEditing(false);
    setFormData({
      nome: user?.nome || "",
      email: user?.email || "",
      telefone: user?.telefone || "",
    });
  };

  const handleSave = () => {
    console.log("Save profile:", formData);
    // TODO: Implement profile update
    setIsEditing(false);
  };

  const handleInputChange = (field: string, value: string) => {
    setFormData((prev) => ({
      ...prev,
      [field]: value,
    }));
  };

  if (!user) {
    return (
      <div className="min-h-screen bg-gray-50 p-4 lg:p-8">
        <div className="max-w-4xl mx-auto">
          <div className="text-center py-12">
            <User className="w-12 h-12 text-gray-300 mx-auto mb-4" />
            <h3 className="text-lg font-medium text-gray-900 mb-2">
              Usuário não encontrado
            </h3>
            <p className="text-gray-500">
              Faça login para visualizar seu perfil
            </p>
          </div>
        </div>
      </div>
    );
  }

  const getTipoLabel = (tipo: string) => {
    switch (tipo) {
      case "ALUNO":
        return "Aluno";
      case "PROFESSOR":
        return "Professor";
      case "COORDENADOR":
        return "Coordenador";
      case "ADMINISTRADOR":
        return "Administrador";
      default:
        return tipo;
    }
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case "ATIVO":
        return "bg-green-100 text-green-700";
      case "INATIVO":
        return "bg-gray-100 text-gray-700";
      case "SUSPENSO":
        return "bg-red-100 text-red-700";
      default:
        return "bg-gray-100 text-gray-700";
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 p-4 lg:p-8">
      <div className="max-w-4xl mx-auto space-y-6">
        {/* Header */}
        <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
          <div>
            <h1 className="text-2xl lg:text-3xl font-bold text-gray-900">
              Perfil
            </h1>
            <p className="text-gray-600 mt-1">
              Gerencie suas informações pessoais
            </p>
          </div>
          {!isEditing ? (
            <button
              onClick={handleEdit}
              className="btn-primary flex items-center gap-2"
            >
              <Edit className="w-4 h-4" />
              Editar
            </button>
          ) : (
            <div className="flex gap-2">
              <button
                onClick={handleSave}
                className="btn-primary flex items-center gap-2"
              >
                <Save className="w-4 h-4" />
                Salvar
              </button>
              <button
                onClick={handleCancel}
                className="btn-secondary flex items-center gap-2"
              >
                <X className="w-4 h-4" />
                Cancelar
              </button>
            </div>
          )}
        </div>

        {/* Profile Card */}
        <div className="bg-white rounded-lg shadow-sm border border-gray-200">
          <div className="p-6">
            <div className="flex flex-col lg:flex-row lg:items-start gap-6">
              {/* Avatar */}
              <div className="w-24 h-24 bg-blue-100 rounded-full flex items-center justify-center mx-auto lg:mx-0">
                <User className="w-12 h-12 text-blue-600" />
              </div>

              {/* User Info */}
              <div className="flex-1 space-y-4">
                <div className="flex flex-wrap items-center gap-3 justify-center lg:justify-start">
                  <span
                    className={`px-3 py-1 text-sm font-medium rounded-full ${getStatusColor(
                      user.status
                    )}`}
                  >
                    {user.status}
                  </span>
                  <span className="px-3 py-1 text-sm font-medium bg-blue-100 text-blue-700 rounded-full">
                    {getTipoLabel(user.tipo)}
                  </span>
                </div>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                  {/* Nome */}
                  <div>
                    <label className="form-label">Nome Completo</label>
                    {isEditing ? (
                      <input
                        type="text"
                        value={formData.nome}
                        onChange={(e) =>
                          handleInputChange("nome", e.target.value)
                        }
                        className="form-input"
                      />
                    ) : (
                      <p className="text-gray-900 font-medium">{user.nome}</p>
                    )}
                  </div>

                  {/* Login */}
                  <div>
                    <label className="form-label">Login</label>
                    <p className="text-gray-900 font-medium">{user.login}</p>
                  </div>

                  {/* Email */}
                  <div>
                    <label className="form-label">Email</label>
                    {isEditing ? (
                      <input
                        type="email"
                        value={formData.email}
                        onChange={(e) =>
                          handleInputChange("email", e.target.value)
                        }
                        className="form-input"
                      />
                    ) : (
                      <div className="flex items-center gap-2">
                        <Mail className="w-4 h-4 text-gray-400" />
                        <p className="text-gray-900 font-medium">
                          {user.email}
                        </p>
                      </div>
                    )}
                  </div>

                  {/* Telefone */}
                  <div>
                    <label className="form-label">Telefone</label>
                    {isEditing ? (
                      <input
                        type="tel"
                        value={formData.telefone}
                        onChange={(e) =>
                          handleInputChange("telefone", e.target.value)
                        }
                        className="form-input"
                      />
                    ) : (
                      <div className="flex items-center gap-2">
                        <Phone className="w-4 h-4 text-gray-400" />
                        <p className="text-gray-900 font-medium">
                          {user.telefone}
                        </p>
                      </div>
                    )}
                  </div>

                  {/* CPF */}
                  <div>
                    <label className="form-label">CPF</label>
                    <p className="text-gray-900 font-medium">{user.cpf}</p>
                  </div>

                  {/* Data de Registro */}
                  <div>
                    <label className="form-label">Data de Registro</label>
                    <div className="flex items-center gap-2">
                      <Calendar className="w-4 h-4 text-gray-400" />
                      <p className="text-gray-900 font-medium">
                        {user.dataRegistro
                          ? new Date(user.dataRegistro).toLocaleDateString(
                              "pt-BR"
                            )
                          : "Não informado"}
                      </p>
                    </div>
                  </div>
                </div>

                {/* Additional info for Aluno */}
                {user.tipo === "ALUNO" && (
                  <div className="pt-4 border-t border-gray-100">
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                      <div>
                        <label className="form-label">Matrícula</label>
                        <p className="text-gray-900 font-medium">
                          {/* eslint-disable-next-line @typescript-eslint/no-explicit-any */}
                          {(user as any).matricula || "Não informado"}
                        </p>
                      </div>
                      {user.curso && (
                        <div>
                          <label className="form-label">Curso</label>
                          <p className="text-gray-900 font-medium">
                            {user.curso.nome}
                          </p>
                        </div>
                      )}
                    </div>
                  </div>
                )}

                {/* Additional info for Professor */}
                {user.tipo === "PROFESSOR" && (
                  <div className="pt-4 border-t border-gray-100">
                    <div>
                      <label className="form-label">Departamento</label>
                      <p className="text-gray-900 font-medium">
                        {/* eslint-disable-next-line @typescript-eslint/no-explicit-any */}
                        {(user as any).departamento || "Não informado"}
                      </p>
                    </div>
                  </div>
                )}

                {/* Additional info for Coordenador */}
                {}
                {user.tipo === "COORDENADOR" &&
                  (user as any).cursoCoordenado && (
                    <div className="pt-4 border-t border-gray-100">
                      <div>
                        <label className="form-label">Curso Coordenado</label>
                        <p className="text-gray-900 font-medium">
                          {/* eslint-disable-next-line @typescript-eslint/no-explicit-any */}
                          {(user as any).cursoCoordenado?.nome ||
                            "Não informado"}
                        </p>
                      </div>
                    </div>
                  )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;
