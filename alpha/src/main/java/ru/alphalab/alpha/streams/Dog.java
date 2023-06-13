package ru.alphalab.alpha.streams;

public class Dog implements NamedObject {

    public String getName() {
        return Dog.class.getSimpleName();
    }
}
