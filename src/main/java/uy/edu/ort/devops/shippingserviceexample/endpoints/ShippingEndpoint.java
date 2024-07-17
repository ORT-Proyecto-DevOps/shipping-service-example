package uy.edu.ort.devops.shippingserviceexample.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uy.edu.ort.devops.shippingserviceexample.domain.Shipping;
import uy.edu.ort.devops.shippingserviceexample.logic.ShippingLogic;

@RestController
@RequestMapping("/shipping")
public class ShippingEndpoint {

    @Autowired
    private ShippingLogic logic;

    @PostMapping(path = "/{id}")
    public ResponseEntity create(@PathVariable("id") String id) {
        logic.addShipping(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable("id") String id) {
        if (logic.hasShipping(id)) {
            Shipping shipping = logic.getShipping(id);
            return ResponseEntity.ok(shipping);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping(path = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("{\"status\":\"UP\"}");
    }
}