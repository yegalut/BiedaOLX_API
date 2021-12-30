package com.example.biedaolx_api.service;

import com.example.biedaolx_api.dto.*;
import com.example.biedaolx_api.entity.Message;
import com.example.biedaolx_api.entity.Post;
import com.example.biedaolx_api.entity.Role;
import com.example.biedaolx_api.entity.User;
import com.example.biedaolx_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final MessageRepo messageRepo;
    private final PostRepo postRepo;
    private final PostCategoryRepo postCategoryRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authorities);
    }

    //Get all users
    public List<UserDto> getUsers(){
        return userRepo.findAll().stream().map(Mappers::mapToUserDto).collect(Collectors.toList());
    }


    //get user by id
    public UserDto getUser(String username){
        return Mappers.mapToUserDto(userRepo.findByUsername(username));
    }

    //Adding a user
    public UserDto addUser(CreateUserDto userData){
        if(userRepo.findByUsername(userData.getUsername())!=null){
            throw new IllegalArgumentException("This user already exists");
        }else {
            User user = new User(userData.getName(),
                    userData.getUsername(),
                    passwordEncoder.encode(userData.getPassword()));
            user.getRoles().add(roleRepo.findByName("ROLE_USER").orElseThrow());
            return Mappers.mapToUserDto(userRepo.save(user));
        }
    }

    //Deleting a user
    public void deleteUser(Long id){
        User user = userRepo.findById(id).orElseThrow();
        messageRepo.deleteAll(messageRepo.findAllByRecipientUsername(user.getUsername()));
        userRepo.delete(user);
    }

    //Editing a user
    public UserDto editUser(User user){
        if(userRepo.findById(user.getId()).isPresent()){
            return Mappers.mapToUserDto(userRepo.save(user));
        }else {
            throw new IllegalArgumentException("This role does not exist");
        }
    }

    //Add a post
    public PostDto addPost(String username, CreatePostDto createPostDto){
        User user = userRepo.findByUsername(username);
        Post post = new Post(createPostDto.getTitle(), createPostDto.getBody(), createPostDto.getPrice(), postCategoryRepo.findByName(createPostDto.getPostCategory()));
        post.setUser(user);
        user.getPosts().add(post);
        return Mappers.mapToPostDto(post);
    }

    //Send message
    public SendMessageDto sendMessage(String creator,SendMessageDto message){
        User user = userRepo.findByUsername(creator);
        user.getMessages().add(new Message(user, message.getRecipientUsername(), message.getMessage()));
        return message;
    }

    //Adding a role to user
    public UserDto addRoleToUser(String username, String roleName){
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName).orElseThrow();
        user.getRoles().add(role);
        return Mappers.mapToUserDto(user);
    }

    public List<MessageDto> getAllUserMessages(String username){
        User user = userRepo.findByUsername(username);
        List<Message> messages = messageRepo.findAllByCreator(user);
        return messages.stream().map(Mappers::mapToMessageDto).collect(Collectors.toList());
    }

    //Get conversation between two users
    public List<MessageDto> getConversation(String username1, String username2){
        List<Message> conversation = new ArrayList<>();
        User user1 = userRepo.findByUsername(username1);
        User user2 = userRepo.findByUsername(username2);


        List<Message> userMessages1 = messageRepo.findAllByCreatorAndRecipientUsername(user1,user2.getUsername());
        List<Message> userMessages2 = messageRepo.findAllByCreatorAndRecipientUsername(user2,user1.getUsername());

        if(userMessages1 != null)
            conversation.addAll(userMessages1);

        if(userMessages2 !=null)
            conversation.addAll(userMessages2);

        if(conversation.size()>0){
            return conversation.stream()
                    .map(Mappers::mapToMessageDto)
                    .sorted(Comparator.comparing(MessageDto::getDateOfCreation))
                    .collect(Collectors.toList());
        }else {
            throw new IllegalArgumentException("No messages between: "
                    + username1 +" and "
                    + username2);
        }
    }

    //Get list of users that for specific user
    public List<String> getConversationList(String username){

        User user = userRepo.findByUsername(username);

        List<String> conversationUsers = messageRepo.findAllByCreator(user).stream()
                .map(Message::getRecipientUsername)
                .distinct().collect(Collectors.toList());

        conversationUsers.addAll(messageRepo.findAllByRecipientUsername(username).stream()
                .map(message -> message.getCreator().getUsername()).collect(Collectors.toList()));


        return conversationUsers.stream()
                .distinct()
                .collect(Collectors.toList());
    }


    public List<PostDto> getAllUserPosts(String username) {
        User user = userRepo.findByUsername(username);
        List<Post> posts = postRepo.findAllByUser(user);
        return posts.stream().map(Mappers::mapToPostDto).collect(Collectors.toList());
    }
}
