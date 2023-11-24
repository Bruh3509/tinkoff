package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockPersonServer implements PersonDatabase {
    private final Map<String, List<Person>> peopleByName;
    private final Map<String, List<Person>> peopleByAddress;
    private final Map<String, List<Person>> peopleByPhone;
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock(true);

    public ReadWriteLockPersonServer() {
        peopleByName = new HashMap<>();
        peopleByAddress = new HashMap<>();
        peopleByPhone = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        rwLock.writeLock().lock();
        try {
            peopleByName.putIfAbsent(person.name(), new ArrayList<>());
            peopleByPhone.putIfAbsent(person.phone(), new ArrayList<>());
            peopleByAddress.putIfAbsent(person.address(), new ArrayList<>());

            peopleByName.get(person.name()).add(person);
            peopleByPhone.get(person.phone()).add(person);
            peopleByAddress.get(person.address()).add(person);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        rwLock.writeLock().lock();
        try {
            peopleByName.keySet()
                .forEach(key -> peopleByName.get(key).removeIf(person -> person.id() == id));
            peopleByAddress.keySet()
                .forEach(key -> peopleByAddress.get(key).removeIf(person -> person.id() == id));
            peopleByPhone.keySet()
                .forEach(key -> peopleByPhone.get(key).removeIf(person -> person.id() == id));
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        rwLock.readLock().lock();
        try {
            return peopleByName.get(name);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        rwLock.readLock().lock();
        try {
            return peopleByAddress.get(address);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        rwLock.readLock().lock();
        try {
            return peopleByPhone.get(phone);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
