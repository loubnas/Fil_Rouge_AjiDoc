package com.example.filrouge_loubna.services.Message;


import com.example.filrouge_loubna.dto.model.AppointementDto;
import com.example.filrouge_loubna.dto.model.MessageDto;
import com.example.filrouge_loubna.dto.services.IMapClassWithDto;
import com.example.filrouge_loubna.entities.Appointement;
import com.example.filrouge_loubna.entities.Message;
import com.example.filrouge_loubna.repositories.AppointementRepository;
import com.example.filrouge_loubna.repositories.MessageRepository;
import com.example.filrouge_loubna.services.Appointement.IAppointementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService implements IMessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    IMapClassWithDto<Message, MessageDto> messageMapping;

    @Override
    public MessageDto AddMessage(MessageDto messageDto) {
        Message m = messageMapping.convertToEntity(messageDto, Message.class);
        m = messageRepository.save(m);
        return messageMapping.convertToDto(m, MessageDto.class);
    }

    @Override
    public List<MessageDto> getAllMessages(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Message> m = messageRepository.findAll(pageableRequest);
        return messageMapping.convertPageToListDto(m, MessageDto.class);
    }

    @Override
    public List<MessageDto> getAllMessages() {

        List<Message> m = messageRepository.getAllBy();
        return messageMapping.convertListToListDto(m, MessageDto.class);
    }
}
