package br.com.alura.forum.br.com.alura.forum.repositorry;

import br.com.alura.forum.br.com.alura.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico,Long> {

}
