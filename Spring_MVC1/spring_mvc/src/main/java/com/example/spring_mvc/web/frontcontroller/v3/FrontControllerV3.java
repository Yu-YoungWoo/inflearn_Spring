package com.example.spring_mvc.web.frontcontroller.v3;

import com.example.spring_mvc.web.frontcontroller.ModelView;
import com.example.spring_mvc.web.frontcontroller.MyView;
import com.example.spring_mvc.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.spring_mvc.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.spring_mvc.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // interface의 다형성에 의해 부모는 자식의 타입을 받을 수 있다.
        ControllerV3 controller = controllerMap.get(requestURI);

        if(controller == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }


        Map<String, String> paramMap = createParamMap(request);

        // Map<String, String> 타입의 쿼리를 Controller에게 넘겨주고 ModelView 타입으로 반환 받는다.
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();

        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);

    }


    // 동적인 view 이름을 폴더 경로로 바꿔줌
    public MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    public Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }
}
