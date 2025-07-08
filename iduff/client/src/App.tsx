import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import { useAuth } from "./context/useAuth";
import Layout from "./components/layout/Layout";
import Login from "./components/auth/Login";
import Dashboard from "./components/dashboard/Dashboard";
import UsuariosList from "./components/usuarios/UsuariosList";
import DisciplinasList from "./components/disciplinas/DisciplinasList";
import InscricoesList from "./components/inscricoes/InscricoesList";
import CursosList from "./components/cursos/CursosList";
import DocumentosList from "./components/documentos/DocumentosList";
import Profile from "./components/profile/Profile";

// Protected Route Component
const ProtectedRoute: React.FC<{ children: React.ReactNode }> = ({
  children,
}) => {
  const { isAuthenticated } = useAuth();

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  return <>{children}</>;
};

// Public Route Component (redirect to dashboard if authenticated)
const PublicRoute: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const { isAuthenticated } = useAuth();

  if (isAuthenticated) {
    return <Navigate to="/dashboard" replace />;
  }

  return <>{children}</>;
};

// App Routes Component (needs to be inside AuthProvider)
const AppRoutes: React.FC = () => {
  return (
    <Router>
      <Routes>
        {/* Public Routes */}
        <Route
          path="/login"
          element={
            <PublicRoute>
              <Login />
            </PublicRoute>
          }
        />

        {/* Protected Routes */}
        <Route
          path="/"
          element={
            <ProtectedRoute>
              <Layout />
            </ProtectedRoute>
          }
        >
          <Route index element={<Navigate to="/dashboard" replace />} />
          <Route path="dashboard" element={<Dashboard />} />
          <Route path="usuarios" element={<UsuariosList />} />
          <Route path="disciplinas" element={<DisciplinasList />} />
          <Route path="inscricoes" element={<InscricoesList />} />
          <Route path="cursos" element={<CursosList />} />
          <Route path="documentos" element={<DocumentosList />} />
          <Route path="perfil" element={<Profile />} />
        </Route>

        {/* Catch all */}
        <Route path="*" element={<Navigate to="/dashboard" replace />} />
      </Routes>
    </Router>
  );
};

const App: React.FC = () => {
  return (
    <AuthProvider>
      <AppRoutes />
    </AuthProvider>
  );
};

export default App;
