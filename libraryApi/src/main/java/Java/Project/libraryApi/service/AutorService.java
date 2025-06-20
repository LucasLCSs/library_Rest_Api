package Java.Project.libraryApi.service;

import Java.Project.libraryApi.exception.OperacaoNaoPermitida;
import Java.Project.libraryApi.model.Autor;
import Java.Project.libraryApi.repository.LivroRepository;
import Java.Project.libraryApi.validator.AutorValidador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import Java.Project.libraryApi.repository.AutorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Declara as dependencias do contrutor e @AutoWired
public class AutorService {

    private AutorRepository autorRepository;
    private AutorValidador autorValidador;
    private LivroRepository livroRepository;

    public List<Autor> listarPorNome (String name) {
        return autorRepository.findByAutor(name);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade) {

        if (nome != null && nacionalidade != null) {
            return autorRepository.findByAutorAndNacionalidade(nome, nacionalidade);
        } else if (nome != null) {
            return autorRepository.findByAutor(nome);
        } else if (nacionalidade != null) {
            return autorRepository.findByNacionalidade(nacionalidade);
        }

        return autorRepository.findAll();
    }

    public void salvarAutor (Autor autor) {
        autorValidador.validar(autor);
        autorRepository.save(autor);
    }

    public Optional<Autor> deletarAutor (UUID id) {
        Optional<Autor> autorOptional = autorRepository.findById(id);

        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();

            if (possuiLivro(autor)) {
                throw new OperacaoNaoPermitida(
                        "Erro na exclusão: registro está sendo utilizado."
                );
            }
        }

        autorOptional.ifPresent(autor -> autorRepository.deleteById(id));

        return autorOptional;
    }

    public void atualizarAutor(Autor autor) {
        Optional<Autor> autorOptional = autorRepository.findById(autor.getId());

        if (autorOptional.isPresent()) {
            autorValidador.validar(autor);
            autorRepository.save(autor);
        } else {
            throw new IllegalArgumentException(
                    "Autor invalido! é necessário que o autor esteja cadastrado!"
            );
        }

    }

    public boolean possuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return autorRepository.findById(id);
    }
}
