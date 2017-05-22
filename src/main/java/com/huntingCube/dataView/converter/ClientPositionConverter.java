package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.ClientPosition;
import com.huntingCube.dataView.service.ClientPositionService;
import com.huntingCube.utility.HuntingCubeUtility;
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

    static final Logger logger = LoggerFactory.getLogger(ClientPositionConverter.class);

    @Autowired
    ClientPositionService clientPositionService;

    @Override
    public ClientPosition convert(Object source) {
        if(source != null && source instanceof ClientPosition) {
            logger.info("(ClientPosition) source>>>>>>>>>>>>>"+(ClientPosition) source);
            return (ClientPosition) source;
        }

        if(source != null && source instanceof String && HuntingCubeUtility.isNotEmptyOrNull((String) source)) {
            logger.info("(String) source>>>>>>>>>>>>"+(String) source);
            Integer id = Integer.parseInt((String) source);
            ClientPosition clientPosition = clientPositionService.findById(id);
            logger.info("clientPosition<><><><><><><><><><><"+clientPosition);
            logger.info("Client Position : {}", clientPosition);
            return clientPosition;
        } else {
            return null;
        }
    }
}
