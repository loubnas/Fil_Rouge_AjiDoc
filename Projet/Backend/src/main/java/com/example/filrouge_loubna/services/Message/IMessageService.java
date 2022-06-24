package com.example.filrouge_loubna.services.Message;

import com.example.filrouge_loubna.dto.model.AppointementDto;
import com.example.filrouge_loubna.dto.model.MessageDto;

import java.util.List;

public interface IMessageService {

    MessageDto AddMessage(MessageDto message);
    List<MessageDto> getAllMessages(int page , int limit);
    List<MessageDto> getAllMessages();
}
