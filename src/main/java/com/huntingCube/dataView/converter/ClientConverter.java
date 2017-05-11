package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.Client;
import com.huntingCube.dataView.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by guptado on 11/05/2017.
 */
@Component
public class ClientConverter implements Converter<Object, Client> {

    static final Logger logger = LoggerFactory.getLogger(ClientConverter.class);

    @Autowired
    ClientService clientService;

    @Override
    public Client convert(Object source) {
        Integer id = Integer.parseInt((String) source);
        Client client = clientService.findById(id);
        logger.info("Client : {}", client);
        return client;
    }
}