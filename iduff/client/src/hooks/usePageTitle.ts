import { useEffect } from "react";
import { useLocation } from "react-router-dom";

interface PageTitleMap {
  [key: string]: string;
}

const pageTitles: PageTitleMap = {
  "/dashboard": "Dashboard",
  "/usuarios": "Usuários",
  "/disciplinas": "Disciplinas",
  "/inscricoes": "Inscrições",
  "/cursos": "Cursos",
  "/documentos": "Documentos",
  "/perfil": "Perfil",
  "/login": "Login",
};

export const usePageTitle = () => {
  const location = useLocation();

  useEffect(() => {
    const currentPath = location.pathname;
    const pageTitle = pageTitles[currentPath] || "IdUFF";
    const fullTitle =
      pageTitle === "IdUFF"
        ? "IdUFF - Sistema de Inscrições"
        : `${pageTitle} - IdUFF`;

    document.title = fullTitle;
  }, [location.pathname]);

  const getCurrentPageTitle = () => {
    const currentPath = location.pathname;
    return pageTitles[currentPath] || "IdUFF";
  };

  return { getCurrentPageTitle };
};
