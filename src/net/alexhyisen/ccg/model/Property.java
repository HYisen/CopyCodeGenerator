package net.alexhyisen.ccg.model;

import java.util.Objects;

public class Property {
    private final String type;
    private final String name;

    public Property(String type, String name) {
        this.type = type;
        this.name = upperFirst(name);
    }

    public String genCopyCode(String source, String target) {
        return String.format("%s.set%s(%s.get%s());", source, getName(), target, getName());
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Property) {
            Property that = (Property) obj;
            return Objects.equals(this.getName(), that.getName()) &&
                    Objects.equals(this.getType(), that.getType());
        }
        return false;
    }

    @Override
    public String toString() {
        return getType() + " : " + getName();
    }

    private String upperFirst(String orig) {
        final char[] chars = orig.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return String.valueOf(chars);
    }
}
