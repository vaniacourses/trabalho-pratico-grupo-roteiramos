import { useState } from "react";
import { useAuth } from "../../context/useAuth";
import { usePageTitle } from "../../hooks/usePageTitle";
import { BookOpen, Eye, EyeOff, Loader2 } from "lucide-react";

export default function Login() {
  // Update document title for login page
  usePageTitle();

  const [credentials, setCredentials] = useState({
    login: "",
    senha: "",
  });
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const { login } = useAuth();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError("");
    setIsSubmitting(true);

    try {
      const success = await login(credentials);
      if (!success) {
        setError(
          "Credenciais inválidas. Tente: joao.silva / maria.santos / carlos.coord com senha: 123456"
        );
      }
    } catch {
      setError("Erro ao fazer login. Tente novamente.");
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-50 py-12 px-4 sm:px-6 lg:px-8">
      <div className="max-w-md w-full space-y-8">
        <div className="card p-8">
          <div className="text-center mb-8">
            <div className="flex items-center justify-center gap-2 mb-4">
              <BookOpen className="text-primary-600 w-8 h-8" />
              <h1 className="text-2xl font-bold text-gray-900">IdUFF</h1>
            </div>
            <p className="text-sm text-gray-600">
              Sistema de Inscrição em Disciplinas da UFF
            </p>
          </div>

          <form onSubmit={handleSubmit} className="space-y-6">
            {error && (
              <div className="bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded-md text-sm">
                {error}
              </div>
            )}

            <div>
              <label
                htmlFor="login"
                className="block text-sm font-medium text-gray-700 mb-2"
              >
                Login
              </label>
              <input
                type="text"
                id="login"
                className="input"
                value={credentials.login}
                onChange={(e) =>
                  setCredentials({ ...credentials, login: e.target.value })
                }
                required
                placeholder="Digite seu login"
              />
            </div>

            <div>
              <label
                htmlFor="senha"
                className="block text-sm font-medium text-gray-700 mb-2"
              >
                Senha
              </label>
              <div className="relative">
                <input
                  type={showPassword ? "text" : "password"}
                  id="senha"
                  className="input pr-12"
                  value={credentials.senha}
                  onChange={(e) =>
                    setCredentials({ ...credentials, senha: e.target.value })
                  }
                  required
                  placeholder="Digite sua senha"
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute inset-y-0 right-0 pr-3 flex items-center text-gray-400 hover:text-gray-600"
                >
                  {showPassword ? (
                    <EyeOff className="w-5 h-5" />
                  ) : (
                    <Eye className="w-5 h-5" />
                  )}
                </button>
              </div>
            </div>

            <button
              type="submit"
              className="btn btn-primary w-full"
              disabled={isSubmitting}
            >
              {isSubmitting ? (
                <div className="flex items-center justify-center gap-2">
                  <Loader2 className="w-4 h-4 animate-spin" />
                  Entrando...
                </div>
              ) : (
                "Entrar"
              )}
            </button>
          </form>

          <div className="mt-6 text-center">
            <p className="text-sm text-gray-600">
              <strong>Usuários de teste:</strong>
              <br />
              <span className="block mt-2 space-y-1">
                <span className="block">Aluno: joao.silva / Senha: 123456</span>
                <span className="block">
                  Professor: maria.santos / Senha: 123456
                </span>
                <span className="block">
                  Coordenador: carlos.coord / Senha: 123456
                </span>
              </span>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
