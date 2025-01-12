package com.intership.infrastructure.services;

import com.intership.infrastructure.domain.entity.Subnet;
import com.intership.infrastructure.domain.repository.SubnetRepository;
import com.intership.infrastructure.payload.dto.SubnetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubnetServiceImpl implements SubnetService {

    private final SubnetRepository subnetRepository;

    @Override
    public SubnetDTO createSubnet(SubnetDTO subnetDTO) {
        Subnet subnet = Subnet.builder()
                .cidrNotation(subnetDTO.getCidrNotation())
                .subnetMask(subnetDTO.getSubnetMask())
                .ipRange(subnetDTO.getIpRange())
                .gateway(subnetDTO.getGateway())
                .build();
        subnet = subnetRepository.save(subnet);
        return mapToDTO(subnet);
    }

    @Override
    public SubnetDTO getSubnetById(Integer id) {
        Subnet subnet = subnetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subnet not found"));
        return mapToDTO(subnet);
    }

    @Override
    public List<SubnetDTO> getAllSubnets() {
        return subnetRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubnetDTO updateSubnet(Integer id, SubnetDTO subnetDTO) {
        Subnet subnet = subnetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subnet not found"));

        subnet.setCidrNotation(subnetDTO.getCidrNotation());
        subnet.setSubnetMask(subnetDTO.getSubnetMask());
        subnet.setIpRange(subnetDTO.getIpRange());
        subnet.setGateway(subnetDTO.getGateway());

        subnet = subnetRepository.save(subnet);
        return mapToDTO(subnet);
    }

    @Override
    public void deleteSubnet(Integer id) {
        subnetRepository.deleteById(id);
    }

    private SubnetDTO mapToDTO(Subnet subnet) {
        return SubnetDTO.builder()
                .id(subnet.getId())
                .cidrNotation(subnet.getCidrNotation())
                .subnetMask(subnet.getSubnetMask())
                .ipRange(subnet.getIpRange())
                .gateway(subnet.getGateway())
                .build();
    }
}
