package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * non blocking cash.
 */
public class NonBlockCash {
    /**
     * Concurrent safety cache.
     */
    private ConcurrentHashMap<String, Model> cache = new ConcurrentHashMap<>();

    /**
     * getter.
     * @return - cache.
     */
    public ConcurrentHashMap<String, Model> getCache() {
        return cache;
    }

    /**
     * add element.
     * @param key - key.
     * @param model - model.
     * @return - added element.
     */
    Model add(String key, Model model) {
        return cache.put(key, model);
    }

    /**
     * update element. If another thread was changed model after this thread read element - throws exception.
     * @param key - key.
     * @param model - model.
     * @throws OptimisticException - Runtime Exception child.
     */
    void update(String key, Model model) throws OptimisticException {
        cache.computeIfPresent(key, (k, m) -> {
            //в качестве второго аргумента метод computeIfPresent берет значение, соответствующее ключу key.
            //а k присваивается ссылка на key: val = remappingFunction.apply(key, e.val); (это из доков).
            if (model.getVersion() == m.getVersion()) {
                model.incrementVersion();
                return model;
            } else {
                throw new OptimisticException();
            }
        });
    }

    /**
     * delete element.
     *
     * @param key - key.
     */
    void delete(String key) {
        cache.computeIfPresent(key, new BiFunction<String, Model, Model>() {
            @Override
            public Model apply(String s, Model model) {
                return null;
            }
        });
    }
}
