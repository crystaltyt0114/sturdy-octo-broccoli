package com.tytont.test2020.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.tytont.test2020.mvc.ResponseInfo;

// @Controller
public class GlobalErrorController implements ErrorController {

	private static final String ERROR_PATH = "error";
	ErrorAttributes errorAttributes;

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	@Autowired
	public GlobalErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	/**
	 * JSON格式异常处理
	 */
	@RequestMapping(value = ERROR_PATH)
	@ResponseBody
	public ResponseInfo errorApiHandler(HttpServletRequest request, HttpServletResponse response) {
		ServletWebRequest requestAttributes = new ServletWebRequest(request);
		int status = getStatus(request);
		Throwable th = (Throwable) requestAttributes.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR", RequestAttributes.SCOPE_REQUEST);
		if (th instanceof UnknownAccountException) {
			return ResponseInfo.fail("请登录");
		}
		String msg = "";
		if (th instanceof UnauthorizedException) {
			msg = "您没有权限访问";
		}
		msg = th.getMessage();
		// response.setStatus(status);
		return ResponseInfo.fail(msg);
	}

	private int getStatus(HttpServletRequest request) {
		Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (status != null) {
			return status;
		}
		return 500;
	}

	/**
	 * 页面错误异常处理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = ERROR_PATH, produces = "text/html")
	public String errorPageHandler(HttpServletRequest request, HttpServletResponse response, Model model) {
		ServletWebRequest requestAttributes = new ServletWebRequest(request);
		Throwable th = (Throwable) requestAttributes.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR", RequestAttributes.SCOPE_REQUEST);
		if (th instanceof UnknownAccountException) {
			return "redirect:/login";
		}
		if (th instanceof UnauthorizedException) {
			return "redirect:/pub/noPrivilage";
		}
		int status = response.getStatus();
		model.addAttribute("msg", th.getMessage());
		model.addAttribute("status", status);
		//		if(th instanceof BusinessException || th instanceof RuntimeException || th instanceof Exception) {}
		return "admin/errors";
	}

}
