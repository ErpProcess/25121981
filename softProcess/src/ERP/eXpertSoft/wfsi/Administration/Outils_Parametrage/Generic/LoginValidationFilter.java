package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginValidationFilter implements Filter {

    private String loginPage = "";
    private List<String> excludedURLs;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.loginPage = filterConfig.getInitParameter("loginPage");

        String[] excluded = filterConfig.getInitParameter("excludedURLs").split(";");
        excludedURLs = new ArrayList<String>();
        for (int i = 0; i < excluded.length; i++) {
            excludedURLs.add(excluded[i]);
        }
    }

    public void destroy() {
        this.loginPage = "";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,
            ServletException {

        HttpServletRequest httpRequest = request instanceof HttpServletRequest ? (HttpServletRequest) request : null;
        HttpServletResponse httpResponse = response instanceof HttpServletResponse ? (HttpServletResponse) response
                : null;

        if (httpRequest == null || httpResponse == null) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean isExcludedURL = false;
        

        if (isExcludedURL) {
            filterChain.doFilter(request, response);
        } else {
            
        }
    }
}