package edu.hw7;

import edu.hw7.task3.ReadWriteLockPersonServer;
import edu.hw7.task3.SynchronizedPersonServer;
import edu.hw7.task3.Person;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Test Task3")
    void testTask3() throws InterruptedException, ExecutionException {
        // arrange
        var executorService = Executors.newFixedThreadPool(4);
        var person1 = new Person(1, "Nick", "Stockton", "12318391");
        var person2 = new Person(2, "Nate", "Stockton", "12317471");
        var person3 = new Person(3, "Ivan", "Minsk", "0298481994");
        var person4 = new Person(4, "Jacob", "Jamaica", "8182020");

        var server = new SynchronizedPersonServer();
        var countDown = new CountDownLatch(3);

        var add1 = new Thread(() -> {
            countDown.countDown();
            server.add(person1);
        });
        var add2 = new Thread(() -> {
            countDown.countDown();
            server.add(person2);
        });
        var add3 = new Thread(() -> {
            countDown.countDown();
            server.add(person3);
        });
        var add4 = new Thread(() -> {
            countDown.countDown();
            server.add(person4);
        });

        Runnable deleter = () -> server.delete(4);
        Callable<List<Person>> finderByName = () -> server.findByName("Ivan");
        Callable<List<Person>> finderByAddress = () -> server.findByAddress("Stockton");
        Callable<List<Person>> finderByPhone = () -> server.findByPhone("12318391");

        // act
        add1.start();
        add2.start();
        add3.start();

        countDown.await();

        add4.start();

        new Thread(deleter).start();
        var list1 = executorService.submit(finderByName);
        var list2 = executorService.submit(finderByAddress);
        var list3 = executorService.submit(finderByPhone);
        var list4 = executorService.submit(() -> server.findByName("Jacob"));

        var res1 = list1.get();
        var res2 = list2.get();
        var res3 = list3.get();
        var res4 = list4.get();

        executorService.shutdown();
        executorService.close();

        // assert
        assertThat(res1)
            .containsExactlyInAnyOrder(new Person(3, "Ivan", "Minsk", "0298481994"));
        assertThat(res2)
            .containsExactlyInAnyOrder(
                new Person(1, "Nick", "Stockton", "12318391"),
                new Person(2, "Nate", "Stockton", "12317471")
            );
        assertThat(res3)
            .containsExactlyInAnyOrder(new Person(1, "Nick", "Stockton", "12318391"));
        assertThat(res4).isEmpty();
    }

    @Test
    @DisplayName("Test Task 3.5")
    void testTask3_5() throws InterruptedException, ExecutionException {
        // arrange
        var executorService = Executors.newFixedThreadPool(4);
        var person1 = new Person(1, "Nick", "Stockton", "12318391");
        var person2 = new Person(2, "Nate", "Stockton", "12317471");
        var person3 = new Person(3, "Ivan", "Minsk", "0298481994");
        var person4 = new Person(4, "Jacob", "Jamaica", "8182020");

        var server = new ReadWriteLockPersonServer();
        var countDown = new CountDownLatch(3);

        var add1 = new Thread(() -> {
            countDown.countDown();
            server.add(person1);
        });
        var add2 = new Thread(() -> {
            countDown.countDown();
            server.add(person2);
        });
        var add3 = new Thread(() -> {
            countDown.countDown();
            server.add(person3);
        });
        var add4 = new Thread(() -> {
            countDown.countDown();
            server.add(person4);
        });

        Runnable deleter = () -> server.delete(4);
        Callable<List<Person>> finderByName = () -> server.findByName("Ivan");
        Callable<List<Person>> finderByAddress = () -> server.findByAddress("Stockton");
        Callable<List<Person>> finderByPhone = () -> server.findByPhone("12318391");

        // act
        add1.start();
        add2.start();
        add3.start();

        countDown.await();

        add4.start();

        new Thread(deleter).start();
        var list1 = executorService.submit(finderByName);
        var list2 = executorService.submit(finderByAddress);
        var list3 = executorService.submit(finderByPhone);
        var list4 = executorService.submit(() -> server.findByName("Jacob"));

        var res1 = list1.get();
        var res2 = list2.get();
        var res3 = list3.get();
        var res4 = list4.get();

        executorService.shutdown();
        executorService.close();

        // assert
        assertThat(res1)
            .containsExactlyInAnyOrder(new Person(3, "Ivan", "Minsk", "0298481994"));
        assertThat(res2)
            .containsExactlyInAnyOrder(
                new Person(1, "Nick", "Stockton", "12318391"),
                new Person(2, "Nate", "Stockton", "12317471")
            );
        assertThat(res3)
            .containsExactlyInAnyOrder(new Person(1, "Nick", "Stockton", "12318391"));
        assertThat(res4).isEmpty();
    }
}
