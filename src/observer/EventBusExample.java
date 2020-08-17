package observer;

import observer.EventBusData.*;

import static lib.Printer.println;

public class EventBusExample {

    public static void run() {

        Storage<Integer> storage = new Storage<>(10);
        Logger logger = new Logger();

        Producer producerUSA = new Producer(storage, "USA");
        Producer producerJapan = new Producer(storage, "Japan");
        Producer producerEU = new Producer(storage, "EU");
        Consumer consumerVietnam = new Consumer(storage, "Vietnam");

        Thread t1 = new Thread(producerUSA);
        Thread t2 = new Thread(producerJapan);
        Thread t3 = new Thread(producerEU);

        Thread t4 = new Thread(consumerVietnam);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            Thread.sleep(10*1000L);
            producerUSA.stop();
            producerJapan.stop();
            producerEU.stop();
            consumerVietnam.stop();
            EventsManager.sharedInstance.unsubscribe(logger);
            EventsManager.sharedInstance.unsubscribeAll();
        }
        catch (InterruptedException ex) {
            println("Ops! Something wrong ...");
        }

    }
}
