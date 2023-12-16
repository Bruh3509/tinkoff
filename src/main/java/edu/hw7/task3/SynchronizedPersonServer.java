package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedPersonServer implements PersonDatabase {
    private final Map<String, List<Person>> peopleByName;
    private final Map<String, List<Person>> peopleByAddress;
    private final Map<String, List<Person>> peopleByPhone;

    public SynchronizedPersonServer() {
        peopleByName = new HashMap<>();
        peopleByAddress = new HashMap<>();
        peopleByPhone = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        peopleByName.putIfAbsent(person.name(), new ArrayList<>());
        peopleByPhone.putIfAbsent(person.phone(), new ArrayList<>());
        peopleByAddress.putIfAbsent(person.address(), new ArrayList<>());

        peopleByName.get(person.name()).add(person);
        peopleByPhone.get(person.phone()).add(person);
        peopleByAddress.get(person.address()).add(person);
    }

    @Override
    public synchronized void delete(int id) {
        peopleByName.keySet()
            .forEach(key -> peopleByName.get(key).removeIf(person -> person.id() == id));
        peopleByAddress.keySet()
            .forEach(key -> peopleByAddress.get(key).removeIf(person -> person.id() == id));
        peopleByPhone.keySet()
            .forEach(key -> peopleByPhone.get(key).removeIf(person -> person.id() == id));
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return peopleByName.get(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return peopleByAddress.get(address);
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return peopleByPhone.get(phone);
    }
}
