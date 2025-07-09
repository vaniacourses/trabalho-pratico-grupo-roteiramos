# IdUFF - Sistema de Gerenciamento Acadêmico

[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=19483263&assignment_repo_type=AssignmentRepo)

## 📋 Sobre o Projeto

O IdUFF é um sistema de gerenciamento acadêmico desenvolvido como trabalho prático da disciplina de Arquitetura e Design de Software. O sistema permite o gerenciamento de inscrições em disciplinas, controle de usuários (alunos, professores e coordenadores), e integração com o sistema CEDERJ.

## 👥 Integrantes do Grupo

- **Atharv Nuthi**
- **Bruno Marchiori**
- **Felipe Figueiredo**
- **Marcos Vinícius**
- **Mateus Maia**

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas baseada nos princípios de Domain-Driven Design (DDD):

- **Camada de Apresentação**: Controllers REST (Spring Boot)
- **Camada de Aplicação**: Services e interfaces de negócio
- **Camada de Domínio**: Entidades e regras de negócio
- **Camada de Transferência**: DTOs para comunicação entre camadas

## 🚀 Tecnologias Utilizadas

### Backend

- **Java 17**
- **Spring Boot 3.x**
- **Gradle** (gerenciamento de dependências)

### Frontend

- **React 18**
- **TypeScript**
- **Vite** (build tool)
- **Tailwind CSS** (estilização)

## 📁 Estrutura do Projeto

```
trabalho-pratico-grupo-roteiramos/
├── Artefatos/                          # Documentação e diagramas
│   ├── diagrama-casos-de-uso/
│   ├── diagrama-classes-detalhado/
│   ├── diagramas-interacao/
│   ├── diagramas-sequencia-sistema/
│   ├── diagramas-visao-arquitetural/
│   └── modelo-conceitual/
├── iduff/
│   ├── client/                         # Aplicação React
│   └── server/                         # API Spring Boot
└── README.md
```

## 📚 Documentação e Apresentações

### 📄 Documentos

- [**Documento do Trabalho**](https://docs.google.com/document/d/1rJsWoiDZ14ZtSVWRscpIt-rRxqxax1kx6l3lzwLJG2c/edit?usp=sharing)

### 🎯 Apresentações

- [**Primeira Apresentação**](https://www.canva.com/design/DAGn1goEXkQ/tzpK5EKaEjpPlYsb8vXIzQ/edit?utm_content=DAGn1goEXkQ&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
- [**Segunda Apresentação**](https://gamma.app/docs/IdUFF-Arquitetura-e-Design-de-Software-8ejndei6akofdxy)

## 🔧 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Node.js 18 ou superior
- npm ou yarn

### Backend (Spring Boot)

```bash
cd iduff/server
./gradlew bootRun
```

### Frontend (React)

```bash
cd iduff/client
npm install
npm run dev
```

## 📊 Artefatos de Modelagem

O projeto inclui uma completa documentação de modelagem UML:

- **Diagrama de Casos de Uso**: Especificação dos requisitos funcionais
- **Diagramas de Classes**: Modelagem detalhada das camadas de aplicação e domínio
- **Diagramas de Sequência**: Fluxos de interação do sistema
- **Diagramas de Sequência de Sistema (DSS)**: Operações do sistema
- **Modelo Conceitual**: Estrutura conceitual do domínio
- **Diagramas de Visão Arquitetural**: Arquitetura geral e microsserviços

## 🎯 Funcionalidades Principais

- ✅ Autenticação de usuários (Aluno, Professor, Coordenador)
- ✅ Gerenciamento de inscrições em disciplinas
- ✅ Aprovação/reprovação de solicitações
- ✅ Integração com sistema CEDERJ
- ✅ Lançamento de notas
- ✅ Cancelamento de inscrições

## 📝 Status do Projeto

🚧 **Em Desenvolvimento** - Versão atual: Protótipo funcional com storage em memória
