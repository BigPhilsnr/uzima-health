package com.uzimahealth.service;

import com.uzimahealth.model.VideoCall;
import com.uzimahealth.repository.VideoCallRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VideoCallService {
    private final VideoCallRepository videoCallRepository;

    public VideoCallService(VideoCallRepository videoCallRepository) {
        this.videoCallRepository = videoCallRepository;
    }

    public List<VideoCall> findAll() {
        return videoCallRepository.findAll();
    }

    public Optional<VideoCall> findById(Long id) {
        return videoCallRepository.findById(id);
    }

    public VideoCall save(VideoCall videoCall) {
        return videoCallRepository.save(videoCall);
    }
}