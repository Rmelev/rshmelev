package ru.job4j.testtask;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import java.util.Iterator;
import java.util.Comparator;

/**
 * operations with order books.
 */
public class OrderBooksCreation {
    /**
     * divide orders to buy orders & sell orders.
     * @param map - map ouf compound orders.
     */
    void divideToBuyAndSell(HashMap<String, Order> map) {
        List<Order> list = new ArrayList<>(map.values());
        List<Order> listBuy = new ArrayList<>();
        List<Order> listSell = new ArrayList<>();

        for (Order tempOrder : list) {
            if (tempOrder.isOperation()) {
                listBuy.add(tempOrder);
            } else {
                listSell.add(tempOrder);
            }
        }
        listBuy.sort(Comparator.comparing(Order::getPrice));
        listSell.sort(Comparator.comparing(Order::getPrice));
        Collection<Order> addedVolumesListBuy = onePriceOneOrder(listBuy);
        Collection<Order> addedVolumesListSell = onePriceOneOrder(listSell);

        pairCompare(addedVolumesListBuy, addedVolumesListSell);
    }

    /**
     * compound orders with one price.
     * @param list - list of all orders (buy or sell).
     * @return - Collection with exclusive price and compounded volumes of orders with the same price.
     */
    private Collection<Order> onePriceOneOrder(List<Order> list) {
        TreeMap<Double, Order> mapReady = new TreeMap<>();
        int sum = 0;
        for (Order tempOrder : list) {
            if (mapReady.containsKey(tempOrder.getPrice())) {
                sum += tempOrder.getVolume();
                mapReady.put(tempOrder.getPrice(), tempOrder.setVolume(sum));
            } else {
                mapReady.put(tempOrder.getPrice(), tempOrder);
                sum = tempOrder.getVolume();
            }
        }
        return mapReady.values();
    }

    /**
     * compare orders in buy list and in sell list to get deal.
     * @param listB - list with buy orders.
     * @param listS - list with sell orders.
     */
    private void pairCompare(Collection<Order> listB, Collection<Order> listS) {
        List<Order> listBuy = new ArrayList<>(listB);
        List<Order> listSell = new ArrayList<>(listS);
        listBuy.sort(Comparator.comparing(Order::getPrice));
        listSell.sort(Comparator.comparing(Order::getPrice));
        Iterator<Order> iterBuy = listBuy.iterator();
        Iterator<Order> iterSell = listSell.iterator();
        Order tempBuy = iterBuy.next();
        Order tempSell = iterSell.next();
        while (iterBuy.hasNext() && iterSell.hasNext()) {
            int newVolume = Math.max(tempBuy.getVolume(), tempSell.getVolume())
                    - Math.min(tempBuy.getVolume(), tempSell.getVolume()
            );
            if (tempBuy.getPrice() >= tempSell.getPrice()) {
                if (tempBuy.getVolume() == tempSell.getVolume()) {
                    iterBuy.remove();
                    iterSell.remove();
                    tempBuy = iterBuy.next();
                    tempSell = iterSell.next();
                } else {
                    if (tempBuy.getVolume() - tempSell.getVolume() > 0) {
                        tempBuy.setVol(newVolume);
                        iterSell.remove();
                        tempSell = iterSell.next();
                    } else {
                        tempSell.setVol(newVolume);
                        iterBuy.remove();
                        tempBuy = iterBuy.next();
                    }
                }
            } else {
                tempBuy = iterBuy.next();
            }

            // весь этот код ниже в этом методе, только для обработки последнего ордера в любом из списков.
            // очень много занимает, но я не нашел алгоритма сократить.
            // по идее, можно вынести общий код, но тогда нужно отправлять в метод не только списки ордеров
            // но и создавать внутри метода итераторы. По итогу сильного сокращения кода не добиться этим способом.
            if (!iterBuy.hasNext() && iterSell.hasNext()) {
                while (true) {
                    if (tempSell.getPrice() > tempBuy.getPrice()) {
                        break;
                    }
                    if (tempBuy.getPrice() >= tempSell.getPrice() && tempSell.getVolume() > tempBuy.getVolume()) {
                        tempSell.setVol(tempSell.getVolume() - tempBuy.getVolume());
                        iterBuy.remove();
                        break;
                    }
                    if (tempBuy.getPrice() >= tempSell.getPrice() && tempSell.getVolume() < tempBuy.getVolume()) {
                        tempBuy.setVol(tempBuy.getVolume() - tempSell.getVolume());
                        iterSell.remove();
                        if (iterSell.hasNext()) {
                            tempSell = iterSell.next();
                        }
                        continue;
                    }
                    if (tempBuy.getPrice() >= tempSell.getPrice() && tempSell.getVolume() == tempBuy.getVolume()) {
                        iterBuy.remove();
                        iterSell.remove();
                        break;
                    }
                }
            }

            if (!iterSell.hasNext() && iterBuy.hasNext()) {
                while (true) {
                    if (tempBuy.getPrice() >= tempSell.getPrice() && tempSell.getVolume() < tempBuy.getVolume()) {
                        tempBuy.setVol(tempBuy.getVolume() - tempSell.getVolume());
                        iterSell.remove();
                        break;
                    }
                    if (tempBuy.getPrice() >= tempSell.getPrice() && tempSell.getVolume() > tempBuy.getVolume()) {
                        tempSell.setVol(tempSell.getVolume() - tempBuy.getVolume());
                        iterBuy.remove();
                        if (iterBuy.hasNext()) {
                            tempBuy = iterBuy.next();
                        }
                        continue;
                    }
                    if (tempBuy.getPrice() >= tempSell.getPrice() && tempSell.getVolume() == tempBuy.getVolume()) {
                        iterBuy.remove();
                        iterSell.remove();
                        break;
                    }
                }
            }
            if (!iterSell.hasNext() && !iterBuy.hasNext()) {
                if (tempSell.getVolume() > tempBuy.getVolume()) {
                    tempSell.setVol(tempSell.getVolume() - tempBuy.getVolume());
                    iterBuy.remove();
                } else if (tempSell.getVolume() < tempBuy.getVolume()) {
                    tempBuy.setVol(tempBuy.getVolume() - tempSell.getVolume());
                    iterSell.remove();
                } else {
                    iterBuy.remove();
                    iterSell.remove();
                }
            }
        }

        printResult(listBuy, listSell);
    }

    /**
     * print result.
     * @param listBuy - finish buy orders with lower price than "deal price".
     * @param listSell - finish sell orders with higherer price than "deal price".
     */
    private void printResult(List<Order> listBuy, List<Order> listSell) {
        System.out.println("---------------------------------ASK");
        for (int i = listSell.size() - 1; i >= 0; i--) {
            System.out.print("---------------------------------" + listSell.get(i));
        }
        System.out.println("BID");
        for (int i = listBuy.size() - 1; i >= 0; i--) {
            System.out.print(listBuy.get(i));
        }
        System.out.println();
    }
}