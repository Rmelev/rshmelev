package ru.job4j.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * class.
 */
public class OrderBook {

    /**
     * Buy orders.
     */
    private HashMap<Integer, Order> unsorted = new HashMap<>();

    /**
     * Buy orders.
     */
    private TreeMap<Double, HashMap<Integer, Order>> buySort = new TreeMap<>((o1, o2) -> Double.compare(o2, o1));

    /**
     * Sell orders.
     */
    private TreeMap<Double, HashMap<Integer, Order>> sellSort = new TreeMap<>(Double::compare);

    /**
     * Method add orders to buy or sell hashmap.
     * @param order order to add.
     */
    public void add(Order order) {
        unsorted.put(order.getOrderId(), order);
        if (order.isOperation()) {
            addToSorted(buySort, order);
        } else {
            addToSorted(sellSort, order);
        }
    }

    /**
     * Method add orders to sorted maps by prices.
     * @param sortMap map to add.
     * @param order order to add.
     */
    private void addToSorted(TreeMap<Double, HashMap<Integer, Order>> sortMap, Order order) {
        HashMap<Integer, Order> map;
        if (sortMap.containsKey(order.getPrice())) {
            sortMap.get(order.getPrice()).put(order.getOrderId(), order);
        } else {
            map = new HashMap<>();
            map.put(order.getOrderId(), order);
            sortMap.put(order.getPrice(), map);
        }
    }

    /**
     * Method delete order by id.
     * @param orderId id to delete.
     */
    public void delete(int orderId) {
        if (unsorted.containsKey(orderId)) {
            Order order = unsorted.get(orderId);
            if (order.isOperation()) {
                buySort.get(order.getPrice()).remove(order.getOrderId());
                if (buySort.get(order.getPrice()).size() == 0) {
                    buySort.remove(order.getPrice());
                }
            } else {
                sellSort.get(order.getPrice()).remove(order.getOrderId());
                if (sellSort.get(order.getPrice()).size() == 0) {
                    sellSort.remove(order.getPrice());
                }
            }
            unsorted.remove(orderId);
        }
    }

    /**
     * Method make deals between sell and buy orders.
     */
    public void checkSides() {
        Iterator<Double> iterBuy = buySort.keySet().iterator();
        Iterator<Double> iterSell = sellSort.keySet().iterator();
        if (iterBuy.hasNext() && iterSell.hasNext()) {
            Double nextBuy = iterBuy.next();
            Double nextSell = iterSell.next();
            while (iterBuy.hasNext() && iterSell.hasNext()) {
                HashMap<Integer, Order> mapBuy = buySort.get(nextBuy);
                HashMap<Integer, Order> mapSell = sellSort.get(nextSell);
                int mapBuyVolumes = calculateVolume(mapBuy);
                int mapSellVolumes = calculateVolume(mapSell);
                if (mapBuyVolumes > mapSellVolumes && nextBuy >= nextSell) {
                    removeOrders(buySort.get(nextBuy), mapSellVolumes);
                    iterSell.remove();
                    nextSell = iterSell.next();
                } else if (mapBuyVolumes < mapSellVolumes && nextBuy >= nextSell) {
                    removeOrders(sellSort.get(nextSell), mapBuyVolumes);
                    iterBuy.remove();
                    nextBuy = iterBuy.next();
                } else if (mapBuyVolumes == mapSellVolumes && nextBuy >= nextSell) {
                    iterBuy.remove();
                    iterSell.remove();
                    nextSell = iterSell.next();
                    nextBuy = iterBuy.next();
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Method removes orders when check side.
     * @param map map to delete.
     * @param value value to delete.
     */
    private void removeOrders(HashMap<Integer, Order> map, int value) {
        Iterator<Order> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            Order nextOrder = iterator.next();
            if (nextOrder.getVolume() < value) {
                value -= nextOrder.getVolume();
                iterator.remove();
            } else {
                nextOrder.setVolume(nextOrder.getVolume() - value);
                break;
            }
        }
    }


    /**
     * Calculates volumes for each price.
     * @param map map to calculate.
     * @return sum of volumes.
     */
    private int calculateVolume(HashMap<Integer, Order> map) {
        int result = 0;
        for (Order order : map.values()) {
            result += order.getPrice();
        }
        return result;
    }

    /**
     * Method prints pairs of sell orders and buy orders.
     */
    public void print() {
        Iterator<Double> iterBuy = buySort.keySet().iterator();
        Iterator<Double> iterSell = sellSort.keySet().iterator();
        while (iterBuy.hasNext() && iterSell.hasNext()) {
            Double nextBuy = iterBuy.next();
            Double nextSell = iterSell.next();
            HashMap<Integer, Order> mapBuy = buySort.get(nextBuy);
            HashMap<Integer, Order> mapSell = sellSort.get(nextSell);
            System.out.println(String.format("%s@%s - %s@%s", calculateVolume(mapBuy), nextBuy,
                    calculateVolume(mapSell), nextSell));
        }
    }

}
