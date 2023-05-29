import streams.Dog;
import streams.Grouper;
import streams.NamedObject;
import streams.Rabbit;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        streams();
    }

    public static void streams() {
        List<NamedObject> namedObjects = new ArrayList<>();
        namedObjects.add(new Dog());
        namedObjects.add(new Dog());
        namedObjects.add(new Rabbit());
        namedObjects.add(new Rabbit());
        namedObjects.add(new Rabbit());

        var grouper = new Grouper();
        var resultMap = grouper.groupByName(namedObjects);
        System.out.println(resultMap.get(Dog.class.getName()).size());
        System.out.println(resultMap.get(Rabbit.class.getName()).size());
    }
}
