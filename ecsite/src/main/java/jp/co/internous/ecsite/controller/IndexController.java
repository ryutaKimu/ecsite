package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import jp.co.internous.ecsite.model.domain.MstGoods;
import jp.co.internous.ecsite.model.domain.MstUser;
import jp.co.internous.ecsite.model.dto.HistoryDto;
import jp.co.internous.ecsite.model.form.CartForm;
import jp.co.internous.ecsite.model.form.HistoryForm;
import jp.co.internous.ecsite.model.form.LoginForm;
import jp.co.internous.ecsite.model.form.RegisterForm;
import jp.co.internous.ecsite.model.mapper.MstGoodsMapper;
import jp.co.internous.ecsite.model.mapper.MstUserMapper;
import jp.co.internous.ecsite.model.mapper.TblPurchaseMapper;

@Controller

@RequestMapping("/ecsite")
public class IndexController {

	@Autowired
	private MstGoodsMapper goodsMapper;
	
	@Autowired
	private MstUserMapper userMapper;
	
	private Gson gson = new Gson();
	
	@Autowired 
	private TblPurchaseMapper purchaseMapper;
	
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<MstGoods> goods = goodsMapper.findAll();
		model.addAttribute("goods",goods);
		
		return "index";
	}
	
	@ResponseBody
	@PostMapping("/api/login")
	
	public String loginApi(@RequestBody LoginForm f) {
		MstUser user = userMapper.findByUserNameAndPassword(f);
		
		if(user == null) {
			user = new MstUser();
			user.setFullName("ゲスト");
		}
		return gson.toJson(user);
	}
	
	@ResponseBody
	@PostMapping("/api/purchase")
	
	public int purchaseApi(@RequestBody CartForm f) {
		
		f.getCartList().forEach((c)->{
			int total = c.getPrice() * c.getCount();
			purchaseMapper.insert(f.getUserId(),c.getId(),c.getGoodsName(),c.getCount(),total);
		});
		
		return f.getCartList().size();
	}
	
	@ResponseBody
	@PostMapping("/api/history")
	 public String historyApi(@RequestBody HistoryForm f) {
		int userId = f.getUserId();
		List<HistoryDto> history = purchaseMapper.findHistory(userId);
		
		return gson.toJson(history);
	}
	
	 @GetMapping("/register")
	 public String registrationForm() {
		 
		 return "register";
	 }
	
	 @PostMapping("/register")
	 
	 public ModelAndView registerUser(RegisterForm form) {
		    // ユーザー名を取得
	        String userName = form.getUserName();
	        
	        // フルネームにユーザー名を設定
	        form.setFullName(userName);
		 ModelAndView mv = new ModelAndView();
		 int result =  userMapper.insert(form);
		  if (result > 0) {
	            mv.addObject("message", "新規会員登録が完了しました。");
	            mv.setViewName("registerSuccess");
	        } else {
	            mv.addObject("message", "新規会員登録に失敗しました。");
	            mv.setViewName("registerError");
	        }
		  
		  mv.setViewName("register");
		  
		  return mv;
	 }
	
	
	
	
	
}
