import React, { useState } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "./Navbar";
import Sidebar from "./Sidebar";
import { usePageTitle } from "../../hooks/usePageTitle";

const Layout: React.FC = () => {
  const [isSidebarOpen, setIsSidebarOpen] = useState(false);

  // This hook will automatically update the document title based on the current route
  usePageTitle();

  const toggleSidebar = () => {
    setIsSidebarOpen(!isSidebarOpen);
  };

  const closeSidebar = () => {
    setIsSidebarOpen(false);
  };

  return (
    <div className="min-h-screen flex flex-col bg-gray-50">
      <Navbar onToggleSidebar={toggleSidebar} isSidebarOpen={isSidebarOpen} />
      <div className="flex flex-1 relative">
        <Sidebar isOpen={isSidebarOpen} onClose={closeSidebar} />
        {/* Main content area */}
        <main className="flex-1 min-w-0 flex flex-col lg:ml-0">
          <div className="flex-1 p-4 md:p-6 lg:p-8 overflow-y-auto">
            <Outlet />
          </div>
        </main>
      </div>
    </div>
  );
};

export default Layout;
