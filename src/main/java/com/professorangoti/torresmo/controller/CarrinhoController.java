package com.professorangoti.torresmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.professorangoti.torresmo.model.Produto;
import com.professorangoti.torresmo.service.CarrinhoService;
import com.professorangoti.torresmo.service.ProdutoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

  @Autowired
  private CarrinhoService carrinhoService;

  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public String carrinho(Model model) {
    model.addAttribute("itens", carrinhoService.getItens());
    model.addAttribute("totalItens", carrinhoService.getTotalItens());
    model.addAttribute("valorTotal", carrinhoService.getValorTotal());
    return "carrinho";
  }

  @PostMapping("/adicionar/{id}")
  public String adicionarItem(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    Produto produto = produtoService.findById(id).get();
    carrinhoService.adicionarItem(produto);
    redirectAttributes.addFlashAttribute("mensagem", produto.getNome() + " adicionado ao carrinho!");
    return "redirect:/produtos";
  }

  @PostMapping("/remover/{id}")
  public String removerItem(@PathVariable("id") Long id) {
    carrinhoService.removerItem(id);
    return "redirect:/carrinho";
  }

  @PostMapping("/atualizar/{id}")
  public String atualizarQuantidade(@PathVariable("id") Long id, @RequestParam Integer quantidade) {
    System.out.println("Atualizando quantidade do produto ID " + id + " para " + quantidade);
    carrinhoService.atualizarQuantidade(id, quantidade);
    return "redirect:/carrinho";
  }

  @PostMapping("/limpar")
  public String limpar() {
    carrinhoService.limpar();
    return "redirect:/carrinho";
  }
}
