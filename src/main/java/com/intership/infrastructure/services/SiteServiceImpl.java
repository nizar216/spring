package com.intership.infrastructure.services;

import com.intership.infrastructure.domain.entity.Site;
import com.intership.infrastructure.domain.repository.SiteRepository;
import com.intership.infrastructure.payload.dto.SiteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteServiceImpl implements SiteService {
    private final SiteRepository siteRepository;
    private final ModelMapper modelMapper;

    @Override
    public SiteDTO createSite(SiteDTO siteDTO) {
        Site site = modelMapper.map(siteDTO, Site.class);
        site = siteRepository.save(site);
        return modelMapper.map(site, SiteDTO.class);
    }

    @Override
    public List<SiteDTO> getAllSites() {
        return siteRepository.findAll().stream()
                .map(site -> modelMapper.map(site, SiteDTO.class))
                .collect(Collectors.toList());
    }
}