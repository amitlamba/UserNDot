package com.userndot.controller;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.userndot.common.utils.RequestHelper;

public abstract class BaseController {
	
	@Named
	RequestHelper requestHelper;

	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.error("Exception Occured ", ex);
		logger.error("Request URL: " + request.getRequestURL());

		// dump stack trace on the page as hidden element
		StringBuffer errorDetailsBuffer = new StringBuffer();
		StackTraceElement[] stackTrace = ex.getStackTrace();
		for (StackTraceElement stackTraceElement : stackTrace) {
			errorDetailsBuffer.append("\n" + stackTraceElement.toString());
		}

		request.setAttribute("errorDetails", errorDetailsBuffer.toString());
		if (ex != null) {
			request.setAttribute("error", ExceptionUtils.getRootCauseMessage(ex));
		}

		ModelMap map = new ModelMap();
		boolean isAjax = requestHelper.isAjax(request);
		if (isAjax) {
			return handleAJaxException(ex, map, request, response);
		}
        else {
			return handleNonAjaxException(map);
		}
	}

	private ModelAndView handleNonAjaxException(ModelMap map) {
        map.put("pageTitle", "Some error occurred");
        return getModelAndView("@serverError", map);
	}

	private ModelAndView handleAJaxException(Exception ex, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		String message = ex.getMessage();
		if (StringUtils.isNotBlank(message)) {
			int index = message.indexOf("Details");
			if(index != -1){
				message = message.substring(0, index);
			}
			map.put("error", message);
		}
		return getModelAndView("@serverError", map);
	}

    protected ModelAndView getModelAndView(String viewName) {
        return getModelAndView(viewName, "und", new ModelMap());
    }

	protected ModelAndView getModelAndView(String viewName, ModelMap modelMap) {
		return getModelAndView(viewName, "und", modelMap);
	}

	protected ModelAndView getModelAndView(String viewName, String partnerId, ModelMap modelMap) {
		if (StringUtils.isNotBlank(partnerId))
			viewName = viewName + "-" + partnerId;

		ModelAndView mav = new ModelAndView(viewName);
		mav.addAllObjects(modelMap);
		return mav;
	}

	protected ModelAndView getModelAndViewForForward(String action,	ModelMap modelMap) {
		action = "forward:" + action;
		ModelAndView mav = new ModelAndView(action, modelMap);
		mav.addAllObjects(modelMap);
		return mav;
	}

	protected ModelAndView getModelAndViewRedirect(String action) {
		action = "redirect:" + action;
		ModelAndView mav = new ModelAndView(action);
		return mav;
	}

}
