package app.prod;

import java.util.Objects;

/**
 * Represents the abstract base class for all entities within the application.
 * Provides common properties and functionality for entity identification and naming.
 */
public abstract class Entity {
    private Long id;
    private String name;

    /**
     * Constructs a new Entity with the specified identifier and name.
     *
     * @param id   The unique identifier of the entity.
     * @param name The name of the entity.
     */
    public Entity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(getId(), entity.getId()) && Objects.equals(getName(), entity.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
