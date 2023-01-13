package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogTestController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @GetMapping("/test")
    public void test(){
        logger.info("info");
        logger.trace("info");
        logger.debug("info");
        logger.warn("info");
        logger.error("info");
    }
}
