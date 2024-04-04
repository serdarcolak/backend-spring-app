package com.example.springapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.springapp.responses.PostResponse;
import org.springframework.stereotype.Service;
import com.example.springapp.entities.Post;
import com.example.springapp.entities.User;
import com.example.springapp.repos.PostRepository;
import com.example.springapp.requests.PostCreateRequest;
import com.example.springapp.requests.PostUpdateRequest;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	
	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	public List<PostResponse> getAllPosts(Optional<Long> userId) {
		List<Post> list;

		if(userId.isPresent()) {
			list = postRepository.findByUserId(userId.get());
		}
		list = postRepository.findAll();
		return list.stream().map(p -> new PostResponse(p)).collect(Collectors.toList());
		
	}

	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public Post createOnePost(PostCreateRequest newPostRequest) {
		User user = userService.getOneUserById(newPostRequest.getUserId());
		if (user == null)
			return null;
		Post toSave = new Post();
		toSave.setId(newPostRequest.getId());
		toSave.setTitle(newPostRequest.getTitle());
		toSave.setText(newPostRequest.getText());
		toSave.setUser(user);
		return postRepository.save(toSave);
	}

	public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
		Optional<Post> post = postRepository.findById(postId);
		if (post.isPresent()) {
			Post toUpdate = post.get();
			toUpdate.setTitle(updatePost.getTitle());
			toUpdate.setText(updatePost.getText());
			postRepository.save(toUpdate);
			return toUpdate;
		}
		return null;
	}

	public void deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
	}
}
