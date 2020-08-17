package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import static lib.Printer.println;
import static observer.EventBusData.EventType.*;

public class EventBusData {

    public enum EventType {
        EXCEED_CAPACITY, ADDED_NEW_ITEM, STORAGE_IS_EMPTY, REMOVED_LAST_ITEM
    }

    public interface EventSubscriber<T> {
        void notify(Operator operator, EventType type, T data);
    }

    public static class Storage<T> {

        private int capacity;
        private List<T> items;

        public Storage(int capacity) {
            this.capacity = capacity;
            this.items = new ArrayList<>();
        }

        public void add(Operator operator, T item) {
            boolean canAdd = items.size() < capacity;

            if (canAdd) {
                items.add(item);
                EventsManager.sharedInstance.publish(operator, ADDED_NEW_ITEM, item);
            }
            else {
                EventsManager.sharedInstance.publish(operator, EXCEED_CAPACITY, item);
            }
        }

        public void removeLastItem(Operator operator) {

            boolean canRemove = (items.size() > 0);

            if (canRemove) {
                T item = items.remove(items.size()-1);
                EventsManager.sharedInstance.publish(operator, REMOVED_LAST_ITEM, item);
            }
            else {
                EventsManager.sharedInstance.publish(operator, STORAGE_IS_EMPTY, null);
            }
        }
    }

    public static class EventsManager<T> {

        private ConcurrentHashMap<EventType, Set<EventSubscriber>> map = new ConcurrentHashMap();

        public static EventsManager sharedInstance = new EventsManager();
        private EventsManager() {}

        public void publish(Operator operator, EventType type, T data) {
            if (!map.containsKey(type)) return;
            for (EventSubscriber subscriber : map.get(type)) {
                subscriber.notify(operator, type, data);
            }
        }


        public void subscribe(EventType type, EventSubscriber<T> subscriber) {
            Set<EventSubscriber> set = map.getOrDefault(type, ConcurrentHashMap.newKeySet());
            set.add(subscriber);
            map.put(type, set);
        }

        public void unsubscribe(EventType type, EventSubscriber<T> subscriber) {
            if (!map.containsKey(type)) return;

            Set<EventSubscriber> set = map.get(type);
            set.remove(subscriber);
        }

        public void unsubscribe(EventSubscriber<T> subscriber) {
            for (EventType type : map.keySet()) {
                Set<EventSubscriber> set = map.get(type);
                if (set.contains(subscriber)) set.remove(subscriber);
            }
        }

        public void unsubscribeAll() {
            map.clear();
        }
    }

    public static abstract class Operator implements EventSubscriber<Integer>, Runnable {

        protected String name;
        protected boolean stop;
        protected Storage<Integer> storage;

        public Operator(Storage<Integer> storage, String name) {
            this.name = name;
            this.storage = storage;
        }

        public abstract void execute();

        @Override
        public void run() {
            while (!stop) {
                execute();
                sleep(100);
            }
        }

        public void stop() {
            stop = true;
            EventsManager.sharedInstance.unsubscribe(this);
        }

        public void sleep(long millis) {
            try {
                Thread.sleep(millis);
            }
            catch (Exception ex) {
                println("Ops! Something wrong ...");
            }
        }
    }

    public static class Consumer extends Operator {

        public Consumer(Storage<Integer> storage, String name) {
            super(storage, name);
            EventsManager.sharedInstance.subscribe(STORAGE_IS_EMPTY, this);
        }

        @Override
        public void execute() {
            storage.removeLastItem(this);
        }

        @Override
        public void notify(Operator operator, EventType type, Integer data) {

            if (this == operator && type == STORAGE_IS_EMPTY)
                sleep(2000);
        }
    }

    public static class Producer extends Operator {

        public Producer(Storage<Integer> storage, String name) {
            super(storage, name);
            EventsManager.sharedInstance.subscribe(EXCEED_CAPACITY, this);
        }

        @Override
        public void execute() {
            int item = ThreadLocalRandom.current().nextInt(100);
            storage.add(this, item);
        }

        @Override
        public void notify(Operator operator, EventType type, Integer data) {
            if (operator == this && type == EXCEED_CAPACITY)
                sleep(1000);
        }
    }

    public static class Logger implements EventSubscriber<Integer> {

        public Logger() {
            for (EventType type : EventType.values())
                EventsManager.sharedInstance.subscribe(type, this);
        }

        @Override
        public void notify(Operator operator, EventType type, Integer data) {

            String operatorName = (operator == null) ? "Unknown" : operator.name;

            switch (type) {
                case ADDED_NEW_ITEM:
                    println(operatorName + " added : " + "\t\t" + data);
                    break;
                case REMOVED_LAST_ITEM:
                    println(operatorName + " removed : " + "\t\t" + data);
                    break;
                case EXCEED_CAPACITY:
                    println(operatorName + " try to add : " + "\t\t" + data + "\t\t Storage's capacity is exceeded!");
                    break;
                default:
                    println(operatorName + " try to remove last item." + "\t\t Storage is empty now!");
                    break;
            }
        }
    }
}
