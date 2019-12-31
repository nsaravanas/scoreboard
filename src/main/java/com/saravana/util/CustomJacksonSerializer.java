package com.saravana.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CustomJacksonSerializer {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper mapper;


    @PostConstruct
    private void addObjectId() {
        LOG.info("adding {} serializer", ObjectId.class);
        final SimpleModule sm = new SimpleModule();
        sm.addSerializer(ObjectId.class, new ObjectIdSerializer());
        mapper.registerModule(sm);
    }

}
