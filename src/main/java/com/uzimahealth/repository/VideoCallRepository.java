package com.uzimahealth.repository;

import com.uzimahealth.model.VideoCall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCallRepository extends JpaRepository<VideoCall, Long> {
}