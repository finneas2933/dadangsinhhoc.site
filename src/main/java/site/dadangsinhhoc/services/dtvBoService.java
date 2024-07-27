package site.dadangsinhhoc.services;

import site.dadangsinhhoc.exception.BadResourceException;
import site.dadangsinhhoc.exception.ResourceAlreadyExistsException;
import site.dadangsinhhoc.exception.ResourceNotFoundException;
import site.dadangsinhhoc.models.boModel;
import site.dadangsinhhoc.repositories.dtvBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class dtvBoService {
    @Autowired
    private dtvBoRepository dtvBoRepository;

    public boolean existById(long id) {
        return dtvBoRepository.existsById(id);
    }

    public Optional<boModel> findById(long id) {
        return dtvBoRepository.findById(id);
    }

    public List<boModel> getAllBo() {
        return dtvBoRepository.findAll();
    }

    public long countAllBo() {
        return dtvBoRepository.count();
    }

    public boModel saveBo(boModel boModel) throws BadResourceException, ResourceAlreadyExistsException {
        if(boModel.getName()!=null && !boModel.getName().isEmpty()) {
            if(existById(boModel.getId()) && boModel.getId() != null) {
                throw new ResourceAlreadyExistsException(
                        "Contact with id: " + boModel.getId() + " already exists");
            }
            return dtvBoRepository.save(boModel);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save contact!");
            exc.addErrorMessage("Id for `dtv_bo` is null or empty");
            throw exc;
        }
    }

    public void updateBo(boModel boModel) throws BadResourceException, ResourceNotFoundException {
        if(boModel.getName()!=null && !boModel.getName().isEmpty()) {
            if (!dtvBoRepository.existsById(boModel.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + boModel.getId());
            }
            dtvBoRepository.save(boModel);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save contact!");
            exc.addErrorMessage("Id for `dtv_bo` is null or empty");
            throw exc;
        }
    }

    public void deleteByIdBo(Long id) throws ResourceNotFoundException {
        if (!dtvBoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot find contact with id: " + id);
        }
        else {
            dtvBoRepository.deleteById(id);
        }
    }

}
