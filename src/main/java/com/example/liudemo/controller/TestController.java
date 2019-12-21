package com.example.liudemo.controller;

import com.alibaba.fastjson.JSON;
import com.example.liudemo.models.User;
import io.ebean.EbeanServer;
import io.ebean.SqlRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author lzp
 * @version V1.0
 * @Classname TestController
 * @Date 2019/10/31 8:43
 */
@Controller
public class TestController {

    @Autowired
    private EbeanServer ebeanServer;

    @ResponseBody
    @RequestMapping("/hello")
    public String helloWorld(){
        return "liu,hello";
    }

    @ResponseBody
    @RequestMapping("/git")
    public String queryGitHub() {
        try {
            URL url = new URL("https://api.github.com/users/Vitaminliu/repos");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true); // 设置该连接是可以输出的
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) { // 读取数据
                result.append(line + "\n");
            }
            connection.disconnect();

            System.out.println(result.toString());
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/Oauth")
    public void Oauthtest(@RequestParam("code") String code){

        System.out.println(code);
    }

    @ResponseBody
    @RequestMapping("/testEbean")
    public void testEbean(){
        List<SqlRow> list = ebeanServer.createSqlQuery("select * from  user").findList();
        System.out.println(list.size());
    }

    @ResponseBody
    @RequestMapping("/testentity")
    public void testentity(){
        List<User> users = ebeanServer.createQuery(User.class).findList();
        System.out.println(users.size());
    }

    @ResponseBody
    @GetMapping("/queryUser")
    public List<User> queryUser(@RequestParam("userid") String userId){
        List<User> users = ebeanServer.createQuery(User.class).where().eq("slackUserId",userId).findList();
        return users;
    }




    @ResponseBody
    @PostMapping("/saveuser")
    public void testEbean(@RequestBody String  jsonstr){
        Map<String, Object> paramMap = JSON.parseObject(jsonstr, Map.class);
//        String githubReposUrl = (String)paramMap.get("githubReposUrl");
//        String githubToken = (String)paramMap.get("githubToken");
//        String githubUserName = (String)paramMap.get("githubUserName");
//        String slackUserId = (String)paramMap.get("slackUserId");
//        String slackUserName = (String)paramMap.get("slackUserName");

//        List<User> users = ebeanServer.createQuery(User.class).findList();
//        System.out.println(users);

        User user = new User();
        user.setGithubReposUrl((String)paramMap.get("githubReposUrl"));
        user.setGithubToken((String)paramMap.get("githubToken"));
        user.setGithubUserName((String)paramMap.get("githubUserName"));
        user.setSlackUserId((String)paramMap.get("slackUserId"));
        user.setSlackUserName((String)paramMap.get("slackUserName"));
        user.setId(UUID.randomUUID().toString());
        ebeanServer.save(user);
    }

}
