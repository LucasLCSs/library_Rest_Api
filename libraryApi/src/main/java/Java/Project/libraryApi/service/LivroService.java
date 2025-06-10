package Java.Project.libraryApi.service;

import Java.Project.libraryApi.model.Livro;
import org.springframework.stereotype.Service;
import Java.Project.libraryApi.repository.LivroRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodosLivros () {
        return livroRepository.findAll();
    }

    public Optional<Livro> listarPorNome (String name) {
        return livroRepository.findByLivro(name);
    }

    public Livro salvarLivro (Livro livro) {
        return livroRepository.save(livro);
    }

    public void deletarLivro (UUID id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
        } else System.out.println("Livro com ID " + id + " inexistente");
    }

    public Livro atualizarLivro(Livro livro) {
        if (livroRepository.existsById(livro.getId())) {
            System.out.println("Atualizado com sucesso!");
            return livroRepository.save(livro);
        } else {
            System.out.println("Livro com ID" + livro.getId() + " inexistente");
            return null;
        }
    }
}
