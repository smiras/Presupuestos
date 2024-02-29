package com.Cats.index.RestController;

import com.Cats.index.Entity.User;
import com.Cats.index.Request.ServiceRequest;
import com.Cats.index.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> guardarUsuario(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping("/firstSave")
    public ResponseEntity<User> firstSave(@RequestBody User user){
        return ResponseEntity.ok(userService.saveFirstUser(user));
    }

    @PostMapping("/checkUsername")
    public boolean checkUsername(@RequestBody String username){
        return userService.checkUsername(username);
    }

    @PostMapping("/habilityService")
    ResponseEntity<String> habilityService(ServiceRequest serviceRequest){
        return ResponseEntity.ok(userService.habilityServices(serviceRequest));
    }


}
