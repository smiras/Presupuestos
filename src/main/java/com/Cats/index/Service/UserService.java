package com.Cats.index.Service;

import com.Cats.index.Entity.Empresa;
import com.Cats.index.Entity.User;
import com.Cats.index.Enum.Role;
import com.Cats.index.Repository.UserRepository;
import com.Cats.index.Request.UserRequest;
import com.Cats.index.Response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EmpresaService empresaService;

    public User obtenerUsuarioAutenticado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public UserResponse saveUser(UserRequest request){
        UserResponse response = new UserResponse();
        if (Objects.isNull(request.getId())) {
            if (userRepository.existsByUsername(request.getUsername())) {
                response.setMensaje("Ya existe ese nombre de usuario");
                response.setCreado(false);
            } else {
                User user = User.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .name(request.getName())
                        .role(request.getRole())
                        .empresa(empresaService.findByName(request.getEmpresa()))
                        .build();
                userRepository.save(user);
                response.setMensaje("Usuario creado correctamente");
                response.setCreado(true);
            }
        }else {
            User user = User.builder()
                    .id(request.getId())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .role(request.getRole())
                    .empresa(empresaService.findByName(request.getEmpresa()))
                    .build();
            userRepository.save(user);
            response.setMensaje("Usuario guardado correctamente");
            response.setCreado(true);
        }
        return response;
    }

    public boolean checkSuper(Long userId){
        User user = userRepository.findById(userId);
        return user.getRole().equals(Role.SUPERADMIN);
    }

    public List<User> listarUsuarios(){
        return userRepository.findAll();
    }

    public User findById(Integer id){
        return userRepository.findById(id).get();
    }

    public UserResponse eliminar(Integer id){
        userRepository.deleteById(id);
        return UserResponse.builder()
                .mensaje("Empresa eliminada correctamente")
                .build();
    }

    public Empresa findCompanyByUserId(Long userId){
        User user = userRepository.findById(userId);
        return user.getEmpresa();
    }

    public Boolean checkUsername(String username){
        return userRepository.existsByUsername(username);
    }
}
