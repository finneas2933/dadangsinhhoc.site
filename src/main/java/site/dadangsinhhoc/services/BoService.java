package site.dadangsinhhoc.services;

import site.dadangsinhhoc.exception.BadResourceException;
import site.dadangsinhhoc.exception.ResourceAlreadyExistsException;
import site.dadangsinhhoc.exception.ResourceNotFoundException;
import site.dadangsinhhoc.models.BoModel;
import site.dadangsinhhoc.repositories.BoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoService {
    @Autowired
    private BoRepository BoRepository;

    public boolean existById(long id) {
        return BoRepository.existsById(id);
    }

    public Optional<BoModel> findById(long id) throws ResourceNotFoundException {
        Optional<BoModel> bo = BoRepository.findById(id);
        if (bo.isPresent()) {
            return bo;
        } else {
            throw new ResourceNotFoundException("Cannot find bo with id: " + id);
        }
    }

    public List<BoModel> getAllBo() {
        return BoRepository.findAll();
    }

    public long countAllBo() {
        return BoRepository.count();
    }

    public BoModel saveBo(BoModel boModel) throws BadResourceException, ResourceAlreadyExistsException {
        if (boModel.getName() != null && !boModel.getName().isEmpty()) {
            if (BoRepository.existsById(boModel.getId())) {
                throw new ResourceAlreadyExistsException("Bo with id: " + boModel.getId() + " already exist.");
            }
            return BoRepository.save(boModel);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save bo!");
            exc.addErrorMessage("Name for `bo` is null or empty");
            throw exc;
        }
    }

    public void updateBo(BoModel boModel) throws BadResourceException, ResourceNotFoundException {
        if (boModel.getName() != null && !boModel.getName().isEmpty()) {
            if (!BoRepository.existsById(boModel.getId())) {
                throw new ResourceNotFoundException("Cannot find bo with id: " + boModel.getId());
            }
            BoRepository.save(boModel);
        } else {
            BadResourceException exc = new BadResourceException("Failed to update bo!");
            exc.addErrorMessage("Name for `bo` is null or empty");
            throw exc;
        }
    }

    public void deleteByIdBo(Long id) throws ResourceNotFoundException {
        if (!BoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot find bo with id: " + id);
        } else {
            BoRepository.deleteById(id);
        }
    }

}
