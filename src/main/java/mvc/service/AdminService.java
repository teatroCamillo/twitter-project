package mvc.service;

import mvc.model.dto.AdminDTO;
import mvc.model.entity.Admin;
import mvc.repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final ModelMapper modelMapper;
    private final AdminRepository adminRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public AdminService(ModelMapper modelMapper, AdminRepository adminRepository) {
        this.modelMapper = modelMapper;
        this.adminRepository = adminRepository;
    }

    public void addAdmin(AdminDTO adminDTO){
        Admin admin = modelMapper.map(adminDTO, Admin.class);
       adminRepository.save(admin);
    }

    public List<AdminDTO> getAllAdmins(){
        return adminRepository
                .findAll()
                .stream()
                .map(a-> modelMapper.map(a,AdminDTO.class))
                .collect(Collectors.toList());
    }

    public void deleteAdminById(Long id){
        adminRepository.deleteById(id);
    }
}
