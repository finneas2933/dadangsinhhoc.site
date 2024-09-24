package site.dadangsinhhoc.services;

import org.springframework.stereotype.Service;
import site.dadangsinhhoc.dto.response.ResponseObject;
import site.dadangsinhhoc.models.MessageModel;

@Service
public interface IMessageService {
     boolean existById(Long id);

     ResponseObject findMessageById(Long id);

     ResponseObject getAllMessage();

     ResponseObject countAllMessage();

     ResponseObject saveMessage(MessageModel messageModels);

     ResponseObject updateMessage(Long id, MessageModel messageModel);

     ResponseObject deleteByIdMessage(Long id);

}
