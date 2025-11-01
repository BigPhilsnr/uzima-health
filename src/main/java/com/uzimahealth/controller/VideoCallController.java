package com.uzimahealth.controller;

import com.uzimahealth.model.VideoCall;
import com.uzimahealth.service.VideoCallService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/videocalls")
public class VideoCallController {
    private final VideoCallService videoCallService;

    public VideoCallController(VideoCallService videoCallService) {
        this.videoCallService = videoCallService;
    }

    @GetMapping
    public List<VideoCall> getAllVideoCalls() {
        return videoCallService.findAll();
    }

    @PostMapping
    public VideoCall scheduleVideoCall(@Valid @RequestBody VideoCall videoCall) {
        return videoCallService.save(videoCall);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoCall> updateVideoCall(@PathVariable Long id, @Valid @RequestBody VideoCall videoCall) {
        if (!videoCallService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        videoCall.setId(id);
        return ResponseEntity.ok(videoCallService.save(videoCall));
    }
}