package com.Nikhil308.NikTube.Model;


import jakarta.persistence.*;

import java.sql.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/* While the @Entity annotation is necessary for a class to be treated as a database table,
the @Table annotation is not. If a class is annotated with @Entity but not @Table, the class
name will be used as the default table name. However, using both annotations in conjunction
allows for more flexibility */
@Entity//help us to map our domain objects (POJOs) to the relational database tables
@Table(name = "songs_data")
public class Song {

   /*The @Id annotation can be applied to a member of a class to designate that this member
     will uniquely identify the entity in the database.*/

    /* @GeneratedValue GenerationType. IDENTITY: This strategy relies on the auto-increment
    functionality provided by the database to generate unique identifier values automatically.
    GenerationType. SEQUENCE: With this strategy, the @GeneratedValue annotation fetches unique
    identifier values from a predefined sequence generator.  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /* We can store images or files in our database by converting them into large binary objects.
     The @Lob annotation indicates that the property should be stored in the database in the form
     of a large object type in the database. */


    /* use the @Column annotation to mention the details of a column in the table.

The @Column annotation has many elements such as name, length, nullable, and unique:*/
  // @Lob
    //@Column(name = "content")//@Column(name = "content", columnDefinition = "byte[]")
   @Column(name = "content", columnDefinition = "bytea")
   private byte[] content;

    // Add other fields as needed
    @Column(name = "vname")
    private String vname;

    @Column(name = "vdescription")
    private String vdescription;



    public Song() {
    }

    public Song(byte[] content,String dname, String ddescription) {
        this.vname=dname;
        this.vdescription=ddescription;
        this.content = content;
    }

    public Song(byte[] content,String dname, String ddescription,long id) {
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
