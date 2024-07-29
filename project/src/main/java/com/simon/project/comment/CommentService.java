package com.simon.project.comment;

import com.simon.project.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void addComment(Authentication auth, String content, Long parent){
        CustomUser customUser = (CustomUser)auth.getPrincipal();
        var data = new Comment();
        data.setUsername(customUser.getUsername());
        data.setContent(content);
        data.setParentId(parent);
        commentRepository.save(data);
    }
}
