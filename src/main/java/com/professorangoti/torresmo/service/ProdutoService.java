package com.professorangoti.torresmo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.professorangoti.torresmo.model.Produto;
import com.professorangoti.torresmo.repository.ProdutoRepository;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<Produto> findDestaques() {

        // Versão 1: Filtra os produtos em destaque usando Stream API. Este tipo de implementação deve ser evitada em aplicações reais por questões de desempenho.
        // List<Produto> produtos = findAll();
        // return produtos.stream().filter(Produto::getDestaque).toList();

        // Versão 2: JPA Query Methods: Query Creation
        // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.query-creation
        return produtoRepository.findByDestaqueTrueAndDisponivelTrue();
    }

    public List<Produto> produtosDisponoveis() {
        return produtoRepository.findByDisponivelTrue();
    }
}
