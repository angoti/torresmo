package com.professorangoti.torresmo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarrinhoItem {
  private Long produtoId;
  private String nome;
  private String descricao;
  private Double preco;
  private String tamanho;
  private Integer quantidade;

  public CarrinhoItem(Produto produto) {
    this.produtoId = produto.getIdProduto();
    this.nome = produto.getNome();
    this.descricao = produto.getDescricao();
    this.preco = produto.getPreco();
    this.tamanho = produto.getTamanho();
    this.quantidade = 1;
  }

  public Double getSubtotal() {
    return preco * quantidade;
  }
}
