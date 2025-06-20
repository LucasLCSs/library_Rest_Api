package Java.Project.libraryApi.controller;

import Java.Project.libraryApi.controller.dto.AutorDTO;
import Java.Project.libraryApi.controller.dto.ErroResposta;
import Java.Project.libraryApi.exception.OperacaoNaoPermitida;
import Java.Project.libraryApi.exception.RegistroDuplicado;
import Java.Project.libraryApi.model.Autor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Java.Project.libraryApi.service.AutorService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable UUID id) {
        Optional<Autor> autorOptional = autorService.obterPorId(id);

        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(autor.getId()
                                        , autor.getAutor()
                                        , autor.getDataNascimento()
                                        , autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome")
    public List<Autor> listarPorNome(@RequestParam String name) {
        return autorService.listarPorNome(name);
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(@RequestParam(required = false) String nome, @RequestParam(required = false
    ) String nacionalidade) {
        List<Autor> autorList = autorService.pesquisa(nome, nacionalidade);

        List<AutorDTO> listaDTO = autorList.stream()
                .map(autor -> new AutorDTO(
                autor.getId(),
                autor.getAutor(),
                autor.getDataNascimento(),
                autor.getNacionalidade()
        )).toList();

        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping
    public ResponseEntity<?> salvarAutor(@RequestBody AutorDTO autorDTO) {
        try {
            Autor autor = autorDTO.mapearParaAutor();
            autorService.salvarAutor(autor);

            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autor.getId())
                    .toUri();

            return ResponseEntity.created(uri).build();
        } catch (RegistroDuplicado e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.Status()).body(erroDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarAutor(@PathVariable String id) {

        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.deletarAutor(idAutor);

            if (autorOptional.isPresent()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID inválido");
        } catch (OperacaoNaoPermitida e) {
            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.Status()).body(erroResposta);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAutor(@PathVariable String id, @RequestBody AutorDTO autorDTO) {
        try{
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

            if (autorOptional.isPresent()) {
                Autor autor = autorOptional.get();
                autor.setAutor(autorDTO.nome());
                autor.setDataNascimento(autorDTO.dataNascimento());
                autor.setNacionalidade(autorDTO.nacionalidade());

                autorService.atualizarAutor(autor);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RegistroDuplicado e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.Status()).body(erroDTO);
        }
    }
}
