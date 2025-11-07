package com.professorangoti.torresmo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.professorangoti.torresmo.model.CarrinhoItem;
import com.professorangoti.torresmo.model.Produto;

@Service
public class CarrinhoService {

  private List<CarrinhoItem> itens = new ArrayList<>();

  public void adicionarItem(Produto produto) {
    Optional<CarrinhoItem> itemExistente = itens.stream()
        .filter(item -> item.getProdutoId().equals(produto.getIdProduto()))
        .findFirst();

    if (itemExistente.isPresent()) {
      // Se o item já existe, aumenta a quantidade
      CarrinhoItem item = itemExistente.get();
      item.setQuantidade(item.getQuantidade() + 1);
    } else {
      // Se não existe, adiciona novo item
      itens.add(new CarrinhoItem(produto));
    }
  }

  public void removerItem(Long produtoId) {
    itens.removeIf(item -> item.getProdutoId().equals(produtoId));
  }

  public void atualizarQuantidade(Long produtoId, Integer quantidade) {
    itens.stream()
        .filter(item -> item.getProdutoId().equals(produtoId))
        .findFirst()
        .ifPresent(item -> {
          if (quantidade <= 0) {
            removerItem(produtoId);
          } else {
            item.setQuantidade(quantidade);
          }
        });
  }

  public void limpar() {
    itens.clear();
  }

  public List<CarrinhoItem> getItens() {
    return itens;
  }

  public Integer getTotalItens() {
    return itens.stream()
        .mapToInt(CarrinhoItem::getQuantidade)
        .sum();
  }

  public Double getValorTotal() {
    return itens.stream()
        .mapToDouble(CarrinhoItem::getSubtotal)
        .sum();
  }
}
