package ro.ubb.catalog.web.controller;

import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.catalog.core.converter.ArtistConverter;
import ro.ubb.catalog.core.converter.UserConverter;
import ro.ubb.catalog.core.service.ArtistService;

import java.util.concurrent.ExecutorService;

@RestController
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ExecutorService executor;
    @Autowired
    private ArtistConverter artistConverter;
    private final Logger logger = LoggerFactory.getLogger(ArtistController.class);

}
