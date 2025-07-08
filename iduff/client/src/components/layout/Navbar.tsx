import { useAuth } from "../../context/useAuth";
import { LogOut, User, Menu, X } from "lucide-react";
import { Link } from "react-router-dom";

interface NavbarProps {
  onToggleSidebar?: () => void;
  isSidebarOpen?: boolean;
}

export default function Navbar({
  onToggleSidebar,
  isSidebarOpen,
}: NavbarProps) {
  const { user, logout } = useAuth();

  const handleLogout = () => {
    if (window.confirm("Tem certeza que deseja sair?")) {
      logout();
    }
  };

  const getUserTypeLabel = (tipo: string) => {
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

  return (
    <nav className="bg-white border-b border-gray-200 shadow-sm sticky top-0 z-50">
      <div className="mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-16">
          {/* Left side - Logo and mobile menu button */}
          <div className="flex items-center gap-3">
            <button
              onClick={onToggleSidebar}
              className="lg:hidden p-2 rounded-md text-gray-400 hover:text-gray-500 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-blue-500"
              aria-label="Toggle sidebar"
            >
              {isSidebarOpen ? (
                <X className="h-6 w-6" />
              ) : (
                <Menu className="h-6 w-6" />
              )}
            </button>

            <div className="flex items-center gap-3">
              <Link
                to="/dashboard"
                className="flex items-center gap-3 hover:opacity-80 transition-opacity"
              >
                <h1 className="text-xl font-bold text-blue-600">IdUFF</h1>
                <span className="text-sm text-gray-500 hidden sm:inline">
                  Sistema de Inscrições
                </span>
              </Link>
            </div>
          </div>

          {/* Right side - User info and logout */}
          <div className="flex items-center gap-4">
            <div className="flex items-center gap-3">
              <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center">
                <User className="w-4 h-4 text-blue-600" />
              </div>
              <div className="hidden md:block">
                <div className="text-sm font-medium text-gray-900 truncate max-w-32">
                  {user?.nome}
                </div>
                <div className="text-xs text-gray-500">
                  {user?.tipo && getUserTypeLabel(user.tipo)}
                </div>
              </div>
            </div>

            <button
              onClick={handleLogout}
              className="btn-secondary flex items-center gap-2 text-sm"
              title="Sair do sistema"
            >
              <LogOut className="w-4 h-4" />
              <span className="hidden sm:inline">Sair</span>
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
}
