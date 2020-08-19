package iterator;

import java.util.Random;

public class Data {

    public interface Iterator<T> {
        boolean hasNext();
        T next();
        void reset();
    }

    public static class DummyComplexObject {

        // ... complex code here

        // get dummy next element
        private int getRandomNumber(int bound) {
            Random random = new Random();
            return random.nextInt(bound);
        }

        public Iterator<Integer> iterator() {
            return new Itr(this);
        }

        private class Itr implements Iterator<Integer> {

            private int max = 10;
            private int cur = 0;
            private DummyComplexObject complexObject;

            public Itr(DummyComplexObject complexObject) {
                this.complexObject = complexObject;
            }

            @Override
            public boolean hasNext() {
                return cur < max;
            }

            @Override
            public Integer next() {
                if (!hasNext()) return null;
                cur++;
                return complexObject.getRandomNumber(max);
            }

            @Override
            public void reset() {
                cur = 0;
            }
        }
    }
}
