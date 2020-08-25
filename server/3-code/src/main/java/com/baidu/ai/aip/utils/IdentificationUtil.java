//package com.baidu.ai.aip.utils;
//
//import com.zhenzi.sms.ZhenziSmsClient;
//import org.json.JSONObject;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * 身份证识别
// */
//public class IdentificationUtil {
//
//    /**
//     * 代码中所需工具类
//     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
//     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
//     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
//     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
//     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
//     * 下载
//     */
//    /**
//     * @return java.lang.String
//     * @Author CC
//     * @Description 通过传入的side（front/back）判断身份证的正反面；通过imgData来识别图片
//     * @Date 2020/2/20 11:21
//     * @Param [side, imgData]
//     **/
//
//    public static String idcard(String side, byte[] imgData) {
//        // 请求url
//        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
//        try {
//            // 本地文件路径
//            // String filePath = "C:\\Users\\CC\\Desktop\\idcard2.jpg";
//            // byte[] imgData = FileUtil.readFileByBytes(filePath);
//            String imgStr = Base64Util.encode(imgData);
//            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
//
//            String param = "id_card_side=" + side + "&image=" + imgParam;
//
//            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = "24.660501bd6d32fcfb9348f62f856c45ac.2592000.1590304069.282335-18519281";
//
//            String result = HttpUtil.post(url, accessToken, param);
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * @return byte[]
//     * @Author CC
//     * @Description 网络图片路径转为byte数组1
//     * @Date 2020/2/19 11:10
//     * @Param [urlPath]
//     **/
//
//    public static byte[] getImageFromURL(String urlPath) {
//        byte[] data = null;
//        InputStream is = null;
//        HttpURLConnection conn = null;
//        try {
//            URL url = new URL(urlPath);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setDoInput(true);
//            // conn.setDoOutput(true);
//            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(6000);
//            is = conn.getInputStream();
//            if (conn.getResponseCode() == 200) {
//                data = readInputStream(is);
//            } else {
//                data = null;
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            conn.disconnect();
//        }
//        return data;
//    }
//
//    /**
//     * @return byte[]
//     * @Author CC
//     * @Description 网络图片路径转为byte数组2
//     * @Date 2020/2/20 11:17
//     * @Param [is]
//     **/
//
//    public static byte[] readInputStream(InputStream is) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int length = -1;
//        try {
//            while ((length = is.read(buffer)) != -1) {
//                baos.write(buffer, 0, length);
//            }
//            baos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        byte[] data = baos.toByteArray();
//        try {
//            is.close();
//            baos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }
//
//    /**
//     * @return java.util.HashMap<java.lang.String, java.lang.String>
//     * @Author CC
//     * @Description 将百度云返回的身份证正面信息转化为合适的格式
//     * @Date 2020/2/20 14:32
//     * @Param [jsonResult]
//     **/
//
//    public static HashMap<String, String> getFrontByJson(String jsonResult) {
//        HashMap map = new HashMap<String, String>();
//        try {
//            JSONObject jsonObject = new JSONObject(jsonResult);
//            JSONObject words_result = jsonObject.getJSONObject("words_result");
//            Iterator<String> it = words_result.keys();
//            while (it.hasNext()) {
//                String key = it.next();
//                JSONObject result = words_result.getJSONObject(key);
//                String value = result.getString("words");
//                switch (key) {
//                    case "姓名":
//                        map.put("name", value);
//                        break;
//                    case "民族":
//                        map.put("nation", value);
//                        break;
//                    case "住址":
//                        map.put("address", value);
//                        break;
//                    case "公民身份号码":
//                        map.put("id", value);
//                        break;
//                    case "出生":
//                        map.put("birth", value);
//                        break;
//                    case "性别":
//                        map.put("sex", value);
//                        break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//
//    /**
//     * @return java.util.HashMap<java.lang.String, java.lang.String>
//     * @Author CC
//     * @Description 将百度云返回的身份证背面信息转化为合适的格式
//     * @Date 2020/2/20 14:33
//     * @Param [jsonResult]
//     **/
//
//    public static HashMap<String, String> getBackByJson(String jsonResult) {
//        HashMap map = new HashMap<String, String>();
//        try {
//            JSONObject jsonObject = new JSONObject(jsonResult);
//            JSONObject words_result = jsonObject.getJSONObject("words_result");
//            Iterator<String> it = words_result.keys();
//            while (it.hasNext()) {
//                String key = it.next();
//                JSONObject result = words_result.getJSONObject(key);
//                System.out.println(result);
//                String value = result.getString("words");
//                switch (key) {
//                    case "签发机关":
//                        map.put("office", value);
//                        break;
//                    case "签发日期":
//                        map.put("exp1", value);
//                        break;
//                    case "失效日期":
//                        map.put("exp2", value);
//                        break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return map;
//    }
//
////    /**
////     * @return java.util.HashMap<java.lang.String, java.lang.String>
////     * @Author CC
////     * @Description 将SMS接口返回的信息转化为合适的格式
////     * @Date 2020/2/23 14:44
////     * @Param [jsonResult]
////     **/
////
////    public static HashMap<String, String> getSmsByJson(String jsonResult) {
////        HashMap map = new HashMap<String, String>();
////        try {
////            JSONObject jsonObject = new JSONObject(jsonResult);
////            JSONObject words_result = jsonObject.getJSONObject("words_result");
////            Iterator<String> it = words_result.keys();
////            while (it.hasNext()) {
////                String key = it.next();
////                JSONObject result = words_result.getJSONObject(key);
////                String value = result.getString("words");
////                switch (key) {
////                    case "code":
////                        map.put("code1", value);
////                        break;
////                    case "data":
////                        map.put("data1", value);
////                        break;
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return map;
////    }
////
////    /**
////     * @return java.util.HashMap<java.lang.String, java.lang.String>
////     * @Author CC
////     * @Description 将SMS接口返回的套餐剩余数量信息转化为合适的格式
////     * @Date 2020/2/23 14:36
////     * @Param [jsonResult]
////     **/
////
////    public static HashMap<String, String> getSmsNumByJson(String jsonResult) {
////        HashMap map = new HashMap<String, String>();
////        try {
////            JSONObject jsonObject = new JSONObject(jsonResult);
////            JSONObject words_result = jsonObject.getJSONObject("words_result");
////            Iterator<String> it = words_result.keys();
////            while (it.hasNext()) {
////                String key = it.next();
////                JSONObject result = words_result.getJSONObject(key);
////                String value = result.getString("words");
////                switch (key) {
////                    case "code":
////                        map.put("code", value);
////                        break;
////                    case "data":
////                        map.put("balance", value);
////                        break;
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return map;
////    }
//
//
//    public static void main(String[] args) {
//        //  此代码可直接获取token
//          AuthService.getAuth();
//
//        //  测试图片
//        //  正面：https://ai.bdstatic.com/file/D348C98B677149C8B95909CFA204BA1C
//        //  反面：https://ai.bdstatic.com/file/75537E3E7EE8446EBDCB35B5CEDEB9B6
//
//        //  短信验证码发送测试方法开始
////        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "104584", "aca1366a-d551-4e8a-80fa-1292cf4fd6b6");
////        Map<String, String> params = new HashMap<String, String>();
////        params.put("message", "验证码为: 112356");
////        params.put("number", "15047057120");
////        try {
////            String result = client.send(params);
////            System.out.println(result);
////        } catch (Exception e) {
////        }
//        //  短信验证码发送测试方法结束
//
//    }
//}