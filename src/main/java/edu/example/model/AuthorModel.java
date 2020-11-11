package edu.example.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="t_author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AuthorModel {

    public static String TYPE_NAME = "Автор";

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_author_id_seq")
    @SequenceGenerator(name = "t_author_id_seq", sequenceName = "t_author_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="birthday")
    private Date birthday;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



}
