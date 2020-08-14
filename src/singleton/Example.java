package singleton;

public class Example {

    public static class SingletonSample {

        private static SingletonSample instance = null;

        // not allow to initiate from outside
        private SingletonSample() {}

        public static SingletonSample getInstance() {
            return (instance == null) ? new SingletonSample() : instance;
        }
    }
}
