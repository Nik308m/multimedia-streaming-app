package com.Nikhil308.NikTube.Controllers;



import com.Nikhil308.NikTube.Model.MusicJSONData;
import com.Nikhil308.NikTube.Model.VideoJSONData;
import com.Nikhil308.NikTube.Repo.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.Nikhil308.NikTube.Model.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/musicpage")
public class MusicController {

    private final SongRepository dataRepository;

    @Autowired
    public MusicController(SongRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFilesm(
            @RequestParam("musicFile") MultipartFile musicFile,
            @RequestParam("mname") String mname,
            @RequestParam("mdescription") String mdescription) {

        // Handle the file upload logic here
        // You can save the files, process them, etc.
        try {
            byte[] musicContent = musicFile.getBytes();
            Song musicData = new Song((byte[])musicContent,mname,mdescription);
            dataRepository.save(musicData);
            UploadResponse response = new UploadResponse("Music File uploaded successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            UploadResponse response = new UploadResponse("Error uploading Music file: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    /* Static nested classes in Java are nested classes that are declared with the static
     modifier. They are associated with their outer class but can be instantiated independently
     of any instance of the outer class. Static nested classes can access static members of the
      outer class directly, but they cannot access instance members of the outer class without
       an instance of the outer class. */
    private static class UploadResponse {
        private final String message;

        public UploadResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }


   /* @GetMapping(value = "/musicfiles/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getMusicMediaContent(@PathVariable Long id) {
        Optional<Song> optionalMediaEntity = dataRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "music.mp3");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        if (optionalMediaEntity.isPresent()) {
            Song mediaEntity = optionalMediaEntity.get();
            return ResponseEntity.ok().headers(headers).body(mediaEntity.getContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @GetMapping(value = "/music/{query}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getMediaContentByName(@PathVariable String query) {
        Optional<Song> optionalMediaEntity = dataRepository.findByVname(query);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "video.mp4");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        if (optionalMediaEntity.isPresent()) {
            Song mediaEntity = optionalMediaEntity.get();
            return ResponseEntity.ok().headers(headers).body(mediaEntity.getContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }





    @GetMapping(value = "/ListOfMusics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MusicJSONData> getListOfMusics() {
        System.out.println(" Started to fetch List of Videos");
        List<MusicJSONData> optionalMediaEntity = dataRepository.findListOfMusics();
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDispositionFormData("attachment", "video.mp4");
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // Set content type to JSON since you're returning a list of data
        headers.setContentType(MediaType.APPLICATION_JSON);

//        if (optionalMediaEntity.isPresent()) {
//            Video mediaEntity = optionalMediaEntity.get();
//            return ResponseEntity.ok().headers(headers).body(mediaEntity.getContent());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        return optionalMediaEntity;
    }


}

/* MultipartFile is an interface in Spring Framework that represents a file uploaded as part of a multipart request in a web application. It's commonly used in web applications to handle file uploads from HTML forms. When a user submits a form containing a file input field (<input type="file">), the selected file is sent to the server as a part of a multipart request.

Here are some key points about MultipartFile:

    Purpose: MultipartFile is used to represent uploaded files in Spring MVC applications. It allows developers to access the contents of the uploaded file, its metadata (such as filename and content type), and perform operations like saving the file to the filesystem or processing its contents.

    Part of Spring's Web MVC: MultipartFile is part of Spring's web module, which provides support for building web applications using the Model-View-Controller (MVC) design pattern. It's commonly used in controllers to handle file uploads from web forms.

    Methods and Properties: The MultipartFile interface defines methods to obtain information about the uploaded file, such as getOriginalFilename() to get the original filename, getContentType() to get the MIME type of the file, getSize() to get the size of the file in bytes, and getInputStream() to obtain an InputStream to read the contents of the file. */





