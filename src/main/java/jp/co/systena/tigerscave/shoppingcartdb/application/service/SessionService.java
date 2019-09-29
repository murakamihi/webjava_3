package jp.co.systena.tigerscave.shoppingcartdb.application.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import jp.co.systena.tigerscave.shoppingcartdb.application.model.Cart;
import jp.co.systena.tigerscave.shoppingcartdb.application.model.ListForm;
import jp.co.systena.tigerscave.shoppingcartdb.application.model.Order;

@Service
public class SessionService {

  @Autowired
  JdbcTemplate jdbcTemplate;

  /**
   * セッション管理DBの全情報取得しカートに保存
   *
   * @param cart
   */
  public void setOrderList(Cart cart) {

    // SELECTを使用してテーブルの情報をすべて取得する
    List<Order> list = jdbcTemplate.query("SELECT * FROM session_items ORDER BY item_id",
        new BeanPropertyRowMapper<Order>(Order.class));

    cart.setOrderList(list);
  }

  /**
   * セッション管理DBの更新orインサートを行う
   *
   * @param listForm
   */
  public void sessionUpdate(ListForm listForm) {
    int formId = listForm.getItemId();
    int formNum = listForm.getNum();

    // SELECTを使用して対象リストのテーブルの情報をすべて取得する
    List<Order> list = jdbcTemplate.query("SELECT * FROM session_items WHERE item_id = ?",
        new Object[] {formId}, new BeanPropertyRowMapper<Order>(Order.class));

    if (!list.isEmpty()) {
      //リストが空でない場合はDBの更新処理を行う
      for (Order order : list) {
        // DB更新
        // 個数は今回入力分＋すでにDB登録されている分で登録する
        update(formId, formNum + order.getNum());
      }
    } else {
      // DBへインサート
      insert(formId, formNum);
    }
  }


  /**
   * 「削除」リンク押下時の処理
   *
   * パラメータで受け取ったアイテムIDのデータを削除する
   *
   * @param itemId
   * @return
   */
  public void delete(int itemId) {
    //todo:途中
    // パラメータで受けとったアイテムIDのデータを削除する
    // SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
    jdbcTemplate.update("DELETE FROM items WHERE item_id = ?", itemId);
  }

  /**
   * セッション管理DBのインサート
   *
   * @param id
   * @param num
   */
  private void insert(int id, int num) {

    // 1行分の値をデータベースにINSERTする
    jdbcTemplate.update("INSERT INTO session_items VALUES( ?, ? )", id, num);

  }

  /**
   * セッション管理DBの更新
   *
   * @param id
   * @param num
   */
  private void update(int id, int num) {
    // 1行分の値でデータベースをUPDATEする
    // item_idをキーに個数を更新する
    jdbcTemplate.update("UPDATE session_items SET num = ? WHERE item_id = ?", num, id);
  }
}
