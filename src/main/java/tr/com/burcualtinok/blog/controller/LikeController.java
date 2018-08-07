package tr.com.burcualtinok.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import tr.com.burcualtinok.blog.model.Like;
import tr.com.burcualtinok.blog.repository.LikeRepository;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    private LikeRepository likeRepository;
    @GetMapping("")
    public ResponseEntity<List<Like>> getAllLikes(){
        List<Like> likes = likeRepository.findAllLikes();
        return ResponseEntity.ok(likes);
    }
    @PostMapping("")
    public ResponseEntity<Like> createNewLike(@Validated @RequestBody Like like){
        likeRepository.save(like);
        return ResponseEntity.ok(like);

    }

}
