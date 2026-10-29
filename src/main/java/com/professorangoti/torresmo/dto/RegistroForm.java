package com.professorangoti.torresmo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistroForm {
  @NotBlank(message = "Informe seu nome")
  @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
  private String nome;

  @NotBlank(message = "Informe seu e-mail")
  @Email(message = "Informe um e-mail válido")
  private String email;

  @NotNull(message = "Informe uma senha")
  @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
  private String senha;

  private String confirmacaoSenha;
}
