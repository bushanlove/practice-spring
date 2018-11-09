package com.czs.practice.springlearn.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DtoController {

    private static Logger logger = LoggerFactory.getLogger(DtoController.class);

    @RequestMapping("/dtohello")
    public String index() {
        for(int i=3;i<5;i++) {
            logger.debug("debug" + i);
            logger.info("info" + i);
            logger.warn("warn" + i);
            logger.error("error" + i);
        }
        return "sto request!";
    }

}
