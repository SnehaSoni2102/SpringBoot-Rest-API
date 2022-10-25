package com.springboot.blog.controller;


import com.springboot.blog.payload.PostDto;

import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Api(value="CRUD Rest APIs for Post resources")
@RestController
@RequestMapping()
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @ApiOperation(value = "Create Post REST API")
    @PreAuthorize("hasRole('ADMIN')")
    //Create blog post
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }
    // get all post rest api
    @ApiOperation(value = "Get all Post REST API")
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value="pageSize", defaultValue = "AppConstants.DEFAULT_PAGE_SIZE", required = false)int pageSize,
            @RequestParam(value="sortBy",defaultValue = "AppConstants.DEFAULT_PAGE_SORT_BY", required=false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = "AppConstants.DEFAULT_SORT_DIRECTION", required=false) String sortDir
    ){

         return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    //get post by id
    // versioning @GetMapping("/api/v1/posts/{id}")
    //  versioning through query @GetMapping(value="/api/posts/{id}", params="version=1")
    //versioning through headers @GetMapping(value="/api/posts/{id}",headers="X-API-VERSION=1")
    //versioning through content negotiation @GetMapping(value="/api/posts/{id}",produces="application/vnd.javaguildes.v1+json")
    // get all post rest api
    @ApiOperation(value = "Get Post by id REST API")
    @GetMapping(value="/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name="id")long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
//    //get post by id
//    // versioning @GetMapping("/api/v2/posts/{id}")
//    //  versioning through query @GetMapping(value="/api/posts/{id}", params="version=2")
//    //versioning through headers @GetMapping(value="/api/posts/{id}",headers="X-API-VERSION=2")
//    //versioning through content negotiation
//    @GetMapping(value="/api/posts/{id}",produces="application/vnd.javaguildes.v2+json")
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name="id")long id){
//        PostDto postDto=postService.getPostById(id);
//        PostDtoV2 postDtoV2=new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("Spring boot");
//        tags.add("AWS");
//        postDtoV2.setTags(tags);
//            return ResponseEntity.ok(postDtoV2);
//    }
    // update post by id rest api
@ApiOperation(value = "Update Post by id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id){
         PostDto postResponse =postService.updatePost(postDto, id);
         return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }
    @ApiOperation(value = "Delete Post by id REST API")
    @PreAuthorize("hasRole('ADMIN')")
    // delete post by id rest api
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost( @PathVariable(name = "id") long id){
     postService.deletePostById(id);
        return new ResponseEntity<>("Post Entity deleted successfully.",HttpStatus.OK);
    }


}
