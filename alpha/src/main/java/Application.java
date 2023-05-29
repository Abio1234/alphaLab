import multithreading.MultithreadingWriter;
import streams.Dog;
import streams.Grouper;
import streams.NamedObject;
import streams.Rabbit;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Application {

    public static void main(String[] args) {
        streams();
        multithreading();
    }

    public static void streams() {
        System.out.println("Start streams");
        List<NamedObject> namedObjects = new ArrayList<>();
        namedObjects.add(new Dog());
        namedObjects.add(new Dog());
        namedObjects.add(new Rabbit());
        namedObjects.add(new Rabbit());
        namedObjects.add(new Rabbit());

        var grouper = new Grouper();
        var resultMap = grouper.groupByName(namedObjects);
        System.out.println(resultMap.get(Dog.class.getSimpleName()).size());
        System.out.println(resultMap.get(Rabbit.class.getSimpleName()).size());
    }

    public static void multithreading() {
        multithreading(false);
        multithreading(true);
    }

    private static void multithreading(boolean isTwoTread) {
        System.out.println();
        System.out.println("Start multithreading write");

        final String resultFileName = "Result.txt";
        final String tread1FileName = "Thread1.txt";
        final String tread2FileName = "Thread2.txt";
        final int busyWaitingTime = 100;

        MultithreadingWriter writer = new MultithreadingWriter();
        AtomicInteger count = new AtomicInteger(0);
        int maxCount = 100;
        var resultFile = clearFile(resultFileName);
        AtomicLong resultTime = new AtomicLong();

        Runnable writeTask1 = () ->
                resultTime.set(writer.writeSimpleCount(count, resultFile, clearFile(tread1FileName), maxCount));
        var writeThread1 = new Thread(writeTask1);
        writeThread1.start();

        if (isTwoTread) {
            Runnable writeTask2 = () -> writer.writeSimpleCount(count, resultFile, clearFile(tread2FileName), maxCount);
            var writeThread2 = new Thread(writeTask2);
            writeThread2.start();
        }

        try {
            Thread.sleep(busyWaitingTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        readFile(resultFileName);
        readFile(tread1FileName);
        if (isTwoTread) {
            readFile(tread2FileName);
        }

        System.out.println(isTwoTread ? "Двухпоточное время выполнения = " + resultTime
                : "Однопоточное время выполнения = " + resultTime);
    }

    private static File clearFile(String pathname) {
        var file = new File(pathname);
        try(BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8))) {
            bufferedWriter.append("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    private static void readFile(String fileName) {
        Path path = Paths.get(fileName);
        try(BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String resultString;
            while((resultString = bufferedReader.readLine()) != null) {
                System.out.println(resultString);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
