package com.blog;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.repository.RoleRepo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
       try {
    	   Role role=new Role();
    	   role.setId(AppConstants.ADMIN_USER);
    	    role.setName("ADMIN_USER");
    	    
    	    Role role1=new Role();
    	    role1.setId(AppConstants.NORMAL_USER);
    	    role1.setName("NORMAL_USER");
    	    List<Role>roles=List.of(role,role1);
    	    List<Role>result=roleRepo.saveAll(roles);
    	    
    	    result.forEach(r->{System.out.println(r.getName());
    	    });
       }catch(Exception e){
    	   e.printStackTrace();
       }
		
	}

}
