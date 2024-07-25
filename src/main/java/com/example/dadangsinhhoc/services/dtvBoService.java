package com.example.dadangsinhhoc.services;

import com.example.dadangsinhhoc.exception.BadResourceException;
import com.example.dadangsinhhoc.exception.ResourceAlreadyExistsException;
import com.example.dadangsinhhoc.exception.ResourceNotFoundException;
import com.example.dadangsinhhoc.models.dtvBo;
import com.example.dadangsinhhoc.repositories.dtvBoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class dtvBoService {
    @Autowired
    private dtvBoRepository dtvBoRepository;

    public boolean existById(long id) {
        return dtvBoRepository.existsById(id);
    }

    public Optional<dtvBo> findById(long id) {
        return dtvBoRepository.findById(id);
    }

    public List<dtvBo> getAllBo() {
        return dtvBoRepository.findAll();
    }

    public long countAllBo() {
        return dtvBoRepository.count();
    }

    public dtvBo saveBo(dtvBo dtvBo) throws BadResourceException, ResourceAlreadyExistsException {
        if(dtvBo.getName()!=null && !dtvBo.getName().isEmpty()) {
            if(existById(dtvBo.getId()) && dtvBo.getId() != null) {
                throw new ResourceAlreadyExistsException(
                        "Contact with id: " + dtvBo.getId() + " already exists");
            }
            return dtvBoRepository.save(dtvBo);
        }
        else {
            BadResourceException exc = new BadResourceException("Failed to save contact!");
            exc.addErrorMessage("Id for `dtv_bo` is null or empty");
            throw exc;
        }
    }

    public void updateBo(dtvBo dtvBo) throws BadResourceException, ResourceNotFoundException {
        if(dtvBo.getName()!=null && !dtvBo.getName().isEmpty()) {
            if (!dtvBoRepository.existsById(dtvBo.getId())) {
                throw new ResourceNotFoundException("Cannot find Contact with id: " + dtvBo.getId());
            }
            dtvBoRepository.save(dtvBo);
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
