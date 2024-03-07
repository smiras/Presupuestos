package com.Cats.index.Service;

import com.Cats.index.Entity.Aplication;
import com.Cats.index.Entity.Empresa;
import com.Cats.index.Entity.User;
import com.Cats.index.Repository.EmpresaRepository;
import com.Cats.index.Request.EmpresaRequest;
import com.Cats.index.Request.AplicationRequest;
import com.Cats.index.Response.EmpresaResponse;
import com.Cats.index.Response.AplicationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public EmpresaResponse crearEmpresa(EmpresaRequest request) {
        EmpresaResponse empresaResponse = new EmpresaResponse();
        if (Objects.isNull(request.getId())) {
            if (empresaRepository.existsByEmail(request.getEmail())) {
                empresaResponse.setMensaje("Ya existe una empresa con ese email");
                empresaResponse.setCreado(false);
            } else {
                Empresa empresa = Empresa.builder()
                        .nombre(request.getNombre())
                        .domicilio(request.getDomicilio())
                        .telefono(request.getTelefono())
                        .email(request.getEmail())
                        .build();
                empresaRepository.save(empresa);
                empresaResponse.setMensaje("Empresa creada correctamente");
                empresaResponse.setCreado(true);
            }
        } else {
            Empresa empresa = Empresa.builder()
                    .id(request.getId())
                    .nombre(request.getNombre())
                    .domicilio(request.getDomicilio())
                    .telefono(request.getTelefono())
                    .email(request.getEmail())
                    .build();
            empresaRepository.save(empresa);
            empresaResponse.setMensaje("Empresa guardada correctamente");
            empresaResponse.setCreado(true);
        }
        return empresaResponse;
    }

    public List<Empresa> listarEmpresas(){
        return empresaRepository.findAll();
    }

    public Empresa findByName(String name){
        return empresaRepository.findByNombre(name);
    }

    public Empresa findById(Integer id){
        return empresaRepository.findById(id).get();
    }

    public EmpresaResponse eliminar(Integer id){
        empresaRepository.deleteById(id);
        return EmpresaResponse.builder()
                .mensaje("Empresa eliminada correctamente")
                .build();
    }

    public List<User> getUsers(Integer empresaId){
        Optional<Empresa> empresa = empresaRepository.findById(empresaId);
        return empresa.map(Empresa::getUsuarios).orElse(new ArrayList<>());
    }

    public AplicationResponse addService(AplicationRequest request){
       Empresa empresa = empresaRepository.findById(request.getEmpresaId());
        List<Aplication> aplications;
        String mensaje;
        boolean creado = true;
       if(empresa.getAplications()==null){
           aplications = new ArrayList<>();
           aplications.add(request.getAplication());
           mensaje = "Servicio añadido correctamente";
       } else {
           aplications = empresa.getAplications();
           if (isServiceInList(request.getAplication(), aplications)) {
               mensaje = "El servicio ya se encuentra habilitado";
               creado = false;
           } else {
               aplications.add(request.getAplication());
               empresa.setAplications(aplications);
               mensaje = "Servicio añadido correctamente";
           }
       }
       empresaRepository.save(empresa);
        return AplicationResponse.builder()
                .creado(creado)
                .mensaje(mensaje)
                .build();
    }

    public static boolean isServiceInList(Aplication aplications, List<Aplication> serviceApp) {
        for (Aplication a : serviceApp) {
            if (a.equals(aplications)) {
                return true;
            }
        }
        return false;
    }

}
