package com.Cats.index.Service;

import com.Cats.index.Entity.Empresa;
import com.Cats.index.Enum.Services;
import com.Cats.index.Repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceService {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<Services> getServicesByCompany(Long empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId);
        return empresa.getServices();
    }

}

