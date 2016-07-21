package com.onlineTrade.web;

import java.io.IOException;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.onlineTrade.dao.ProductDao;
import com.onlineTrade.dao.UserValidateDao;
import com.onlineTrade.entity.Person;
import com.onlineTrade.service.TradeService;
import com.onlineTrade.service.UserService;
import com.onlineTrade.service.impl.UploadImpl;

@Controller
@RequestMapping("/")
public class OnlineTradeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Person user;
	@Autowired
	private UserService userService;
	@Autowired
	private TradeService ts;
	@Resource
	private UserValidateDao userValidateDao;
	@Resource
	private ProductDao pdao;
	@Autowired
	private UploadImpl upload;

	String image;

	// 根目录
	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("productList", pdao.productList());

		return "index";
	}

	// 进入login页面
	@RequestMapping(value = "/login") // @Param("userName") @Param("password")
	public String login() {
		return "login";
	}

	// login，并创建Session
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public @ResponseBody Model apiLogin(String userName, String password, Model model, HttpServletRequest request) {
		if (userService.login(userName, password)) {
			user = userValidateDao.getUserInfo(userName);
			request.getSession().setAttribute("user", user);
			model.addAttribute("code", 200);
			model.addAttribute("message", "成功");
			model.addAttribute("result", true);
			return model;
		} else {
			// model.addAttribute("userLogin", false);
			model.addAttribute("code", null);
			model.addAttribute("message", "请输入正确的账户或密码");
			model.addAttribute("result", false);
			return model;
		}

	}

	// 退出登录，并注销session
	@RequestMapping(value = "/logout") // @Param("userName") @Param("password")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "login";
	}

	// 展示所有商品
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showProduct(@RequestParam("id") Integer id, Model model, HttpSession session) {

		model.addAttribute("product", pdao.showDetail(id));
		System.out.println(model);
		return "show";
	}

	@RequestMapping("/public")
	public String establish(HttpSession session, Model model) {
		return "public";
	}

	// 发布商品
	@RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
	public String publicSubmit(@RequestParam("title") String title, @RequestParam("detail") String detail,
			@RequestParam("price") long price, @RequestParam("summary") String summary, Model model) {

		pdao.publish(title, detail, image, price, summary);

		model.addAttribute("product", pdao.productQueryByTitle(title));
		return "publicSubmit";
	}

	// 按照contentId删除指定商品
	@RequestMapping("/api/delete")
	public @ResponseBody Model delete(@RequestParam("contentId") int contentId, Model model) {
		if (pdao.delete(contentId)) {
			model.addAttribute("code", 200);
			model.addAttribute("message", "成功");
			model.addAttribute("result", true);
		} else {
			model.addAttribute("code", null);
			model.addAttribute("message", "删除失败");
			model.addAttribute("result", false);
		}
		return model;
	}

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account() {
		pdao.queryTrxByUserId(user.getId());
		return "account";
	}

	@RequestMapping(value = "/settleAccount", method = RequestMethod.GET)
	public String settleAccount() {
		return "settleAccount";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@Param("id") Integer contentId, Model model) {

		model.addAttribute("product", pdao.productQueryById(contentId));
		return "edit";
	}

	@RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
	public String editSubmit(@RequestParam("id") int id, @RequestParam("title") String title,
			@RequestParam("summary") String summary, @RequestParam("detail") String detail,
			@RequestParam("price") long price, Model model) {
		pdao.publish(title, detail, image, price, summary);
		model.addAttribute("product", pdao.productQueryByTitle(title));
		if (model != null) {
			return "editSubmit";
		}else{
			return "edit";
			}
	}

	// 文件上传
	@RequestMapping(value = "/api/upload", method = RequestMethod.POST)
	public @ResponseBody Model doUpload(@RequestParam("file") MultipartFile file, Model model,
			HttpServletRequest request) throws IOException {
		if (!file.isEmpty()) {
			logger.debug("Process file:{}", file.getOriginalFilename());
			upload.upload(file, request);
			model.addAttribute("code", 200);
			model.addAttribute("message", "成功");
			image = upload.getStorePath();
			return model.addAttribute("result", image);
		}
		model.addAttribute("code", "wrong");
		model.addAttribute("message", "请求有误");
		return model.addAttribute("result", null);

	}

	@RequestMapping(value = "/api/buy", method = RequestMethod.POST)
	public @ResponseBody String buyProduct(int contentId, int number, Model model) {
		if (contentId != 0 && number > 0) {
			ts.buy(contentId, number);
			pdao.setbuyer(contentId);
			pdao.setSeller(contentId);
			model.addAttribute("code", 200);
			model.addAttribute("message", "购买成功");
			model.addAttribute("result", true);
		} else {
			model.addAttribute("code", null);
			model.addAttribute("message", "删除失败");
			model.addAttribute("result", false);
		}
		return "show";
	}
	

}
