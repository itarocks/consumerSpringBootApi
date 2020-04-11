package br.com.alura.forum.controller;


import br.com.alura.forum.br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.br.com.alura.forum.repository.TopicoRepository;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.Topicoform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/topicos")
public class TopicosController {

    List<Topico> topicos;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {

        if (nomeCurso == null) {

            topicos = topicoRepository.findAll();

        } else {

            topicos = topicoRepository.findByCursoNome(nomeCurso);

        }
        return TopicoDto.converter(topicos);
    }

   @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody Topicoform topicoform, UriComponentsBuilder uriBuilder){
        Topico topico = topicoform.converteToTopico(cursoRepository);
        topicoRepository.save(topico);
        URI uri =  uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }
}