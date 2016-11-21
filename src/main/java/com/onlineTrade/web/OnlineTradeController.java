package com.onlineTrade.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.onlineTrade.entity.Person;
import com.onlineTrade.service.OnlineTradeService;
import com.onlineTrade.service.UploadService;
import com.onlineTrade.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 用于网页的映射
 * 
 * @author kilido
 *
 */
@Controller
@RequestMapping("/")
public class OnlineTradeController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private Person user;
	@Autowired
	private UserService userService;
	@Autowired
	private OnlineTradeService onlineService;
	@Autowired
	private UploadService upload;

	/**
	 * 
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {

		JSONArray showProduct = new JSONArray();

		showProduct = onlineService.showIndex(user);

		model.addAttribute("productList", showProduct);

		return "index";
	}

	/**
	 * 登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login") // @Param("userName") @Param("password")
	public String login() {
		return "login";
	}

	/**
	 * 登录界面，校对，存储到session中
	 * 
	 * @param userName
	 * @param password
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public @ResponseBody Model apiLogin(String userName, String password, Model model, HttpServletRequest request) {
		if (userService.login(userName, password)) {
			user = userService.getUserInfo(userName);
			request.getSession().setAttribute("user", user);
			model.addAttribute("code", 200);
			model.addAttribute("message", "成功");
			model.addAttribute("result", true);
			return model;
		} else {
			// model.addAttribute("userLogin", false);
			model.addAttribute("code", "");
			model.addAttribute("message", "请输入正确的账户或密码");
			model.addAttribute("result", false);
			return model;
		}

	}

	/**
	 * 退出登录，同时注销session
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout") // @Param("userName") @Param("password")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "login";
	}

	/**
	 * 展示单件商品细节
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String showProductDetail(@RequestParam("id") Integer id, Model model, HttpSession session) throws Exception {

		JSONObject productObj = new JSONObject();

		productObj = onlineService.showProductDetail(id);

		model.addAttribute("product", productObj);

		return "show";
	}

	/**
	 * 跳转到产品发布页
	 * 
	 * 
	 * @return
	 */
	@RequestMapping("/public")
	public String establish() {
		return "public";
	}

	/**
	 * 
	 * 卖家提交发布的商品，并保存的本地库中
	 * 
	 * @param title
	 * @param detail
	 * @param price
	 * @param summary
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
	public String publicSubmit(@RequestParam("title") String title, @RequestParam("detail") String detail,
			@RequestParam("price") long price, @RequestParam("summary") String summary,
			@RequestParam("image") String url, Model model) {

		JSONObject jsonObj = new JSONObject();
		jsonObj = onlineService.publicSubmit(title, detail, detail, price, summary, url);
		model.addAttribute("product", jsonObj);

		return "publicSubmit";
	}

	/**
	 * 按照Id删除指定商品
	 * 
	 * @param contentId
	 * @param model
	 * @return
	 */
	@RequestMapping("/api/delete")
	public @ResponseBody Model delete(@RequestParam("id") Integer id, Model model) {
		if (onlineService.delete(id) == 1) {
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

	/**
	 * 购买商品前台传入商品id，session获取用户id参数
	 * 
	 * @param id
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/api/buy", method = RequestMethod.POST)
	public @ResponseBody Model buyProduct(Integer id, Model model, HttpSession session) {

		if (id != null) {
			onlineService.buy(id, user.getId());
			model.addAttribute("code", 200);
			model.addAttribute("message", "购买成功");
			model.addAttribute("result", true);
		} else {
			model.addAttribute("code", "");
			model.addAttribute("message", "购买失败");
			model.addAttribute("result", false);
		}
		return model;
	}

	/**
	 * @return 账务页
	 */
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model) {
		JSONArray productsArray = new JSONArray();

		productsArray = onlineService.account(user.getId());

		model.addAttribute("buyList", productsArray);

		return "account";
	}

	/**
	 * 编辑页
	 * 
	 * @param contentId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Model model) {

		model.addAttribute("product", onlineService.showProductDetail(id));

		return "edit";
	}

	/**
	 * 编辑提交
	 * 
	 * @param id
	 * @param title
	 * @param summary
	 * @param detail
	 * @param price
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
	public String editSubmit(@RequestParam("id") int id, @RequestParam("title") String title,
			@RequestParam("summary") String summary, @RequestParam("detail") String detail,
			@RequestParam("price") long price, @RequestParam("image") String url, Model model) {
		JSONObject jsonObj = new JSONObject();
		jsonObj = onlineService.publicSubmit(title, detail, detail, price, summary, url);
		model.addAttribute("product", jsonObj);
		return "editSubmit";
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
			String image = upload.getPath();
			return model.addAttribute("result", image);
		}
		model.addAttribute("code", "");
		model.addAttribute("message", "图片未上传");
		return model.addAttribute("result", null);

	}

}
