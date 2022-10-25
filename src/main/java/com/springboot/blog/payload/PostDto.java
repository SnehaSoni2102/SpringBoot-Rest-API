package com.springboot.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Post Model Information")
@Data
public class PostDto {
    @ApiModelProperty(value="Blog post id")
    private long id;

    // title should not be empty
    // title should have at least 2 characters
    @ApiModelProperty(value="Blog post title")
    @NotEmpty
    @Size(min =2,message = "post title should have at least 2 characters ")
    private String title;
    @ApiModelProperty(value="Blog post description")
    @NotEmpty
    @Size(min =10,message = "post title should have at least 2 characters ")
    private String description;

    //Post should be null or empty
    @ApiModelProperty(value="Blog post Content")
    @NotEmpty
    private String content;
    @ApiModelProperty(value="Blog post comments")
    private Set<CommentDto> comments;

}
