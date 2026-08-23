package com.Nikhil308.NikTube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;




/* Annotation Interface SpringBootApplication. Indicates a configuration class that declares
 one or more @Bean methods and also triggers auto-configuration and component scanning .
 This is a convenience annotation that is equivalent to declaring @SpringBootConfiguration ,
  @EnableAutoConfiguration and @ComponentScan  */



/* @EntityScan annotationis used when entity classes are not placed in the main application
package or its sub-packages. In this situation, we would declare the package or list of
packages in the main configuration class within @EntityScan annotation. This will tell
Spring where to find entities used in our application:  */
@SpringBootApplication
@EntityScan(basePackages = "com.Nikhil308.NikTube")
public class NikTubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NikTubeApplication.class, args);
	}

}
