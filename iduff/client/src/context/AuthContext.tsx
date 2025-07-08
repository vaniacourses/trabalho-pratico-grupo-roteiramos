import { createContext, useState, useEffect } from "react";
import type { ReactNode } from "react";
import type { Usuario, LoginCredentials } from "../types";

interface AuthContextType {
  user: Usuario | null;
  isAuthenticated: boolean;
  isLoading: boolean;
  login: (credentials: LoginCredentials) => Promise<boolean>;
  logout: () => void;
  updateUser: (user: Usuario) => void;
}

export const AuthContext = createContext<AuthContextType | undefined>(
  undefined
);

interface AuthProviderProps {
  children: ReactNode;
}

export function AuthProvider({ children }: AuthProviderProps) {
  const [user, setUser] = useState<Usuario | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // Check if user is already logged in (from localStorage)
    const savedUser = localStorage.getItem("user");
    if (savedUser) {
      try {
        setUser(JSON.parse(savedUser));
      } catch (error) {
        console.error("Error parsing saved user:", error);
        localStorage.removeItem("user");
      }
    }
    setIsLoading(false);
  }, []);

  const login = async (credentials: LoginCredentials): Promise<boolean> => {
    setIsLoading(true);
    try {
      // Mock API call - replace with actual API call later
      const mockUsers = [
        {
          id: "1",
          nome: "João Silva",
          login: "joao.silva",
          email: "joao.silva@id.uff.br",
          cpf: "123.456.789-00",
          telefone: "(21) 99999-9999",
          tipo: "ALUNO" as const,
          role: "ALUNO" as const,
          status: "ATIVO" as const,
          dataRegistro: new Date("2023-01-15"),
          matricula: "2023001",
        },
        {
          id: "2",
          nome: "Prof. Maria Santos",
          login: "maria.santos",
          email: "maria.santos@id.uff.br",
          cpf: "987.654.321-00",
          telefone: "(21) 88888-8888",
          tipo: "PROFESSOR" as const,
          role: "PROFESSOR" as const,
          status: "ATIVO" as const,
          dataRegistro: new Date("2022-08-20"),
          departamento: "Ciência da Computação",
        },
        {
          id: "3",
          nome: "Dr. Carlos Coordenador",
          login: "carlos.coord",
          email: "carlos.coord@id.uff.br",
          cpf: "111.222.333-44",
          telefone: "(21) 77777-7777",
          tipo: "COORDENADOR" as const,
          role: "COORDENADOR" as const,
          status: "ATIVO" as const,
          dataRegistro: new Date("2022-03-10"),
        },
      ];

      const user = mockUsers.find((u) => u.login === credentials.login);

      if (user && credentials.senha === "123456") {
        setUser(user);
        localStorage.setItem("user", JSON.stringify(user));
        setIsLoading(false);
        return true;
      } else {
        setIsLoading(false);
        return false;
      }
    } catch (error) {
      console.error("Login error:", error);
      setIsLoading(false);
      return false;
    }
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem("user");
  };

  const updateUser = (updatedUser: Usuario) => {
    setUser(updatedUser);
    localStorage.setItem("user", JSON.stringify(updatedUser));
  };

  const value = {
    user,
    isAuthenticated: !!user,
    isLoading,
    login,
    logout,
    updateUser,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
