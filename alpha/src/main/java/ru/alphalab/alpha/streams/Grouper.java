package ru.alphalab.alpha.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grouper {

    public Map<String, List<NamedObject>> groupByName(List<NamedObject> objects) {
        return objects.stream().collect(Collectors.groupingBy(NamedObject::getName));
    }
}
