package me.dmillerw.minelua.lib.mapping;

/**
 * @author dmillerw
 */
public class MethodMapping {

    public final String owner;
    public final String name;
    public final String desc;

    public MethodMapping(String owner, String name, String desc) {
        this.owner = owner;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "{owner: " + owner + ", name: " + name + ", desc: " + desc + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodMapping that = (MethodMapping) o;

        if (!desc.equals(that.desc)) return false;
        if (!name.equals(that.name)) return false;
        if (!owner.equals(that.owner)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = owner.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + desc.hashCode();
        return result;
    }
}
