package Java.Project.libraryApi.repository;

import Java.Project.libraryApi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    List<Autor> findByAutor(String name);
    List<Autor> findByNacionalidade(String nacionalidade);
    List<Autor> findByAutorAndNacionalidade(String nome, String nacionalidade);

    Optional<Autor> findByAutorAndDataNascimentoAndNacionalidade(String nome, LocalDate dataNascimento, String nacionalidade);
}
