package Java.Project.libraryApi.repository;

import Java.Project.libraryApi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    public Optional<Autor> findByAutor(String name);
}
