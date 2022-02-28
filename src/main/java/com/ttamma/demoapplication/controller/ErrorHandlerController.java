package com.ttamma.demoapplication.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandlerController implements ErrorController {
    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        return String.format(
                "<html><body><h2>Error page</h2><div>Status code: <b>%s</b></div>"
                + "<div>Exception Message: <b>%s</b></div></body></html>",
                statusCode, exception == null ? "N/A" : exception.getMessage());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
