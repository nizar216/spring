package com.intership.infrastructure.services;

import com.intership.infrastructure.domain.entity.Server;
import com.intership.infrastructure.domain.repository.ServerRepository;
import com.intership.infrastructure.payload.dto.ServerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public ServerDTO createServer(ServerDTO serverDTO) {
        Server server = Server.builder()
                .hostName(serverDTO.getHostName())
                .addressIp(serverDTO.getAddressIp())
                .status(serverDTO.getStatus())
                .specification(serverDTO.getSpecification())
                .build();
        server = serverRepository.save(server);
        return mapToDTO(server);
    }

    @Override
    public ServerDTO getServerById(Integer id) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Server not found"));
        return mapToDTO(server);
    }

    @Override
    public List<ServerDTO> getAllServers() {
        return serverRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServerDTO updateServer(Integer id, ServerDTO serverDTO) {
        Server server = serverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Server not found"));

        server.setHostName(serverDTO.getHostName());
        server.setAddressIp(serverDTO.getAddressIp());
        server.setStatus(serverDTO.getStatus());
        server.setSpecification(serverDTO.getSpecification());

        server = serverRepository.save(server);
        return mapToDTO(server);
    }

    @Override
    public void deleteServer(Integer id) {
        serverRepository.deleteById(id);
    }

    private ServerDTO mapToDTO(Server server) {
        return ServerDTO.builder()
                .id(server.getId())
                .hostName(server.getHostName())
                .addressIp(server.getAddressIp())
                .status(server.getStatus())
                .specification(server.getSpecification())
                .build();
    }
}
