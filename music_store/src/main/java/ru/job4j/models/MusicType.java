package ru.job4j.models;

public class MusicType extends EntityCommon {
    private final String type;

    public MusicType(int id, String type) {
        super(id);
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.getType();
    }
}
