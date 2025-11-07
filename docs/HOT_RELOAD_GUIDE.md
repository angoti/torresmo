# Guia de Hot Reload - Atualização Automática no Navegador

## 🔥 Solução SEM Extensões do Navegador

O projeto está configurado para atualizar automaticamente o navegador quando você modificar arquivos HTML/Thymeleaf **SEM PRECISAR DE EXTENSÕES**.

## ✅ O que já está configurado

1. **spring-boot-devtools** (no pom.xml)
2. **Thymeleaf cache desabilitado** (spring.thymeleaf.cache=false)
3. **LiveReload habilitado** (spring.devtools.livereload.enabled=true)
4. **Script LiveReload integrado** (no layout.html - conecta automaticamente)
5. **Restart automático** (spring.devtools.restart.enabled=true)

## 🚀 Como usar

### ✨ Atualização Automática (Funciona em qualquer navegador!)

#### Passo 1: Iniciar a aplicação

```cmd
cd e:\torresmo
.\mvnw.cmd spring-boot:run
```

#### Passo 2: Abrir o navegador e testar

1. Acesse: http://localhost:8080
2. O script LiveReload conecta automaticamente (porta 35729)
3. Edite qualquer arquivo HTML em `src/main/resources/templates/`
4. Salve o arquivo (Ctrl+S)
5. **O navegador atualiza automaticamente!** ✨

**Como funciona:** O `layout.html` inclui um script que conecta ao servidor LiveReload do Spring Boot DevTools (porta 35729). Quando você salva um arquivo, o servidor notifica o navegador para recarregar.

### 🔍 Verificar se está funcionando

Abra o Console do navegador (F12) e você verá:

```
LiveReload is connected
```

Quando salvar um arquivo HTML:

```
LiveReload detected change, reloading...
```

## 📝 Arquivos que serão recarregados automaticamente

- ✅ Templates Thymeleaf (\*.html em `templates/`)
- ✅ Arquivos estáticos (CSS, JS em `static/`)
- ✅ Fragments Thymeleaf (`templates/fragments/`)
- ✅ application.properties (reinicia a aplicação)

## 🔧 Comandos Úteis:

### Executar em modo desenvolvimento:

```cmd
.\mvnw.cmd spring-boot:run
```

### Executar com debug:

```cmd
.\mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

## 💡 Dicas:

1. **Salve o arquivo**: O hot reload só funciona depois de salvar (Ctrl+S)
2. **Aguarde 1-2 segundos**: O DevTools precisa de um momento para detectar mudanças
3. **Console do IDE**: Você verá mensagens como "Reloading Spring Boot Application" no console
4. **Mudanças em Java**: Alterações em classes Java reiniciam a aplicação automaticamente

## 🐛 Troubleshooting

### O navegador não atualiza automaticamente?

1. Verifique o console do navegador (F12) para ver se há mensagens do LiveReload
2. Confirme que a aplicação está rodando com `.\mvnw.cmd spring-boot:run`
3. Verifique se salvou o arquivo (Ctrl+S)
4. Aguarde 1-2 segundos após salvar
5. Verifique se o servidor LiveReload está na porta 35729

### A aplicação não detecta mudanças?

1. Execute via Maven wrapper: `.\mvnw.cmd spring-boot:run`
2. Não execute o JAR diretamente (DevTools não funciona em JAR empacotado)
3. Verifique se `spring-boot-devtools` está no pom.xml
4. Confirme que `spring.thymeleaf.cache=false` está no application.properties

### O console mostra erro "Failed to connect to LiveReload"?

- Isso é normal se você não estiver visualizando a página ainda
- O script tenta conectar assim que a página carrega
- Verifique se a aplicação Spring Boot está rodando

### Mudanças em arquivos Java não funcionam?

- Alterações em código Java reiniciam a aplicação completa (demora mais)
- Use um IDE com suporte a hot swap para mudanças mais rápidas em Java

## 📚 Referências

- [Spring Boot DevTools Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)
- [Thymeleaf with Spring Boot](https://www.thymeleaf.org/doc/tutorials/3.1/thymeleafspring.html)
- [LiveReload Protocol](http://livereload.com/api/protocol/)
