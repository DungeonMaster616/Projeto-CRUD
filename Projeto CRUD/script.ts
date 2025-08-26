import { Component, OnInit } from '@angular/core';
import { Aluno } from 'src/app/models/aluno';
import { AlunoService } from 'src/app/services/aluno.service';

@Component({
  selector: 'app-aluno',
  templateUrl: './aluno.component.html',
  styleUrls: ['./aluno.component.css']
})
export class AlunoComponent implements OnInit {

  alunos: Aluno[] = [];
  alunoSelecionado: Aluno = { nome: '', email: '', matricula: '' };
  isEditando = false;

  constructor(private alunoService: AlunoService) {}

  ngOnInit(): void {
    this.carregarAlunos();
  }

  carregarAlunos(): void {
    this.alunoService.listar().subscribe(data => {
      this.alunos = data;
    });
  }

  salvarAluno(): void {
    this.alunoService.salvar(this.alunoSelecionado).subscribe(() => {
      this.carregarAlunos();
      this.limparFormulario();
    });
  }

  selecionarParaEditar(aluno: Aluno): void {
    // Cria uma cópia do objeto para evitar alteração direta na lista
    this.alunoSelecionado = { ...aluno };
    this.isEditando = true;
  }

  deletarAluno(id: number): void {
    if (confirm('Tem certeza que deseja excluir este aluno?')) {
      this.alunoService.deletar(id).subscribe(() => {
        this.carregarAlunos();
      });
    }
  }

  limparFormulario(): void {
    this.alunoSelecionado = { nome: '', email: '', matricula: '' };
    this.isEditando = false;
  }
}