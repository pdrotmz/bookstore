package dev.pdrotmz.bookstore.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_BOOK")
public class BookModel implements Serializable {

    // Id de controle da JVM para serialização
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true) // (não aceita nulo, titulo único)
    private String title;


    // Criando relação entre book e publisher
    @ManyToOne
    @JoinColumn(name = "publisher_id") // Especifica qual vai ser a chave estrangeira para conexão com TB_PUBLISHER
    private PublisherModel publisher;


    @ManyToMany
    // Definindo tabela auxiliar e mapeando
    @JoinTable(
            // nome da tabela de relação
            name = "tb_book_author",
            // Criando variável para definir as chaves estrangeiras de ambos os lados
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorModel> authors = new HashSet<>();

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PublisherModel getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherModel publisher) {
        this.publisher = publisher;
    }

    public Set<AuthorModel> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorModel> authors) {
        this.authors = authors;
    }
}
