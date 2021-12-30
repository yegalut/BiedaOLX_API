package com.example.biedaolx_api.controller;
import com.example.biedaolx_api.dto.*;
import com.example.biedaolx_api.entity.User;
import com.example.biedaolx_api.service.JwtService;
import com.example.biedaolx_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin("**")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/getUsers")
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/getUser")
    public UserDto getUser(HttpServletRequest request){
        return userService.getUser(jwtService.getUsernameFromToken(request));
    }

    @PostMapping("/addUser")
    public UserDto addUser(@RequestBody CreateUserDto user){
        return userService.addUser(user);
    }

    @PutMapping("/editUser")
    public UserDto editUser(HttpServletRequest request, @RequestBody User user){
        return userService.editUser(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    @PutMapping("/addRoleToUser")
    public UserDto addRoleToUser(@RequestBody RoleUserDto roleUserDto){
        return userService.addRoleToUser(roleUserDto.getUsername(), roleUserDto.getRoleName());
    }

    @GetMapping("/getConversation")
    public List<MessageDto> getConversation(HttpServletRequest request, @RequestParam( name = "secondUser")String username2){
        String username1 = jwtService.getUsernameFromToken(request);
        return userService.getConversation(username1, username2);
    }
    @GetMapping("/getAllMessages")
    public List<MessageDto> getAllMessages(HttpServletRequest request){
        return userService.getAllUserMessages(jwtService.getUsernameFromToken(request));
    }

    @GetMapping("/getUserPosts")
    public List<PostDto> getAllUserPosts(HttpServletRequest request){
        return userService.getAllUserPosts(jwtService.getUsernameFromToken(request));
    }

    @GetMapping("/getConversationsList")
    public List<String> getAllConversationsList(HttpServletRequest request){
        return userService.getConversationList(jwtService.getUsernameFromToken(request));
    }

    @PostMapping("/sendMessage")
    public SendMessageDto sendMessage(HttpServletRequest request,
                                      @RequestBody SendMessageDto sendMessageDto){
        return userService.sendMessage(jwtService.getUsernameFromToken(request), sendMessageDto);
    }

    @PostMapping("/addPostToUser")
    public PostDto addPost(HttpServletRequest request, @RequestBody CreatePostDto createPostDto){
        return userService.addPost(jwtService.getUsernameFromToken(request), createPostDto);
    }

    @GetMapping("/getCookie")
    public ResponseEntity<String> getCookie(HttpServletResponse response, HttpServletRequest request){

        HttpCookie cookie = ResponseCookie.from("Token", jwtService.getTokenFromHeader(request))
                .path("/")
                .maxAge(60*60*10)
                .httpOnly(true)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("setCookie");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){
        HttpCookie cookie = ResponseCookie.from("Token", "")
                .path("/")
                .maxAge(1)
                .httpOnly(true)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("setCookie");
    }


}
