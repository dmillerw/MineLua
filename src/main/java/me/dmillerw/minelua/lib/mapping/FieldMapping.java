package me.dmillerw.minelua.lib.mapping;

/**
 * @author dmillerw
 */
public class FieldMapping {

    public final String owner;
    public final String name;

    public FieldMapping(String owner, String name) {
        this.owner = owner;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{owner: " + owner + ", name: " + name + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldMapping that = (FieldMapping) o;

        if (!name.equals(that.name)) return false;
        if (!owner.equals(that.owner)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = owner.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
