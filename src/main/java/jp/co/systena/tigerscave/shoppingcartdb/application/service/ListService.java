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
   * 商品管理DBからアイテムデータ一覧を取得する
   *
   * @return アイテムリスト
   */
  public List<Item> getItemList() {

    // SELECTを使用してテーブルの情報をすべて取得する
    List<Item> list = jdbcTemplate.query("SELECT * FROM items ORDER BY item_id",
        new BeanPropertyRowMapper<Item>(Item.class));

    return list;
  }
}
