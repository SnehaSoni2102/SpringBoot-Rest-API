//package com.springboot.blog.payload;
//
//import lombok.Data;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import java.util.List;
//import java.util.Set;
//
//// **********************DELETE POST DTO V2 CLASS******************************************************************************
//
//@Data
//public class PostDtoV2 {
//    private long id;
//
//    // title should not be empty
//    // title should have at least 2 characters
//    @NotEmpty
//    @Size(min =2,message = "post title should have at least 2 characters ")
//    private String title;
//    @NotEmpty
//    @Size(min =10,message = "post title should have at least 2 characters ")
//    private String description;
//
//    //Post should be null or empty
//    @NotEmpty
//    private String content;
//    private Set<CommentDto> comments;
//    private List<String> tags;
//}
