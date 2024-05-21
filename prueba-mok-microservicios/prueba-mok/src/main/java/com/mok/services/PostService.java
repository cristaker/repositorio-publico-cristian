package com.mok.services;

import com.mok.models.Category;
import com.mok.models.Comment;
import com.mok.models.DTO.PostDTO;
import com.mok.models.Post;
import com.mok.models.User;
import com.mok.repository.CategoryRepository;
import com.mok.repository.PostRepository;
import com.mok.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PostDTO> findAll() {
        try {
            return postRepository.findAll().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar", e);
        }
    }

    public PostDTO findById(Long id) {
        try {
            return postRepository.findById(id)
                    .map(this::convertToDto)
                    .orElseThrow(() -> new RuntimeException("Post no encontrado" + id));
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar" + id, e);
        }
    }

    public PostDTO save(PostDTO postDTO) {
        try {
            Post post = convertToEntity(postDTO);
            Post savedPost = postRepository.save(post);
            return convertToDto(savedPost);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar", e);
        }
    }

    public void deleteById(Long id) {
        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al borrar" + id, e);
        }
    }

    private PostDTO convertToDto(Post post) {
        try {
            PostDTO postDTO = modelMapper.map(post, PostDTO.class);
            if (post.getUser() != null) {
                List<String> postTitles = post.getUser().getPosts().stream()
                        .map(Post::getTitle)
                        .collect(Collectors.toList());
                postDTO.getUser().setPostTitles(postTitles);
            }
            return postDTO;
        } catch (Exception e) {
            throw new RuntimeException("occurrio error al convertir a DTO", e);
        }
    }

    private Post convertToEntity(PostDTO postDTO) {
        try {
            Post post = modelMapper.map(postDTO, Post.class);

            if (postDTO.getUser() != null) {
                User user = userRepository.findById(postDTO.getUser().getId())
                        .orElseThrow(() -> new RuntimeException("usuario no encontrado"));
                post.setUser(user);
            }

            if (postDTO.getCategory() != null) {
                Category category = categoryRepository.findById(postDTO.getCategory().getId())
                        .orElseThrow(() -> new RuntimeException("categoria no encontrada"));
                post.setCategory(category);
            }

            if (postDTO.getComments() != null) {
                List<Comment> comments = postDTO.getComments().stream()
                        .map(commentDTO -> {
                            Comment comment = modelMapper.map(commentDTO, Comment.class);
                            comment.setPost(post);
                            return comment;
                        })
                        .collect(Collectors.toList());
                post.setComments(comments);
            }

            return post;
        } catch (Exception e) {
            throw new RuntimeException("Ocurrio un error al convertir a DTO", e);
        }
    }
}


