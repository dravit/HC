package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.ClientStatus;
import com.huntingCube.dataView.service.ClientStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by guptado on 11/05/2017.
 */
@Component
public class ClientStatusConverter implements Converter<Object, ClientStatus> {

    static final Logger logger = LoggerFactory.getLogger(ClientStatusConverter.class);

    @Autowired
    ClientStatusService clientStatusService;

    @Override
    public ClientStatus convert(Object source) {
        Integer id = Integer.parseInt((String) source);
        ClientStatus clientStatus = clientStatusService.findById(id);
        logger.info("Client Status : {}", clientStatus);
        return clientStatus;
    }
}
