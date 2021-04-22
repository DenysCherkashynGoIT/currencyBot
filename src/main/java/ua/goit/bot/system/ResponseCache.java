package ua.goit.bot.system;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ResponseCache {
    private static final ResponseCache CACHE = new ResponseCache();
    private static final long DELAY_TIME = 5 * 60 * 1000;
    private static ConcurrentHashMap<Key, List<BankResponse>> map;
    private static DelayQueue<Key> toRemove;

    private ResponseCache() {
        map = new ConcurrentHashMap<>();
        toRemove = new DelayQueue<>();
        Thread cleaner = new Cleaner();
        cleaner.setDaemon(true);
        cleaner.start();
    }

    public static ResponseCache getInstance() {
        return CACHE;
    }

    protected static void removeResponse(Key key) {
        map.remove(key);
    }

    public void saveResponse(List<BankResponse> list, Bank bank) {
        Key key = new Key(bank.name());
        map.putIfAbsent(key, list);
        toRemove.put(key);
    }

    public List<BankResponse> response(Bank bank) {
        return map.get(new Key(bank.name()));
    }

    protected static Key toDelete() {
        try {
            return toRemove.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    static class Key implements Delayed {
        private final String key;
        private final long duration;

        Key(String key) {
            this.key = key;
            this.duration = System.currentTimeMillis() + DELAY_TIME;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = this.getDuration() - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.duration - ((Key) o).getDuration());
        }

        private long getDuration() {
            return this.duration;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;
            Key key1 = (Key) o;
            return key.equals(key1.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
