package com.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final Map<String, List<String>> videoCommentsMap = new HashMap<>();

    @PostMapping
    public void addComment(@RequestBody CommentRequest commentRequest) {
        String videoId = commentRequest.getVideoId();
        String comment = commentRequest.getComment();

        // Save the comment to the in-memory storage
        videoCommentsMap.computeIfAbsent(videoId, k -> new ArrayList<>()).add(comment);
    }

    @GetMapping("/{videoId}")
    public List<String> getComments(@PathVariable String videoId) {
        // Retrieve comments for the specified video ID
        return videoCommentsMap.getOrDefault(videoId, Collections.emptyList());
    }
}