package com.Nikhil308.NikTube.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/* @Controller is used to declare common web controllers which can return HTTP response but
 @RestController is used to create controllers for REST APIs which can return JSON  */



/* The @RequestMapping annotation is used to map requests to controllers methods. It has
various attributes to match by URL, HTTP method, request parameters, headers, and media types.
You can use it at the class level to express shared mappings or at the method level to narrow
down to a specific endpoint mapping. */
@Controller
@RequestMapping("/")
public class MainController {
    public MainController() {  }

    @RequestMapping("/home")
    String  homepage(){

        return "NikVideoPlay.jsp";
    }
    @RequestMapping("/streamvideo")
    String  streamVideo(){

        return "StreamVideo.jsp";
    }

    @RequestMapping("/streammusic")
    String  streamMusic(){

        return "StreamMusic.jsp";
    }

    @RequestMapping("/uploadmusic")
    String  uploadMusic(){

        return "UploadMusic.jsp";
    }

    @RequestMapping("/uploadvideo")
    String  uploadVideo(){

        return "UploadVideo.jsp";
    }



    @RequestMapping("/ListVideos")
    String  ListVideo(){

        return "ListVideos.jsp";
    }

    @RequestMapping("/ListMusics")
    String  ListMusic(){

        return "ListMusics.jsp";
    }

}
