package com.Cats.index.RestController;

import com.Cats.index.Enum.Services;
import com.Cats.index.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/services")
public class ServicesRestController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/getServices")
    public List<String> getAllServices() {
        return Arrays.stream(Services.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/getServicesByCompany/{empresaId}")
    public List<Services> getServicesByCompany(@PathVariable Long empresaId) {
        return serviceService.getServicesByCompany(empresaId);
    }
}
