package mvc.model;

import java.util.Calendar;


public class Tarefa {
	 private Long id;
	 private String descricao;
	 private boolean finalizado;
	 private Calendar dataFinalizacao;
	 public Long getId() {return id;}
	 public void setId(Long id) {this.id = id;}
	 public String getDescricao() {return descricao;}
	 public void setDescricao(String descricao) {this.descricao = descricao;}
	 public boolean isFinalizado() {return finalizado;}
	 public void setFinalizado(boolean finalizado) {this.finalizado = finalizado;}
	 public Calendar getDataFinalizacao() {return dataFinalizacao;}
	 public void setDataFinalizacao(Calendar dataFinal) {this.dataFinalizacao =
	dataFinal;}
}
