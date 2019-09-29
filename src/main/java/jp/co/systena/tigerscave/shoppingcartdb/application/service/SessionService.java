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
   * データベースからアイテムデータ一覧を取得する
   *
   * @return
   */
  public void setOrderList(Cart cart) {

    // SELECTを使用してテーブルの情報をすべて取得する
    List<Order> list = jdbcTemplate.query("SELECT * FROM session_items ORDER BY item_id",
        new BeanPropertyRowMapper<Order>(Order.class));

    cart.setOrderList(list);
  }

  public void sessionUpdate(ListForm listForm) {
    int listId = listForm.getItemId();
    int listNum = listForm.getNum();

    // SELECTを使用して対象リストのテーブルの情報をすべて取得する
    List<Order> list = jdbcTemplate.query("SELECT * FROM session_items WHERE item_id = ?",
        new Object[] {listId}, new BeanPropertyRowMapper<Order>(Order.class));

    if (!list.isEmpty()) {
      for (Order order : list) {
        // データベース更新
        // プラスする
        update(listId, listNum + order.getNum());
      }
    } else {
      // 新規入力
      insert(listForm);
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


    // 本来はここで入力チェックなど


    // パラメータで受けとったアイテムIDのデータを削除する
    // SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
    jdbcTemplate.update("DELETE FROM items WHERE item_id = ?", itemId);
  }

  /**
   * 「登録」ボタン押下時の処理
   *
   * 入力されたアイテムID、名前、価格をデータベースに登録する
   *
   * @param form
   * @param result
   * @param model
   * @return
   */
  public void insert(ListForm listform) {

    // 1行分の値をデータベースにINSERTする
    // SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
    jdbcTemplate.update("INSERT INTO session_items VALUES( ?, ? )", listform.getItemId(),
        listform.getNum());

  }

  /**
   * 「更新」ボタン押下時の処理
   *
   * 入力された名前と価格をアイテムIDをキーとして更新する
   *
   * @param listForm
   * @param result
   * @param model
   * @return
   */
  public void update(int id, int num) {
    // 1行分の値でデータベースをUPDATEする
    // item_idをキーに名称と価格を更新する
    // SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
    jdbcTemplate.update("UPDATE session_items SET num = ? WHERE item_id = ?", num, id);
  }
}
