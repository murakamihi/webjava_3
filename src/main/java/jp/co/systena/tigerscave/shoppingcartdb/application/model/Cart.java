package jp.co.systena.tigerscave.shoppingcartdb.application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Cart {
  public List<Order> orderList;

  public Cart() {
    orderList = new ArrayList<Order>();
  }

  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }

//  public List<Order> getOrderList() {
//    return orderList;
//  }

  public void addOrder(ListForm listform) {
    // オーダーにリストフォームを設定
    Order order = new Order();
    order.setItemId(listform.getItemId());

    List<Order> cartList = orderList;
    Map<Integer, Order> orderListMap = getOrderListMap(cartList);

    //すでにカートに入っているアイテム数
    int orderNum = 0;
    //カートに追加されたアイテム数
    int listNum = listform.getNum();

    if (orderListMap.containsKey(listform.getItemId())) {
      //カートに追加されたキーのアイテム数
      orderNum = orderListMap.get(listform.getItemId()).getNum();
    }
    if (listNum == 0) {
      //カートに追加したアイテム数が0の場合はそのまま設定
      order.setNum(listNum);
    } else {
      //すでにカートに入っているアイテム数＋カートに追加したアイテム数
      order.setNum(orderNum + listNum);
    }
    orderListMap.put(listform.getItemId(), order);

    cartList.clear();
    for (Iterator<Integer> i = orderListMap.keySet().iterator(); i.hasNext();) {
      int key = i.next();
      if (orderListMap.get(key).getNum() == 0) {
        // 購入数が0の場合は削除
      } else {
        cartList.add(orderListMap.get(key));
      }
    }
  }

  private Map<Integer, Order> getOrderListMap(List<Order> cartList) {
    Map<Integer, Order> orderListMap = new HashMap<Integer, Order>();
    for (Order order : cartList) {
      orderListMap.put(order.getItemId(), order);
    }

    return orderListMap;
  }

  public int calculateTotal(Map<Integer, Item> itemListMap) {
    // カートリストの合計金額
    int totalPrice = 0;
    if (!orderList.isEmpty()) {
      for (Order order : orderList) {
        if (itemListMap.containsKey(order.getItemId())) {
          totalPrice += itemListMap.get(order.getItemId()).getPrice() * order.getNum();
        }
      }
    }
    return totalPrice;
  }
}
