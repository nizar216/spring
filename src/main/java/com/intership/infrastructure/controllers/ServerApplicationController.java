package com.intership.infrastructure.controllers;

import com.intership.infrastructure.payload.dto.ServerApplicationDTO;
import com.intership.infrastructure.services.ServerApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/server-applications")
@RequiredArgsConstructor
public class ServerApplicationController {

    private final ServerApplicationService serverApplicationService;

    @PostMapping
    public ResponseEntity<ServerApplicationDTO> createServerApplication(@RequestBody ServerApplicationDTO serverApplicationDTO) {
        return ResponseEntity.ok(serverApplicationService.createServerApplication(serverApplicationDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServerApplicationDTO> getServerApplicationById(@PathVariable Integer id) {
        return ResponseEntity.ok(serverApplicationService.getServerApplicationById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServerApplicationDTO>> getAllServerApplications() {
        return ResponseEntity.ok(serverApplicationService.getAllServerApplications());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServerApplicationDTO> updateServerApplication(@PathVariable Integer id, @RequestBody ServerApplicationDTO serverApplicationDTO) {
        return ResponseEntity.ok(serverApplicationService.updateServerApplication(id, serverApplicationDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServerApplication(@PathVariable Integer id) {
        serverApplicationService.deleteServerApplication(id);
        return ResponseEntity.noContent().build();
    }
}
