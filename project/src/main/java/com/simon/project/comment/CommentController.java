package com.simon.project.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment")
    String postComment(Authentication auth, String content, Long parent){
        commentService.addComment(auth, content, parent);
        return "redirect:/list/page/1";
    }


}
