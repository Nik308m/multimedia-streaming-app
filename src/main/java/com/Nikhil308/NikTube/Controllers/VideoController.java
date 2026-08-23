package com.Nikhil308.NikTube.Controllers;



import com.Nikhil308.NikTube.Model.VideoJSONData;
import com.Nikhil308.NikTube.Repo.VideoRepository;
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
@RequestMapping("/videopage")
public class VideoController {

    private final VideoRepository dataRepository;

    @Autowired
    public VideoController(VideoRepository dataRepository) {
        this.dataRepository = dataRepository;
    }


    @PostMapping("/upload")
    public ResponseEntity<UploadResponse> uploadFilesv(
            @RequestParam("videoFile") MultipartFile videoFile,
            @RequestParam("vname") String vname,
            @RequestParam("vdescription") String vdescription) {
        // Handle the file upload logic here
        // You can save the files, process them, etc.
        try {
            byte[] videoContent = videoFile.getBytes();
            Video videoData = new Video((byte[])videoContent,vname,vdescription);
            dataRepository.save(videoData);

            UploadResponse response = new UploadResponse("Video File uploaded successfully!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
           UploadResponse response = new UploadResponse("Error uploading Video file: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static class UploadResponse {
        private final String message;

        public UploadResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }


   /* @GetMapping(value = "/videofiles/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getMediaContentById(@PathVariable Long id) {
        Optional<Video> optionalMediaEntity = dataRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "video.mp4");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        if (optionalMediaEntity.isPresent()) {
            Video mediaEntity = optionalMediaEntity.get();
            return ResponseEntity.ok().headers(headers).body(mediaEntity.getContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/



    @GetMapping(value = "/video/{query}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getMediaContentByName(@PathVariable String query) {
        Optional<Video> optionalMediaEntity = dataRepository.findByVname(query);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "video.mp4");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        if (optionalMediaEntity.isPresent()) {
            Video mediaEntity = optionalMediaEntity.get();
            return ResponseEntity.ok().headers(headers).body(mediaEntity.getContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping(value = "/ListOfVideos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VideoJSONData> getListOfVideos() {
        System.out.println(" Started to fetch List of Videos");
        List<VideoJSONData> optionalMediaEntity = dataRepository.findListOfVideos();
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

