package entities;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;

public class Password {
    private String password;
    private String hash;

    public Password(String password){
        this.password=password;
        this.hash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
        hash=BCrypt.hashpw(password,BCrypt.gensalt());
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password) &&
                Objects.equals(hash, password1.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, hash);
    }

    @Override
    public String toString() {
        return "Password{" +
                "hash='" + hash + '\'' +
                '}';
    }
}
