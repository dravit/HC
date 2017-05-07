package com.huntingCube.dataView.converter;

import com.huntingCube.dataView.model.Program;
import com.huntingCube.dataView.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by dgup27 on 1/23/2017.
 */
@Component
public class ProgramConverter implements Converter<Object, Program> {

    @Autowired
    ProgramService programService;

    @Override
    public Program convert(Object source) {
        Integer id = Integer.parseInt((String) source);
        Program program = programService.findById(id);
        return program;
    }
}
