//package com.briup.apps.cms.web.controller;
//
//import io.swagger.annotations.ApiOperation;
//import org.apache.oltu.oauth2.client.OAuthClient;
//import org.apache.oltu.oauth2.client.URLConnectionClient;
//import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
//import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
//import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
//import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
//import org.apache.oltu.oauth2.common.OAuth;
//import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
//import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
//import org.apache.oltu.oauth2.common.message.types.GrantType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @Author CC
// * @Description Oauth2登录客户端测试页面
// * @Date 2020/3/4 15:17
// * @Param
// * @return
// **/
//
//
////@Api(description = "登录管理")
////@RestController
////@RequestMapping("/server")
//
//public class Oauth2ServerController {
//    String clientId = null;
//    String clientSecret = null;
//    String accessTokenUrl = null;
//    String userInfoUrl = null;
//    String redirectUrl = null;
//    String response_type = null;
//    String code = null;
//
//    @ApiOperation(value = "提交申请code的请求")
//    @PostMapping("/requestServerCode")
//    public String requestServerFirst(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr) throws OAuthProblemException {
//        clientId = "clientId";
//        clientSecret = "clientSecret";
//        accessTokenUrl = "http://localhost:8081/manager/login/responseCode";//是服务端返回code的controller方法映射地址
//        redirectUrl = "http://localhost:8081/oauthclient01/server/callbackCode";//告诉服务端，code要传回客户端的一个controller方法，该方法的映射地址就是redirectUrl。
//        response_type = "code";
//        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//        String requestUrl = null;
//        try {
//            //构建oauthd的请求。设置请求服务地址（accessTokenUrl）、clientId、response_type、redirectUrl
//            OAuthClientRequest accessTokenRequest = OAuthClientRequest
//                    .authorizationLocation(accessTokenUrl)
//                    .setResponseType(response_type)
//                    .setClientId(clientId)
//                    .setRedirectURI(redirectUrl)
//                    .buildQueryMessage();
//            requestUrl = accessTokenRequest.getLocationUri();
//            System.out.println(requestUrl);
//        } catch (OAuthSystemException e) {
//            e.printStackTrace();
//        }
//        return "redirect:http://localhost:8081/oauthserver/" + requestUrl;
//    }
//
//    @ApiOperation(value = "接受客户端返回的code，提交申请access token的请求")
//    @PostMapping("/callbackCode")
//    public Object toLogin(HttpServletRequest request) throws OAuthProblemException {
//        System.out.println("-----------客户端/callbackCode--------------------------------------------------------------------------------");
//        clientId = "clientId";
//        clientSecret = "clientSecret";
//        accessTokenUrl = "http://localhost:8081/manager/login/responseAccessToken";
//        userInfoUrl = "userInfoUrl";
//        redirectUrl = "http://localhost:8099/server/accessToken";
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
////        code = httpRequest.getParameter("code");
//        // code = 第二个接口的返回值
//        code = "authorizationCode";
//        System.out.println(code);
//        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//        try {
//            OAuthClientRequest accessTokenRequest = OAuthClientRequest
//                    .tokenLocation(accessTokenUrl)
//                    .setGrantType(GrantType.AUTHORIZATION_CODE)
//                    .setClientId(clientId)
//                    .setClientSecret(clientSecret)
//                    .setCode(code)
//                    .setRedirectURI(redirectUrl)
//                    .buildQueryMessage();
//            //去服务端请求access token，并返回响应
//            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
//            //获取服务端返回过来的access token
//            String accessToken = oAuthResponse.getAccessToken();
//            //查看access token是否过期
//            Long expiresIn = oAuthResponse.getExpiresIn();
//            System.out.println("客户端/callbackCode方法的token：" + accessToken);
//            System.out.println("-----------客户端/callbackCode--------------------------------------------------------------------------------");
//            return "redirect:http://localhost:8081/oauthclient01/server/accessToken?accessToken=" + accessToken;
//        } catch (OAuthSystemException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @ApiOperation(value = "接受服务端传回来的access token，由此token去请求服务端的资源")
//    @PostMapping("/accessToken")
//    public ModelAndView accessToken(String accessToken) {
//        System.out.println("---------客户端/accessToken----------------------------------------------------------------------------------");
//        userInfoUrl = "http://localhost:8081/manager/login/userInfo";
//        System.out.println("accessToken");
//        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
//        try {
//            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userInfoUrl)
//                    .setAccessToken(accessToken).buildQueryMessage();
//            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
//            String username = resourceResponse.getBody();
//            System.out.println(username);
//            ModelAndView modelAndView = new ModelAndView("usernamePage");
//            modelAndView.addObject("username", username);
//            System.out.println("---------客户端/accessToken----------------------------------------------------------------------------------");
//            return modelAndView;
//        } catch (OAuthSystemException e) {
//            e.printStackTrace();
//        } catch (OAuthProblemException e) {
//            e.printStackTrace();
//        }
//        System.out.println("---------客户端/accessToken----------------------------------------------------------------------------------");
//        return null;
//    }
//}
