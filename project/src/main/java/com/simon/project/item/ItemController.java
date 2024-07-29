package com.simon.project.item;

import com.simon.project.comment.Comment;
import com.simon.project.comment.CommentRepository;
import com.simon.project.member.Member;
import com.simon.project.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final S3Service s3Service;
    private final CommentRepository commentRepository;

    @GetMapping("/list")
    String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items",result);

        return "list.html";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/write")
    String write(Model model){
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price, Authentication auth, String imageUrl){
        // 위 파라미터의 타입은 사실 유저가 보낸 데이터를
        // 적힌 타입으로 변환해달라는 뜻
        // 기존에는 @RequestParam(name = "title")
        // 이런식으로 작성했지만, 워낙 많이 작성하다 보니
        // 편의상 타입과 변수로만 작성해도 되도록 해놨다.

        // 유저가 보낸 데이터를 DB에 저장해주세요~
        itemService.saveItem(title, price, auth, imageUrl);
        return "redirect:/list/page/1";
    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Integer id, Model model){

        Item item = itemService.getItemById(id);
        Long parentId = Long.valueOf(id);
        var comment = commentRepository.findAllByParentId(parentId);
        if (item != null){
            // .get 을 이용할 수도 있지만,값이 없을수도 있으니
            // .isPresent 를 이용
            // 해당 아이템이 존재할 때의 로직
            model.addAttribute("item", item);
            model.addAttribute("comment",comment);
            // 아이템을 모델에 추가
            return "detail.html";
        } else {
            return "redirect:/list/page/1";
        }

    }

    @GetMapping("/detail/edit/{id}")
    String edit(@PathVariable Integer id,Model model){
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        return "edit.html";
    }

    @PostMapping("/detail/edit/{id}")
    String editPost(@PathVariable Integer id, String title, Integer price,Model model){
        try {
            itemService.editItem(id, title, price);
            return "redirect:/detail/" + id; // 상세페이지로 리다이렉트
        }catch (IllegalArgumentException e){
            // 수정 페이지로 리다이렉트하면서 에러 메시지를 쿼리 파라미터로 전달
            return "redirect:/detail/edit/" + id;
        }
    }

    @PostMapping("/test1")
    String test1(@RequestBody Map<String, Object> body){
        System.out.println(body.get("name"));
        return "redirect:/list";
    }

    @GetMapping("/test2")
    String test2(){
        var result = new BCryptPasswordEncoder().encode("문자");
        System.out.println(result);
        return "redirect:/list";
    }


    @DeleteMapping("/item")
    ResponseEntity<String> deleteItem(@RequestParam Integer id){
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");
    }

    @GetMapping("/list/page/{number}")
    String getListPage(Model model, @PathVariable Integer number){
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(number-1,3));
        model.addAttribute("items",result);
        model.addAttribute("currentPage",number);
        model.addAttribute("totalPages",result.getTotalPages());
        return "list.html";
    }

    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename){
        var result = s3Service.createPresignedUrl("test/" + filename);
        return result;
    }


}
