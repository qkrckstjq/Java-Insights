package objectOrientedProgramming.equalsAndHashCode;

import java.util.Objects;

public class Person extends Object {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Person p && Objects.equals(p.name, this.name));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}
