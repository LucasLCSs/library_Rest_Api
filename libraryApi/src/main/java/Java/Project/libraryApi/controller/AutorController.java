package Java.Project.libraryApi.controller;

import Java.Project.libraryApi.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import Java.Project.libraryApi.service.AutorService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listarTodosAutores() {
        return autorService.listarTodosAutores();
    }

    @GetMapping("/nome")
    public Optional<Autor> listarPorNome(@RequestParam String name) {
        return autorService.listarPorNome(name);
    }

    @PostMapping
    public Autor salvarAutor(@RequestBody Autor autor) {
        return autorService.salvarAutor(autor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAutor(@PathVariable UUID id) {
        autorService.deletarAutor(id);
    }

    @PutMapping
    public Autor atualizarAutor(@RequestBody Autor autor) {
        return autorService.atualizarAutor(autor);
    }
}
