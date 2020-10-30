package edu.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_book")
@NoArgsConstructor
@Getter
@Setter
public class BookModel {

    public static String TYPE_NAME = "Книга";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_book_id_seq")
    @SequenceGenerator(name = "t_book_id_seq", sequenceName = "t_book_id_seq", allocationSize = 1)
    private long id;

    public BookModel(long id, String name, Integer pagesCount, Integer publishYear, AuthorModel authorId) {
        this.id = id;
        this.name = name;
        this.pagesCount = pagesCount;
        this.publishYear = publishYear;
        this.authorId = authorId;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "pages_count")
    private Integer pagesCount;

    @Column(name = "publish_year")
    private Integer publishYear;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private AuthorModel authorId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public AuthorModel getAuthorId() {
        return authorId;
    }

    public void setAuthorId(AuthorModel authorId) {
        this.authorId = authorId;
    }
}
