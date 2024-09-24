package site.dadangsinhhoc.services;

import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.IntroduceModel;

@Service
public interface IIntroduceService {
     boolean existById(Long id);

     ResponseObject findById(Long id);

     ResponseObject getAllIntroduce();

     ResponseObject countAllIntroduce();

     ResponseObject saveIntroduce(IntroduceModel introduceModel);

     ResponseObject updateIntroduce(Long id, IntroduceModel introduceModel);

     ResponseObject deleteByIdIntroduce(Long id);

}
