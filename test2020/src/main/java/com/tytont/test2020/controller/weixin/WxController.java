package com.tytont.test2020.controller.weixin;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tytont.test2020.controller.BaseController;
import com.tytont.test2020.model.WxUser;
import com.tytont.test2020.mvc.ResponseInfo;
import com.tytont.test2020.service.WxUserService;
import com.tytont.test2020.spring.Constants;
import com.tytont.test2020.utils.weixin.SysInitBean;

@RestController
@RequestMapping("/wx")
public class WxController extends BaseController {

	@Autowired
	private WxUserService wxUserService;

	@PostMapping("/auth")
	public ResponseInfo auth(String nickName, String code, String encryptedData, String iv, HttpServletRequest request) throws Exception {
		if (StringUtils.isNotBlank(code)) {
			Object succesResponse = JSON.parse(sendPost(code)); // 先转换成Object
			Map<String, Object> map = (Map<String, Object>) succesResponse; // Object强转换为Map

			if (StringUtils.isNotBlank((String) map.get("openid"))) {
				HttpSession session = request.getSession();
				String openid = (String) map.get("openid");
				QueryWrapper<WxUser> queryWrapper = new QueryWrapper<WxUser>();
				queryWrapper.eq(WxUser.OPENID, openid);
				WxUser user = wxUserService.getOne(queryWrapper);
				if (user == null) {
					user = new WxUser();
					user.setOpenid(openid);
					user.setNickname(nickName);
					user.setCreateTime(new Date());
					user.setFollowStatus(0);
					user.setBindStatus(0);
					wxUserService.save(user);
				}
				session.setAttribute(Constants.SESSION_WX_USER, user);
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("SESSIONID", session.getId());
				map2.put(Constants.SESSION_WX_USER, user);
				return ResponseInfo.success("", map2);

			}
		}

		return ResponseInfo.fail("code为空");
	}

	@RequestMapping("/uploadPicture")
	public ResponseInfo uploadPicture(String sortPath, MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		File targetFile = null;
		String url = "";
		String fileName = file.getOriginalFilename();
		if (fileName != null && fileName != "") {
			String path = request.getSession().getServletContext().getRealPath("upload/") + sortPath; //文件存储位置
			String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
			fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;//新的文件名

			File file1 = new File(path);
			if (!file1.exists() && !file1.isDirectory()) {
				file1.mkdir();
			}
			targetFile = new File(file1, fileName);
			try {
				file.transferTo(targetFile);
				url = "upload/" + sortPath + "/" + fileName;
				return ResponseInfo.success("图片上传成功", url);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseInfo.fail("系统异常，图片上传失败");
			}
		}
		return ResponseInfo.fail("抱歉，图片上传不能为空");

	}

	@RequestMapping("/deletePicture")
	public ResponseInfo deletePicture(String picturePath, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(picturePath)) {
			return ResponseInfo.fail("删除的图片不能为空");
		}
		String path = request.getSession().getServletContext().getRealPath("") + picturePath;
		File file1 = new File(path);
		if (file1.exists()) {
			file1.delete();
			return ResponseInfo.success("图片删除成功");
		}
		return ResponseInfo.fail("图片删除失败");

	}

	/**
	 * 获取openid
	 * 
	 * @param code
	 * @return 所代表远程资源的响应结果
	 */
	private String sendPost(String code) {
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + SysInitBean.xcxAppId + "&secret=" + SysInitBean.secret + "&js_code=" + code + "&grant_type=authorization_code";
		String result = "";
		BufferedReader in = null;
		InputStream is = null;
		InputStreamReader isr = null;
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.connect();
			// Map<String, List<String>> map = conn.getHeaderFields();
			is = conn.getInputStream();
			isr = new InputStreamReader(is);
			in = new BufferedReader(isr);
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
				if (is != null) {
					is.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (Exception e2) {
				// 异常记录
			}
		}
		return result;
	}

}
