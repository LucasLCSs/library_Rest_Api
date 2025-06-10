package Java.Project.libraryApi.controller;

import Java.Project.libraryApi.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import Java.Project.libraryApi.service.LivroService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> listarLivros () {
       return livroService.listarTodosLivros();
    }

    @GetMapping("/buscar")
    public Optional<Livro> listarPorNome (@RequestParam String name) {
       return livroService.listarPorNome(name);
    }

    @PostMapping
    public Livro salvarLivro (@RequestBody Livro livro) {
        return livroService.salvarLivro(livro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarLivro(@PathVariable UUID id) {
        livroService.deletarLivro(id);
    }

    @PutMapping
    public Livro atualizarLivro(@RequestBody Livro livro) {
        return livroService.atualizarLivro(livro);
    }
}
