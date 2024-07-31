package site.dadangsinhhoc.services;

import site.dadangsinhhoc.exception.BadResourceException;
import site.dadangsinhhoc.exception.ResourceAlreadyExistsException;
import site.dadangsinhhoc.exception.ResourceNotFoundException;
import site.dadangsinhhoc.models.NganhModel;
import site.dadangsinhhoc.models.NganhModel;
import site.dadangsinhhoc.repositories.NganhRepository;
import site.dadangsinhhoc.repositories.NganhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NganhService {
    @Autowired
    private site.dadangsinhhoc.repositories.NganhRepository NganhRepository;

    public boolean existById(long id) {
        return NganhRepository.existsById(id);
    }

    public Optional<NganhModel> findById(long id) {
        return NganhRepository.findById(id);
    }

    public List<NganhModel> getAllNganh() {
        return NganhRepository.findAll();
    }

    public long countAllNganh() {
        return NganhRepository.count();
    }

    public NganhModel saveNganh(NganhModel nganhModel) throws BadResourceException, ResourceAlreadyExistsException {
        if (nganhModel.getName() == null || nganhModel.getName().isEmpty()) {
            throw new BadResourceException("Failed to save contact! Id for `nganh` is null or empty");

        }
        if (NganhRepository.existsById(nganhModel.getId())) {
            throw new ResourceAlreadyExistsException("Nganh already exists with id: " + nganhModel.getId());
        }
        return NganhRepository.save(nganhModel);
    }


    public void updateNganh(NganhModel nganhModel) throws BadResourceException, ResourceNotFoundException {
        if (nganhModel.getName() != null && !nganhModel.getName().isEmpty()) {
            if (!NganhRepository.existsById(nganhModel.getId())) {
                throw new ResourceNotFoundException("Cannot find nganh with id: " + nganhModel.getId());
            }
            NganhRepository.save(nganhModel);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save contact!");
            exc.addErrorMessage("Id for `nganh` is null of empty");
            throw exc;
        }
    }

    public void deleteByIdNganh(Long id) throws ResourceNotFoundException {
        if (!NganhRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot find nganh with id: " + id);
        }
        NganhRepository.deleteById(id);
    }
}
