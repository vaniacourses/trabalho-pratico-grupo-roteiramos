import React, { useState } from "react";
import {
  List,
  ListItemButton,
  Button,
  TextField,
  Dialog,
  DialogTitle,
  DialogContent,
} from "@mui/material";

interface DisciplinaTurmaModalProps {
  open: boolean;
  onClose: () => void;
  userId?: string;
  onSolicitacaoCriada?: () => void; // nova prop
}

const DisciplinaTurmaModal: React.FC<DisciplinaTurmaModalProps> = ({
  open,
  onClose,
  userId,
  onSolicitacaoCriada,
}) => {
  const [searchTermModal, setSearchTermModal] = useState("");
  const [disciplinas, setDisciplinas] = useState<any[]>([]);
  const [selectedDisciplina, setSelectedDisciplina] = useState<any | null>(null);
  const [turmas, setTurmas] = useState<any[]>([]);
  const [successMessage, setSuccessMessage] = useState("");

  const handleSearchDisciplinas = async (term: string) => {
    setSearchTermModal(term);
    const res = await fetch(`/api/disciplinas?busca=${term}`);
    const data = await res.json();
    setDisciplinas(data);
  };

  const handleSelectDisciplina = async (disciplina: any) => {
    setSelectedDisciplina(disciplina);
    const res = await fetch(`/api/turmas?disciplinaId=${disciplina.id}`);
    const data = await res.json();
    setTurmas(data);
  };

  const handleSolicitarInscricao = async (turmaId: string) => {
    const res = await fetch("/api/inscricoes/solicitacao", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ alunoId: userId, turmaId }),
    });
    if (res.ok) {
      setSuccessMessage("Inscrição criada com sucesso!");
      if (onSolicitacaoCriada) onSolicitacaoCriada();
      setTimeout(() => {
        setSuccessMessage("");
        onClose();
      }, 2000);
    }
  };

  return (
    <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
      <DialogTitle>Solicitação de inscrição</DialogTitle>
      <DialogContent dividers>
        {!selectedDisciplina ? (
          <>
            <TextField
              label="Buscar disciplina"
              placeholder="Código ou nome"
              fullWidth
              value={searchTermModal}
              onChange={(e) => handleSearchDisciplinas(e.target.value)}
              margin="normal"
            />
            <List>
              {disciplinas.map((d) => (
                <ListItemButton
                  key={d.id}
                  component="li"
                  onClick={() => handleSelectDisciplina(d)}
                >
                  {d.codigo} – {d.nome}
                </ListItemButton>
              ))}
            </List>
          </>
        ) : (
          <>
            <h4>Turmas de {selectedDisciplina.codigo}</h4>
            {successMessage && (
              <div className="text-green-600 font-semibold mb-2">
                {successMessage}
              </div>
            )}
            <List>
              {turmas.map((t) => (
                <ListItemButton
                  key={t.id}
                  component="li"
                  onClick={() => handleSolicitarInscricao(t.id)}
                >
                  Turma {t.nome} – {t.local}
                </ListItemButton>
              ))}
            </List>
            <Button onClick={() => setSelectedDisciplina(null)}>Voltar</Button>
          </>
        )}
        <div className="mt-4 flex justify-end">
          <Button onClick={onClose}>Cancelar</Button>
        </div>
      </DialogContent>
    </Dialog>
  );
};

export default DisciplinaTurmaModal;