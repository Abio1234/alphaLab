package multithreading;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MultithreadingWriter {

    public long writeSimpleCount(AtomicInteger count, File resultFile, File personalFile, int maxCount) {
        var startTime = LocalDateTime.now();
        boolean exit = false;
        while (!exit) {
            var nextCount = getNextSimpleCount(count);
            if (nextCount > maxCount) {
                exit = true;
            } else {
                write(resultFile, nextCount);
                write(personalFile, nextCount);
            }
        }
        var endTime = LocalDateTime.now();
        return Duration.between(startTime, endTime).toNanos();
    }

    private int getNextSimpleCount(AtomicInteger previousCount) {
        boolean notFound = true;
        var nextCount = 0;
        while (notFound) {
            nextCount = previousCount.getAndIncrement();
            if (isSimple(nextCount)) {
                notFound = false;
            }
        }
        return nextCount;
    }

    private boolean isSimple(int count) {
        if (count < 2) {
            return false;
        }
        return IntStream.rangeClosed(2, count / 2).noneMatch(e -> count % e == 0);
    }

    private void write(File file, int count) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
            bufferedWriter.append(String.valueOf(count));
            bufferedWriter.append(" ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
