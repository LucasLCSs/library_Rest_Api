package Java.Project.libraryApi.repository;

import Java.Project.libraryApi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    public Optional<Livro> findByLivro(String name);
}
