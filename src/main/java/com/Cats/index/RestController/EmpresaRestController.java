package com.Cats.index.RestController;

import com.Cats.index.Entity.Empresa;
import com.Cats.index.Entity.User;
import com.Cats.index.Request.EmpresaRequest;
import com.Cats.index.Request.ServiceRequest;
import com.Cats.index.Response.EmpresaResponse;
import com.Cats.index.Response.ServiceResponse;
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
    public List<User> getUsers(@PathVariable Integer id){
        return empresaService.getUsers(id);
    }

    @PostMapping("/addService")
    public ResponseEntity<ServiceResponse> addService(@RequestBody ServiceRequest request){
        return ResponseEntity.ok(empresaService.addService(request));
    }
}
