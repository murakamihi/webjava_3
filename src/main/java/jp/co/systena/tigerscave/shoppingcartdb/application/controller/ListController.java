package jp.co.systena.tigerscave.shoppingcartdb.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingcartdb.application.model.Cart;
import jp.co.systena.tigerscave.shoppingcartdb.application.model.Item;
import jp.co.systena.tigerscave.shoppingcartdb.application.model.ListForm;
import jp.co.systena.tigerscave.shoppingcartdb.application.service.ListService;
import jp.co.systena.tigerscave.shoppingcartdb.application.service.SessionService;

@Controller
public class ListController {

//  @Autowired
//  HttpSession session; // セッション管理

  @Autowired
  private  ListService listService;//リスト

  @Autowired
  private  SessionService sessionService;//リスト


  @RequestMapping(value = "/list", method = RequestMethod.GET) // URLとのマッピング
  public ModelAndView show(ModelAndView mav) {
    // 商品一覧設定
    Map<Integer, Item> itemListMap = getItemListMap();
    mav.addObject("itemList", itemListMap);

    // カート情報設定
    //Cart cart = (Cart) session.getAttribute("cart");
    Cart cart = new Cart();
//    if (cart == null) {
//      cart = new Cart();
//    }
    sessionService.setOrderList(cart);
    //session.setAttribute("cart", cart);
    mav.addObject("cart", cart);

    // リストフォームを新規設定
    mav.addObject("listForm", new ListForm());

    // 合計金額設定
    int total = cart.calculateTotal(itemListMap);
    mav.addObject("total", total);

    // Viewのテンプレート名を設定
    mav.setViewName("ListView");
    return mav;
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST) // URLとのマッピング
  public ModelAndView order(ModelAndView mav, @Valid ListForm listForm, BindingResult bindingResult,
      HttpServletRequest request) {

    if (bindingResult.getAllErrors().size() > 0) {
      // エラーがある場合はそのまま戻す
      mav.addObject("listFrom", listForm);

      // 商品一覧設定
      Map<Integer, Item> itemListMap = getItemListMap();
      mav.addObject("itemList", itemListMap);

      mav.setViewName("ListView");

      return mav;
    }

//    Cart cart = (Cart) session.getAttribute("cart");
//    if (cart == null) {
//      cart = new Cart();
//    }
//
//    cart.addOrder(listForm);
//    session.setAttribute("cart", cart);
    sessionService.insert(listForm);

    return new ModelAndView("redirect:/list"); // リダイレクト
  }

  private Map<Integer, Item> getItemListMap() {
//    ListService listService = new ListService();
    List<Item> itemList = listService.getItemList();

    Map<Integer, Item> itemListMap = new HashMap<Integer, Item>();
//    listService.delete(itemList.get(0).getItemId());
    for (Item item : itemList) {
      itemListMap.put(item.getItemId(), item);
    }

    return itemListMap;
  }
}
