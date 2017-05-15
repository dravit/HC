package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.ClientPosition;
import com.huntingCube.dataView.model.ClientStatus;
import com.huntingCube.dataView.service.ClientPositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by dgup27 on 5/12/2017.
 */
@Component
public class ClientPositionConverter implements Converter<Object, ClientPosition> {

    static final Logger logger = LoggerFactory.getLogger(ClientStatusConverter.class);

    @Autowired
    ClientPositionService clientPositionService;

    @Override
    public ClientPosition convert(Object source) {

        logger.info("Started Fucking client status");
        if(source != null && source instanceof ClientStatus) {
            logger.info("Fucking client status");
        }
        if(source != null && source instanceof ClientPosition) {
            return (ClientPosition) source;
        }

        Integer id = Integer.parseInt((String) source);
        ClientPosition clientPosition = clientPositionService.findById(id);
        logger.info("Client Position : {}", clientPosition);
        return clientPosition;
    }
}
