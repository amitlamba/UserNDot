package com.userndot.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.userndot.exception.UserNDotException;

@Named
public class RequestHelper {
	/**
     * Extracts a parameter value from supplied request
     * 
     * @param request
     * @param parameterName
     * @param logicalName
     * @param validate
     * @return
     */
    public String extractRequestParamerter(HttpServletRequest request, String parameterName, String logicalName, boolean validate) {
        String parameterValue = request.getParameter(parameterName);
        if (!validate)
            return parameterValue;

        if (StringUtils.isBlank(parameterValue)) {
            String name = logicalName != null ? logicalName : parameterName;
            throw new UserNDotException(name + " not Supplied or is Empty");
        }


        parameterValue = parameterValue.trim();
        return parameterValue;
    }



    /**
     * Gets Request Body
     * 
     * @param request
     * @return
     */
    public String getRequestBodyAsStringLine(HttpServletRequest request) {
        ServletInputStream inputStream = null;

        try {
            inputStream = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            return reader.readLine();
        } catch (IOException e) {
            throw new UserNDotException("Unable to Read Request Body", e);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                // unable to close stream - log and continue the good work
            }
        }
    }



    /**
     * Identifies if a request was an Ajax request or not
     * 
     * @param request
     * @return
     */
    public boolean isAjax(HttpServletRequest request) {
        String xRequestWith = request.getHeader("x-requested-with");
        if (xRequestWith != null && "XMLHttpRequest".equalsIgnoreCase(xRequestWith))
            return true;

        // TODO: This may not work with old browsers

        return false;
    }



    /**
     * Gets a map of all request headers
     * 
     * @param request
     * @return
     */
    public Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<String, String>();

        if (request == null)
            return headers;

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerVal = request.getHeader(headerName);

            headers.put(headerName, headerVal);
        }


        return headers;
    }




    /**
     * Gets specified request parameter as a boolean value
     * 
     * @param request
     * @param paramName
     * @return
     */
    public boolean getRequestParamAsBoolean(HttpServletRequest request, String paramName) {
        String paramValue = request.getParameter(paramName);
        if (paramValue == null)
            throw new UserNDotException("Param '" + paramName + "' Not found in request");

        return Boolean.valueOf(paramValue);
    }




    /**
     * Gets specified request parameter as a boolean value
     * 
     * @param request
     * @param paramName
     * @return
     */
    public int getRequestParamAsInteger(HttpServletRequest request, String paramName) {
        String paramValue = request.getParameter(paramName);
        if (paramValue == null)
            throw new UserNDotException("Param '" + paramName + "' Not found in request");

        try {
            return Integer.parseInt(paramValue);
        } catch (NumberFormatException nfe) {
            throw new UserNDotException("Param '" + paramName + "' does not have an integer value(" + paramValue + ")");
        }
    }


    /**
     * Gets request parameters
     * 
     * @param request
     * @return
     */
    public Map<String, String> getRequestParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        if (request == null)
            return params;

        @SuppressWarnings("unchecked")
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramVal = request.getParameter(paramName);

            params.put(paramName, paramVal);
        }

        return params;
    }
}
