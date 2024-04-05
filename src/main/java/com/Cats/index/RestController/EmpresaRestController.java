package com.Cats.index.RestController;

import com.Cats.index.Entity.Empresa;
import com.Cats.index.Entity.User;
import com.Cats.index.Request.EmpresaRequest;
import com.Cats.index.Request.AplicationRequest;
import com.Cats.index.Response.EmpresaResponse;
import com.Cats.index.Response.AplicationResponse;
import com.Cats.index.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/company")
public class EmpresaRestController {

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/save")
    public ResponseEntity<EmpresaResponse> saveCompany(@RequestBody EmpresaRequest request) {
       return ResponseEntity.ok( empresaService.crearEmpresa(request));
    }

    @GetMapping("/list")
    public List<Empresa> listarEmpresas(){
        return empresaService.listarEmpresas();
    }

    @GetMapping("/get/{id}")
    public Empresa getEmpresa(@PathVariable Integer id){
        return empresaService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public EmpresaResponse delete(@PathVariable Integer id){
        return empresaService.eliminar(id);
    }

    @GetMapping("/getUsers/{id}")
    public List<User> getUsers(@PathVariable Long id){
        return empresaService.getUsers(id);
    }

    @PostMapping("/addApp")
    public ResponseEntity<AplicationResponse> addService(@RequestBody AplicationRequest request){
        return ResponseEntity.ok(empresaService.addService(request));
    }

    @GetMapping("/getByUserId/{userId}")
    public Empresa getByUserId(@PathVariable Long userId){
        return empresaService.findByUserId(userId);
    }

}
