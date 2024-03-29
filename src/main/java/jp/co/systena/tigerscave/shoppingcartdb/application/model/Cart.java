package jp.co.systena.tigerscave.shoppingcartdb.application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cart {
  public List<Order> orderList;

  /**
   * コンストラクタ
   */
  public Cart() {
    orderList = new ArrayList<Order>();
  }

  /**
   * オーダーリストをカートに設定
   *
   * @param orderList
   */
  public void setOrderList(List<Order> orderList) {
    this.orderList = orderList;
  }

  /**
   * カートリストの合計金額を返却
   *
   * @param itemListMap<Itemのid, Item>
   * @return 合計金額
   */
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
