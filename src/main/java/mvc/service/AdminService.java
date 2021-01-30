package mvc.service;

import mvc.model.dto.AdminDTO;
import mvc.model.entity.Admin;
import mvc.repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AdminRepository adminRepository;

    /**
     * Adds new admin.
     */
    public void addAdmin(AdminDTO adminDTO) {
        Admin admin = modelMapper.map(adminDTO, Admin.class);
        adminRepository.save(admin);
    }

    /**
     * Returns entire list of admins.
     */
    public List<AdminDTO> getAllAdmins() {
        return adminRepository
                .findAll()
                .stream()
                .map(a -> modelMapper.map(a, AdminDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Removals admin by ID.
     */
    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }
}
