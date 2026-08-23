package com.Nikhil308.NikTube.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.Nikhil308.NikTube.Model.*;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<Song, Long>{

/* The Optional class in Java 8 is a container object which is used to contain a value that
might or might not be present. It was introduced as a way to help reduce the number of
NullPointerExceptions that occur in Java code. */
    Optional<Song> findByVname(String query);

   default  public List<MusicJSONData> findListOfMusics(){
       List<MusicJSONData> listobj1= new ArrayList<MusicJSONData>();

       Connection connection = null;
       Statement statement = null;
       ResultSet resultSet = null;
       try {
        /*    //Dynamically load the MySQL JDBC driver
           Class.forName("com.mysql.cj.jdbc.Driver");
           // Establish connection for MySQL Database
           String url = "jdbc:mysql://localhost:3306/niktube";
           String username = "root";
           String password = "#Bigguy1";*/

           Class.forName("org.postgresql.Driver");
            //Establish connection for PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/NikTube";
            String username = "postgres";
            String password = "#Bigguy1";

           connection = DriverManager.getConnection(url, username, password);

           System.out.println("Connected to PostgreSQL database!");




           // Perform database operations here...
           String query1 = "SELECT * FROM songs_data";
           statement = connection.createStatement();
           resultSet=statement.executeQuery(query1);
           while(resultSet.next()) {
               int vcounts=0;
               long id = resultSet.getInt("id");

               String name = resultSet.getString("vname");
               String descrip = resultSet.getString("vdescription");
               String contentLink ="http://localhost:8080/music/"+name ;

               // Make Object
               MusicJSONData obj = new   MusicJSONData(id, name, descrip,contentLink);
               System.out.println(++vcounts);
               listobj1.add(obj);

           }

       } catch (ClassNotFoundException e) {
           System.err.println("MySQL JDBC driver not found!");
           e.printStackTrace();
       } catch (SQLException e) {
           System.err.println("Failed to connect to MySQL database!");
           e.printStackTrace();
       } finally {
           // Close the connection
           try {
               if (connection != null) {
                   connection.close();
                   System.out.println("Connection closed.");
               }
           }
           catch (SQLException e) {
               System.err.println("Error while closing connection!");
               e.printStackTrace();
           }
       }
       return listobj1;
   }



}
