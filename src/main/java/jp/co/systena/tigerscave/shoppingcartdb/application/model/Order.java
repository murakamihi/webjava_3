package jp.co.systena.tigerscave.shoppingcartdb.application.model;

public class Order {
  int itemId;
  int num;

  /**
   * itemIdのセッター
   *
   * @param itemId
   */
  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  /**
   * itemIdのゲッター
   *
   * @return itemId
   */
  public int getItemId() {
    return itemId;
  }

  /**
   * numのセッター
   *
   * @param num
   */
  public void setNum(int num) {
    this.num = num;
  }

  /**
   * numのゲッター
   *
   * @return num
   */
  public int getNum() {
    return num;
  }
}
