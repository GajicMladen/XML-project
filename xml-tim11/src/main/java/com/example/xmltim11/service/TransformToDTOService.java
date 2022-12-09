package com.example.xmltim11.service;

import com.example.xmltim11.dto.UserDTO;
import com.example.xmltim11.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransformToDTOService {

    public <T> List<Object> transformToDTOList(List<T> regularList){
        List<Object> ret= new ArrayList<>();
        for (T item: regularList) {
            if( item instanceof User)
                ret.add( new UserDTO((User)item));
            //for other types
        }
        return ret;
    }

}
