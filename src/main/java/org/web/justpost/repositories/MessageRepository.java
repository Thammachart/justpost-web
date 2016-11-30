package org.web.justpost.repositories;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.justpost.entities.Message;
import org.justpost.adapters.repositories.MessageRepositoryInterface;

public class MessageRepository implements MessageRepositoryInterface {
    private List<Message> list;

    public MessageRepository() {
        list = new ArrayList<>();
    }

    @Override
    public List<Message> get(Map<String, String> filters) {
        return list;
    }

    @Override
    public void add(Message message) {
        list.add(message);
    }
}
