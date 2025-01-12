package com.intership.infrastructure.services;

import com.intership.infrastructure.domain.entity.ClusterApplication;
import com.intership.infrastructure.domain.repository.ClusterApplicationRepository;
import com.intership.infrastructure.payload.dto.ClusterApplicationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClusterApplicationServiceImpl implements ClusterApplicationService {

    private final ClusterApplicationRepository clusterApplicationRepository;

    @Override
    public ClusterApplicationDTO createClusterApplication(ClusterApplicationDTO clusterApplicationDTO) {
        ClusterApplication clusterApplication = ClusterApplication.builder()
                .status(clusterApplicationDTO.getStatus())
                .build();
        clusterApplication = clusterApplicationRepository.save(clusterApplication);
        return mapToDTO(clusterApplication);
    }

    @Override
    public ClusterApplicationDTO getClusterApplicationById(Integer id) {
        ClusterApplication clusterApplication = clusterApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cluster Application not found"));
        return mapToDTO(clusterApplication);
    }

    @Override
    public List<ClusterApplicationDTO> getAllClusterApplications() {
        return clusterApplicationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClusterApplicationDTO updateClusterApplication(Integer id, ClusterApplicationDTO clusterApplicationDTO) {
        ClusterApplication clusterApplication = clusterApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cluster Application not found"));

        clusterApplication.setStatus(clusterApplicationDTO.getStatus());

        clusterApplication = clusterApplicationRepository.save(clusterApplication);
        return mapToDTO(clusterApplication);
    }

    @Override
    public void deleteClusterApplication(Integer id) {
        clusterApplicationRepository.deleteById(id);
    }

    private ClusterApplicationDTO mapToDTO(ClusterApplication clusterApplication) {
        return ClusterApplicationDTO.builder()
                .id(clusterApplication.getId())
                .status(clusterApplication.getStatus())
                .build();
    }
}
