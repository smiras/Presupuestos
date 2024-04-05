package com.Cats.index.RestController;

import com.Cats.index.Entity.Empresa;
import com.Cats.index.Entity.User;
import com.Cats.index.Request.UserRequest;
import com.Cats.index.Response.UserResponse;
import com.Cats.index.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUser")
    public ResponseEntity<User> obtenrUsuario(){
        User user = userService.obtenerUsuarioAutenticado();
        user.setPassword("");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/save")
    public ResponseEntity<UserResponse> guardarUsuario(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.saveUser(request));
    }

    @PostMapping("/checkSuper")
    public boolean checkSuper(@RequestBody Long userId){
        return userService.checkSuper(userId);
    }

    @GetMapping("/list")
    public List<User> list(){
        return userService.listarUsuarios();
    }

    @GetMapping("/get/{id}")
    public User getEmpresa(@PathVariable Integer id){
        return userService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public UserResponse delete(@PathVariable Integer id){
        return userService.eliminar(id);
    }

    @GetMapping("/getCompanyByUserId/{id}")
    public Empresa getEmpresaByUserId(@PathVariable Long id){
        return userService.findCompanyByUserId(id);
    }

    @PostMapping("/checkUsername")
    public Boolean checkUsername(@RequestBody UserRequest user){
        return userService.checkUsername(user.getUsername());
    }
}
