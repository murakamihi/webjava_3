package jp.co.systena.tigerscave.shoppingcartdb.application.model;

public class Item {
  int itemId;
  String name;
  int price;

  /**
   * itemIdのゲッター
   *
   * @return itemId
   */
  public int getItemId() {
    return itemId;
  }

  /**
   * itemIdのセッター
   *
   * @param itemId
   */
  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  /**
   * nameのゲッター
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * nameのセッター
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * priceのゲッター
   *
   * @return price
   */
  public int getPrice() {
    return price;
  }

  /**
   * priceのセッター
   *
   * @param price
   */
  public void setPrice(int price) {
    this.price = price;
  }
}
