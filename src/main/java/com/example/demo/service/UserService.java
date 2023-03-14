package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    public UserDTO saveUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    public List<UserDTO> getAllUsers(){
        List<User>userList=userRepo.findAll();
        return modelMapper.map(userList,new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO updateUser(UserDTO userDTO){
        userRepo.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    public boolean deleteUser(UserDTO userDTO){
        userRepo.delete(modelMapper.map(userDTO,User.class));
        return true;
    }

    //user id -> user details
    //SELECT * FROM USER WHERE USER id = 2
    public UserDTO getUserByUserID(String userId){
        User user = userRepo.getUserByUserID(userId);
        return modelMapper.map(user,UserDTO.class);}

    //user id, address -> user details
    //SELECT * FROM USER WHERE USER id = 2 AND address = "kandy"
    public UserDTO getUserByUserIDAndAddress(String userID, String address) {
        User user = userRepo.getUserByUserIDAndAddress(userID,address);
        return modelMapper.map(user,UserDTO.class);
    }
    }
