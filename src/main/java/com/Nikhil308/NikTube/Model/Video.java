package com.Nikhil308.NikTube.Model;

import jakarta.persistence.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "videos_data")
public class Video {


    //PostgreSQL uses SERIAL for auto-incrementing columns, whereas MySQL uses AUTO_INCREMENT.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @Lob
   // @Column(name = "content")// @Column(name = "content", columnDefinition = "byte[]")
    @Column(name = "content", columnDefinition = "bytea")
    private byte[] content;

    // Add other fields as needed
    @Column(name = "vname")
    private String vname;

    @Column(name = "vdescription")
    private String vdescription;



    public Video() {
    }

    public Video(byte[] content,String dname, String ddescription) {
        this.vname=dname;
        this.vdescription=ddescription;
        this.content = content;
    }

    public Video(byte[] content,String dname, String ddescription,long id) {
        this.vname=dname;
        this.vdescription=ddescription;
        this.content = content;
        this.id=id;
    }

    // Getters and setters
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }


    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVdescription() {
        return vdescription;
    }

    public void setVdescription(String vdescription) {
        this.vdescription = vdescription;
    }


}
