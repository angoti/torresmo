package com.professorangoti.torresmo.controller;

import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.professorangoti.torresmo.dto.RegistroForm;
import com.professorangoti.torresmo.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/registro")
public class RegistroController {

  private final UsuarioService usuarioService;

  public RegistroController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public String formulario(Model model) {
    model.addAttribute("registroForm", new RegistroForm());
    return "registro";
  }

  @PostMapping
  public String registrar(@Valid @ModelAttribute RegistroForm registroForm, BindingResult result) {
    if (!Objects.equals(registroForm.getSenha(), registroForm.getConfirmacaoSenha())) {
      result.rejectValue("confirmacaoSenha", "senha.diferente", "As senhas não conferem");
    }
    if (!result.hasFieldErrors("email") && usuarioService.emailJaCadastrado(registroForm.getEmail())) {
      result.rejectValue("email", "email.duplicado", "Este e-mail já está cadastrado");
    }
    if (result.hasErrors()) {
      return "registro";
    }

    usuarioService.registrarCliente(registroForm);
    return "redirect:/login?registrado";
  }
}
