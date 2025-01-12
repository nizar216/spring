package com.intership.infrastructure.service.impl;

import com.intership.infrastructure.domain.entity.Application;
import com.intership.infrastructure.domain.repository.ApplicationRepository;
import com.intership.infrastructure.payload.dto.ApplicationDTO;
import com.intership.infrastructure.services.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationDTO createApplication(ApplicationDTO applicationDTO) {
        Application application = Application.builder()
                .name(applicationDTO.getName())
                .description(applicationDTO.getDescription())
                .build();
        application = applicationRepository.save(application);
        return mapToDTO(application);
    }

    @Override
    public ApplicationDTO getApplicationById(Integer id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        return mapToDTO(application);
    }

    @Override
    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationDTO updateApplication(Integer id, ApplicationDTO applicationDTO) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setName(applicationDTO.getName());
        application.setDescription(applicationDTO.getDescription());

        application = applicationRepository.save(application);
        return mapToDTO(application);
    }

    @Override
    public void deleteApplication(Integer id) {
        applicationRepository.deleteById(id);
    }

    private ApplicationDTO mapToDTO(Application application) {
        return ApplicationDTO.builder()
                .id(application.getId())
                .name(application.getName())
                .description(application.getDescription())
                .build();
    }
}
