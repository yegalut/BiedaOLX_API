package com.example.biedaolx_api;

import com.example.biedaolx_api.dto.CreatePostDto;
import com.example.biedaolx_api.dto.CreateUserDto;
import com.example.biedaolx_api.dto.SendMessageDto;
import com.example.biedaolx_api.entity.Post;
import com.example.biedaolx_api.entity.PostCategory;
import com.example.biedaolx_api.entity.Role;
import com.example.biedaolx_api.entity.User;
import com.example.biedaolx_api.service.PostCategoryService;
import com.example.biedaolx_api.service.RoleService;
import com.example.biedaolx_api.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class BiedaOlxApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiedaOlxApiApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }





    CommandLineRunner run(UserService userService, RoleService roleService, PostCategoryService postCategoryService, PasswordEncoder passwordEncoder){
        return args -> {
          /*  roleService.addRole(new Role(1L, "ROLE_USER"));
            roleService.addRole(new Role(2L, "ROLE_ADMIN"));
*/
            userService.addUser(new CreateUserDto( "Patryk", "patkmi", "1234"));
 /*           userService.addUser(new CreateUserDto("Kamil", "kamlip","12345"));
            userService.addUser(new CreateUserDto( "Asia", "asikul","123456"));
            userService.addUser(new CreateUserDto( "Kasia", "kasmir","1234567"));

            postCategoryService.addPostCategory( new PostCategory("Cars"));
            postCategoryService.addPostCategory( new PostCategory("Tools"));
            postCategoryService.addPostCategory( new PostCategory("Clothes"));

            userService.addPost("patkmi", new CreatePostDto("title 1"," body1", 123,"Cars"));
            userService.addPost("patkmi", new CreatePostDto("title 2"," body2",123,"Cars"));
            userService.addPost("patkmi", new CreatePostDto("title 3"," body3",345,"Cars"));
            userService.addPost("patkmi", new CreatePostDto("title 4"," body4",345,"Cars"));
            userService.addPost("patkmi", new CreatePostDto("title 5"," body5",35,"Tools"));
            userService.addPost("kamlip", new CreatePostDto("title 6"," body6",345,"Tools"));
            userService.addPost("kamlip", new CreatePostDto("title 7"," body7",123,"Clothes"));
            userService.addPost("kamlip", new CreatePostDto("title 8"," body8",23462,"Cars"));


            userService.sendMessage("kamlip", new SendMessageDto("test message2qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq", "patkmi"));
            userService.sendMessage("patkmi", new SendMessageDto("test message3ahskfhasdkjlfhkashdfio hqouwibefouyhqbwefuhbeijqwbfqwebfijqwbefjbqiwefbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", "kamlip"));
            userService.sendMessage("kamlip", new SendMessageDto("test messagaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaae4", "patkmi"));
            userService.sendMessage("patkmi", new SendMessageDto("test messageeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee5", "kamlip"));
            userService.sendMessage("patkmi", new SendMessageDto("test message6", "kamlip"));
            userService.sendMessage("kamlip", new SendMessageDto("test message7", "patkmi" ));
            userService.sendMessage("kamlip", new SendMessageDto("test message8", "patkmi" ));
            userService.sendMessage("kamlip", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("kamlip", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("kamlip", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("kamlip", new SendMessageDto("test message9", "patkmi" ));

            userService.sendMessage("asikul", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("asikul", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("kasmir", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("kasmir", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("kasmir", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("kasmir", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("asikul", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("asikul", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("asikul", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("kasmir", new SendMessageDto("test message9", "patkmi" ));
            userService.sendMessage("kamlip", new SendMessageDto("test message9", "patkmi" ));



*/
 //           userService.addRoleToUser("patkmi", "ROLE_USER");
            userService.addRoleToUser("patkmi", "ROLE_ADMIN");
/*            userService.addRoleToUser("kamlip", "ROLE_USER");
            userService.addRoleToUser("asikul", "ROLE_USER");
            userService.addRoleToUser("kasmir", "ROLE_USER");*/
        };


    }
}
