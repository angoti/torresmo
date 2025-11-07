package com.professorangoti.torresmo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.professorangoti.torresmo.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

  // JPA Query Methods: Query Creation
  // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.query-creation
  List<Produto> findByDestaqueTrueAndDisponivelTrue();
  List<Produto> findByDisponivelTrue();
}
