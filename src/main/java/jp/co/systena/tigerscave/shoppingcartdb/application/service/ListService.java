package jp.co.systena.tigerscave.shoppingcartdb.application.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import jp.co.systena.tigerscave.shoppingcartdb.application.model.Item;

@Service
public class ListService {

  @Autowired
  JdbcTemplate jdbcTemplate;

  /**
   * データベースからアイテムデータ一覧を取得する
   *
   * @return
   */
  public List<Item> getItemList() {

    // SELECTを使用してテーブルの情報をすべて取得する
    List<Item> list = jdbcTemplate.query("SELECT * FROM items ORDER BY item_id",
        new BeanPropertyRowMapper<Item>(Item.class));

    return list;
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
}
