package com.Cats.index.Service;

import com.Cats.index.Entity.Aplication;
import com.Cats.index.Entity.Empresa;
import com.Cats.index.Entity.User;
import com.Cats.index.Repository.AplicationRepository;
import com.Cats.index.Repository.EmpresaRepository;
import com.Cats.index.Repository.UserRepository;
import com.Cats.index.Request.AplicationRequest;
import com.Cats.index.Response.AplicationResponse;
import com.Cats.index.Response.EmpresaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AplicationService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private AplicationRepository aplicationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Aplication> getAll(){
        return aplicationRepository.findAll();
    }

    public List<Aplication> getAplicationsByCompany(Long usuarioId) {
        Empresa empresa = empresaRepository.findById(usuarioId);
        return empresa.getAplications();
    }

    public AplicationResponse saveApp(AplicationRequest request){
        if(aplicationRepository.existsByPath(request.getAplication().getPath())){
            return AplicationResponse.builder()
                    .creado(true)
                    .mensaje("ya existe la aplicación")
                    .build();
        } else {
            Aplication aplication = Aplication.builder()
                    .nombre(request.getAplication().getNombre())
                    .path(request.getAplication().getPath())
                    .build();
            aplicationRepository.save(aplication);
            return AplicationResponse.builder()
                    .creado(true)
                    .mensaje("Aplicación guardada correctamente")
                    .build();
        }
    }

    public AplicationResponse delete(Integer id){
        aplicationRepository.deleteById(id);
        return AplicationResponse.builder()
                .mensaje("Empresa eliminada correctamente")
                .build();
    }

}

