package com.Cats.index.Request;

import com.Cats.index.Entity.Aplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AplicationRequest {

    private Aplication aplication;
    private Long empresaId;
}
