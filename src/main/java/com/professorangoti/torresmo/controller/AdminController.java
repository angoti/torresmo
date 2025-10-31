package com.professorangoti.torresmo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.professorangoti.torresmo.model.Produto;
import com.professorangoti.torresmo.service.ProdutoService;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public String admin(Model model) {
    List<Produto> produtos = produtoService.findAll();
    model.addAttribute("produtos", produtos);
    return "admin";
  }

  @PostMapping("/produtos")
  public String salvarProduto(
      @RequestParam(required = false) Long idProduto,
      @RequestParam String nome,
      @RequestParam(required = false) String descricao,
      @RequestParam Double preco,
      @RequestParam(required = false) String tamanho,
      @RequestParam(required = false) Boolean disponivel) {

    Produto produto = new Produto();
    if (idProduto != null) {
      // Atualização: buscar o produto existente
      produto = produtoService.findById(idProduto).orElse(new Produto());
    }
    produto.setNome(nome);
    produto.setDescricao(descricao);
    produto.setPreco(preco);
    produto.setTamanho(tamanho);
    produto.setDisponivel(disponivel != null ? disponivel : true);

    produtoService.save(produto);
    return "redirect:/admin";
  }

  @PostMapping("/produtos/{id}/delete")
  public String deletarProduto(@PathVariable("id") Long id) {
    produtoService.deleteById(id);
    return "redirect:/admin";
  }
}
