package com.example.doctoranywherespring.Controller;

import com.example.doctoranywherespring.RequestDTO.RootRequest;
import com.example.doctoranywherespring.ResponseDTO.RootResponse;
import com.example.doctoranywherespring.Service.RootService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/api/v1/root")
public class RootController {
    private final RootService rootService;

    public RootController(RootService rootService) {
        this.rootService = rootService;
    }

    @GetMapping
    public ResponseEntity<RootResponse> getBase(@RequestBody RootRequest request) {
        try {
            log.debug("Well-formed Request received");
            return ResponseEntity.ok(rootService.generateRootResponse(request.getMessage()));
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.ok(rootService.generateRootResponse("Fallback Message"));
        }
    }
}
