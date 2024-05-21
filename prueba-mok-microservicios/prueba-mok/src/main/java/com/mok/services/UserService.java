package com.mok.services;

import com.mok.models.DTO.UserDTO;
import com.mok.models.Post;
import com.mok.models.User;
import com.mok.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> findAll() {
        try {
            return userRepository.findAll().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while fetching users", e);
        }
    }

    public UserDTO findById(Long id) {
        try {
            return userRepository.findById(id)
                    .map(this::convertToDto)
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while finding user by id: " + id, e);
        }
    }

    public UserDTO save(UserDTO userDTO) {
        try {
            User user = convertToEntity(userDTO);
            User savedUser = userRepository.save(user);
            return convertToDto(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar usuario", e);
        }
    }

    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar" + id, e);
        }
    }

    private UserDTO convertToDto(User user) {
        try {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);

            List<String> postTitles = user.getPosts().stream()
                    .map(Post::getTitle)
                    .collect(Collectors.toList());
            userDTO.setPostTitles(postTitles);

            return userDTO;
        } catch (Exception e) {
            throw new RuntimeException("Error convirtiendo objeto", e);
        }
    }

    private User convertToEntity(UserDTO userDTO) {
        try {
            return modelMapper.map(userDTO, User.class);
        } catch (Exception e) {
            throw new RuntimeException("Error convirtiendo objeto", e);
        }
    }
}

