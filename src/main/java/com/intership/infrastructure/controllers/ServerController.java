package com.intership.infrastructure.controllers;

import com.intership.infrastructure.payload.dto.ServerDTO;
import com.intership.infrastructure.services.ServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servers")
@RequiredArgsConstructor
public class ServerController {

    private final ServerService serverService;

    @PostMapping
    public ResponseEntity<ServerDTO> createServer(@RequestBody ServerDTO serverDTO) {
        return ResponseEntity.ok(serverService.createServer(serverDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServerDTO> getServerById(@PathVariable Integer id) {
        return ResponseEntity.ok(serverService.getServerById(id));
    }

    @GetMapping
    public ResponseEntity<List<ServerDTO>> getAllServers() {
        return ResponseEntity.ok(serverService.getAllServers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServerDTO> updateServer(@PathVariable Integer id, @RequestBody ServerDTO serverDTO) {
        return ResponseEntity.ok(serverService.updateServer(id, serverDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable Integer id) {
        serverService.deleteServer(id);
        return ResponseEntity.noContent().build();
    }
}
