package ru.edu.kolyanpie.server.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @OneToMany(mappedBy = "blue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Game> blueGames;
    @OneToMany(mappedBy = "red", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Game> redGames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Game> getBlueGames() {
        return blueGames;
    }

    public void setBlueGames(Set<Game> blueGames) {
        this.blueGames = blueGames;
    }

    public Set<Game> getRedGames() {
        return redGames;
    }

    public void setRedGames(Set<Game> redGames) {
        this.redGames = redGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
