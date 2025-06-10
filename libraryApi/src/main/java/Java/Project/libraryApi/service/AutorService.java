package Java.Project.libraryApi.service;

import Java.Project.libraryApi.model.Autor;
import org.springframework.stereotype.Service;
import Java.Project.libraryApi.repository.AutorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarTodosAutores () {
        return autorRepository.findAll();
    }

    public Optional<Autor> listarPorNome (String name) {
        return autorRepository.findByAutor(name);
    }

    public Autor salvarAutor (Autor autor) {
        return autorRepository.save(autor);
    }

    public void deletarAutor (UUID id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
        } else System.out.println("Autor com ID " + id + " inexistente");
    }

    public Autor atualizarAutor(Autor autor) {
        if (autorRepository.existsById(autor.getId())) {
            autorRepository.save(autor);
            System.out.println("Atualizado com sucesso!");
        } else System.out.println("Autor com ID " + autor.getId() + " inexistente");
        return null;
    }
}
