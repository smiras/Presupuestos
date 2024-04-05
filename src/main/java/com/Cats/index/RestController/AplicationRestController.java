package com.Cats.index.RestController;

import com.Cats.index.Entity.Aplication;
import com.Cats.index.Request.AplicationRequest;
import com.Cats.index.Response.AplicationResponse;
import com.Cats.index.Service.AplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/aplications")
public class AplicationRestController {

    @Autowired
    private AplicationService aplicationService;

    @GetMapping("/getAplications")
    public List<Aplication> getAllAplications() {
        return aplicationService.getAll();
    }

    @GetMapping("/getServicesByCompany/{usuarioId}")
    public List<Aplication> getAplicationsByCompany(@PathVariable Long usuarioId) {
        return aplicationService.getAplicationsByCompany(usuarioId);
    }

    @PostMapping("/save")
    public ResponseEntity<AplicationResponse> saveApp(@RequestBody AplicationRequest request){
        return ResponseEntity.ok(aplicationService.saveApp(request));
    }

    @GetMapping("/delete/{appId}")
    public ResponseEntity<AplicationResponse> deleteApp(@PathVariable Integer appId){
        return ResponseEntity.ok(aplicationService.delete(appId));
    }
}
