# IdUFF Frontend

## Overview

This is the frontend application for the IdUFF (Sistema de Identificação da UFF) system, built with React, TypeScript, and Vite.

## Features Implemented

### Authentication

- **Login Component** (`src/components/auth/Login.tsx`)
  - Mock authentication with predefined users
  - Role-based access control
  - Clean, responsive design

### Layout & Navigation

- **Layout System** (`src/components/layout/`)
  - `Layout.tsx` - Main application shell with Outlet for routing
  - `Navbar.tsx` - Top navigation bar with user menu
  - `Sidebar.tsx` - Role-based navigation sidebar

### Dashboard

- **Dashboard** (`src/components/dashboard/Dashboard.tsx`)
  - Role-specific overview and statistics
  - Quick action buttons
  - Recent activity feed

### User Management

- **UsuariosList** (`src/components/usuarios/UsuariosList.tsx`)
  - User listing with search and filters
  - Role-based actions (add, edit, delete)
  - Status management

### Academic Management

- **DisciplinasList** (`src/components/disciplinas/DisciplinasList.tsx`)

  - Discipline listing and management
  - Professor assignment
  - Enrollment tracking

- **CursosList** (`src/components/cursos/CursosList.tsx`)
  - Course management interface
  - Coordinator assignment
  - Discipline association

### Enrollment Management

- **InscricoesList** (`src/components/inscricoes/InscricoesList.tsx`)
  - Student enrollment/solicitation management
  - Tabbed interface for different views
  - Status-based filtering and actions

### Document Management

- **DocumentosList** (`src/components/documentos/DocumentosList.tsx`)
  - Document viewing and download
  - Type-based filtering
  - Role-based access (students see only their documents)

### User Profile

- **Profile** (`src/components/profile/Profile.tsx`)
  - User profile viewing and editing
  - Role-specific information display
  - Profile update functionality

## Architecture

### State Management

- **AuthContext** (`src/context/AuthContext.tsx`) - Authentication state provider
- **useAuth** (`src/context/useAuth.ts`) - Authentication hook

### Type System

- **Types** (`src/types/index.ts`) - Complete type definitions matching backend DTOs
  - All enums (TipoUsuario, StatusSolicitacao, etc.)
  - Entity interfaces (Usuario, Disciplina, Curso, etc.)
  - DTO interfaces for forms and API communication

### Routing

- React Router v6 with nested routes
- Protected routes requiring authentication
- Role-based route access control

### Styling

- Custom CSS design system (`src/index.css`, `src/App.css`)
- Utility classes for consistent spacing and colors
- Responsive design with mobile-first approach
- CSS custom properties for theming

## Mock Data

All components currently use mock data for development and testing:

- Mock users with different roles (Aluno, Professor, Coordenador, Administrador)
- Mock disciplines, courses, enrollments, and documents
- Realistic data structure matching backend entities

## User Roles & Permissions

### Aluno (Student)

- View personal dashboard
- Manage enrollments/solicitations
- View and download personal documents
- View available disciplines and courses
- Edit personal profile

### Professor

- View assigned disciplines
- Manage course content
- View student enrollments
- Access reporting features

### Coordenador (Coordinator)

- All Professor permissions
- Manage users within their course
- Approve/reject enrollment solicitations
- Course and discipline management

### Administrador (Administrator)

- Full system access
- User management across all courses
- System configuration
- Complete reporting access

## Development

### Available Scripts

- `npm run dev` - Start development server
- `npm run build` - Build for production
- `npm run preview` - Preview production build
- `npm run lint` - Run ESLint

### Dependencies

- **React 18** - UI framework
- **TypeScript** - Type safety
- **React Router v6** - Client-side routing
- **Lucide React** - Icon library
- **Vite** - Build tool and dev server

## File Structure

```
src/
├── components/           # React components
│   ├── auth/            # Authentication components
│   ├── dashboard/       # Dashboard components
│   ├── disciplinas/     # Discipline management
│   ├── cursos/         # Course management
│   ├── inscricoes/     # Enrollment management
│   ├── usuarios/       # User management
│   ├── documentos/     # Document management
│   ├── profile/        # Profile management
│   └── layout/         # Layout components
├── context/            # React context providers
├── types/              # TypeScript type definitions
├── App.tsx             # Main application component
├── main.tsx           # Application entry point
├── index.css          # Global styles and design system
└── App.css            # Component-specific styles
```

## Future Enhancements

- [ ] Connect to backend API endpoints
- [ ] Implement real authentication
- [ ] Add form validation
- [ ] Implement data caching
- [ ] Add loading states and error handling
- [ ] Add unit and integration tests
- [ ] Implement real-time updates
- [ ] Add accessibility improvements
- [ ] Performance optimizations

## Notes

This frontend is currently fully self-contained with mock data and ready for backend integration. All components follow the design system and maintain consistency with the planned backend API structure defined in the PlantUML diagram.
