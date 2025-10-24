package com.professorangoti.torresmo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProdutoController {
  
  @GetMapping("/produtos")
  public String exibeProdutos() {
      return "produtos";
  }
  
}
