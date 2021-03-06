package Test.Controller;

import Test.DTO.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RestController
@RequestMapping(value = "/spt")
public class Controller {
    @Autowired
    private Validator validator;

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test(@RequestBody Document testDocument) {
        Set<ConstraintViolation<Document>> errors = validator.validate(testDocument);
        if (errors.isEmpty()) {
            return ResponseEntity.ok("Test OK");
        } else {
            return ResponseEntity.badRequest().body("Bad test");
        }
    }
}
