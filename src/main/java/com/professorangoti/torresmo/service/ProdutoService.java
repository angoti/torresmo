package com.professorangoti.torresmo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.professorangoti.torresmo.repository.ProdutoRepository;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<com.professorangoti.torresmo.model.Produto> findAll() {
        return produtoRepository.findAll();
    }

}
