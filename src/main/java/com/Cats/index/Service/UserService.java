package com.Cats.index.Service;

import com.Cats.index.Entity.User;
import com.Cats.index.Enum.Role;
import com.Cats.index.Enum.Services;
import com.Cats.index.Repository.UserRepository;
import com.Cats.index.Request.ServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User obtenerUsuarioAutenticado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public User saveUser(User user){
        User userActual = this.obtenerUsuarioAutenticado();
        if(Objects.equals(userActual.getId(), user.getId())){
            userActual.setName(user.getName());
            userActual.setUsername(user.getUsername());
            userActual.setPassword(passwordEncoder.encode(user.getPassword()));
            userActual.setEmpresa(user.getEmpresa());
            userActual.setDomicilio(user.getDomicilio());
            userActual.setTelefono(user.getTelefono());
            userActual.setEmail(user.getEmail());
        }
        return userRepository.save(userActual);
    }

    public User saveFirstUser(User user){
        user.setActive(true);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean checkUsername(String username){
        return userRepository.countByUsername(username) > 0;
    }

    public String habilityServices(ServiceRequest serviceRequest){
        User user = userRepository.findById(serviceRequest.getUserId());
        boolean insert = false;

        for(Services s: user.getServices()){
            if (s.equals(serviceRequest.getService())) {
                insert = true;
                break;
            }
        }

        if(!insert){
            List<Services> newServices = user.getServices();
            newServices.add(serviceRequest.getService());
            user.setServices(newServices);
            userRepository.save(user);

            return "Servicio habilitado correctamente";
        } else return "El servicio ya se encontraba habilitado";

    }
}
