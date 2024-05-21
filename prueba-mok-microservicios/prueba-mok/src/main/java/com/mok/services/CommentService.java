package com.mok.services;

import com.mok.exceptions.CommentNotFoundException;
import com.mok.exceptions.PostNotFoundException;
import com.mok.models.Comment;
import com.mok.models.DTO.CommentDTO;
import com.mok.models.Post;
import com.mok.repository.CommentRepository;
import com.mok.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRepository;

    public List<CommentDTO> findAll() {
        return commentRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CommentDTO findById(Long id) {
        return commentRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new CommentNotFoundException("comentario no encontrado" + id));
    }

    public CommentDTO save(CommentDTO commentDTO) {
        Comment comment = convertToEntity(commentDTO);
        Comment savedComment = commentRepository.save(comment);
        return convertToDto(savedComment);
    }

    public void deleteById(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new CommentNotFoundException("comentario no encontrado" + id);
        }
        commentRepository.deleteById(id);
    }

    private CommentDTO convertToDto(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        commentDTO.setPostTitle(comment.getPost().getTitle());
        return commentDTO;
    }

    private Comment convertToEntity(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        Post post = postRepository.findByTitle(commentDTO.getPostTitle());

        if (post == null) {
            throw new PostNotFoundException("post con titulo" + commentDTO.getPostTitle() + " no encontrado");
        }
        comment.setPost(post);
        return comment;
    }
}

