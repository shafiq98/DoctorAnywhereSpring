package com.example.doctoranywherespring.Service;

import com.example.doctoranywherespring.ResponseDTO.RootResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RootService {

    public RootService() {

    }

    public RootResponse generateRootResponse(String message) {
        return RootResponse.builder().message(message).build();
    }
}
