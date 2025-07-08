import React from "react";
import { NavLink } from "react-router-dom";
import {
  Home,
  User,
  BookOpen,
  GraduationCap,
  FileText,
  ClipboardList,
  Users,
} from "lucide-react";
import { useAuth } from "../../context/useAuth";

interface SidebarProps {
  isOpen: boolean;
  onClose: () => void;
}

const Sidebar: React.FC<SidebarProps> = ({ isOpen, onClose }) => {
  const { user } = useAuth();

  const getNavigationItems = () => {
    const commonItems = [
      { path: "/dashboard", icon: Home, label: "Dashboard" },
      { path: "/perfil", icon: User, label: "Perfil" },
    ];

    const roleSpecificItems = {
      ALUNO: [
        { path: "/inscricoes", icon: ClipboardList, label: "Inscrições" },
        { path: "/disciplinas", icon: BookOpen, label: "Disciplinas" },
        { path: "/documentos", icon: FileText, label: "Documentos" },
      ],
      PROFESSOR: [
        { path: "/disciplinas", icon: BookOpen, label: "Disciplinas" },
        { path: "/inscricoes", icon: ClipboardList, label: "Inscrições" },
        { path: "/documentos", icon: FileText, label: "Documentos" },
      ],
      COORDENADOR: [
        { path: "/usuarios", icon: Users, label: "Usuários" },
        { path: "/cursos", icon: GraduationCap, label: "Cursos" },
        { path: "/disciplinas", icon: BookOpen, label: "Disciplinas" },
        { path: "/inscricoes", icon: ClipboardList, label: "Inscrições" },
        { path: "/documentos", icon: FileText, label: "Documentos" },
      ],
      ADMINISTRADOR: [
        { path: "/usuarios", icon: Users, label: "Usuários" },
        { path: "/cursos", icon: GraduationCap, label: "Cursos" },
        { path: "/disciplinas", icon: BookOpen, label: "Disciplinas" },
        { path: "/inscricoes", icon: ClipboardList, label: "Inscrições" },
        { path: "/documentos", icon: FileText, label: "Documentos" },
      ],
    };

    return [
      ...commonItems,
      ...(roleSpecificItems[user?.tipo as keyof typeof roleSpecificItems] ||
        []),
    ];
  };

  const handleLinkClick = () => {
    // Close sidebar on mobile when a link is clicked
    if (window.innerWidth < 1024) {
      onClose();
    }
  };

  return (
    <>
      {/* Mobile backdrop */}
      {isOpen && (
        <div
          className="fixed inset-0 bg-gray-600 bg-opacity-75 z-20 lg:hidden"
          onClick={onClose}
        />
      )}

      {/* Sidebar */}
      <aside
        className={`
        fixed inset-y-0 left-0 z-30 w-64 bg-white border-r border-gray-200 transform transition-transform duration-300 ease-in-out
        lg:translate-x-0 lg:static lg:inset-0
        ${isOpen ? "translate-x-0" : "-translate-x-full"}
      `}
      >
        <div className="flex flex-col h-full">
          {/* Sidebar header */}
          <div className="flex items-center justify-between p-6 border-b border-gray-200">
            <h2 className="text-lg font-semibold text-gray-900">Navegação</h2>
          </div>

          {/* Navigation links */}
          <nav className="flex-1 p-4 space-y-1 overflow-y-auto">
            {getNavigationItems().map((item) => (
              <NavLink
                key={item.path}
                to={item.path}
                onClick={handleLinkClick}
                className={({ isActive }) =>
                  `flex items-center gap-3 px-3 py-2 text-sm font-medium rounded-lg transition-colors ${
                    isActive
                      ? "bg-blue-100 text-blue-700"
                      : "text-gray-600 hover:bg-gray-100 hover:text-gray-900"
                  }`
                }
              >
                <item.icon className="w-5 h-5 flex-shrink-0" />
                <span>{item.label}</span>
              </NavLink>
            ))}
          </nav>

          {/* Sidebar footer - user info on mobile */}
          <div className="lg:hidden p-4 border-t border-gray-200">
            <div className="flex items-center gap-3">
              <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center">
                <User className="w-4 h-4 text-blue-600" />
              </div>
              <div className="flex-1 min-w-0">
                <div className="text-sm font-medium text-gray-900 truncate">
                  {user?.nome}
                </div>
                <div className="text-xs text-gray-500">
                  {user?.tipo && user.tipo.toLowerCase()}
                </div>
              </div>
            </div>
          </div>
        </div>
      </aside>
    </>
  );
};

export default Sidebar;
