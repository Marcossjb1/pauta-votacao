package com.votacao.pauta.validation;

import com.votacao.pauta.exception.BadRequestException;
import com.votacao.pauta.model.Pauta;
import com.votacao.pauta.model.Usuario;
import com.votacao.pauta.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PautaValidator {
    @Autowired
    private PautaRepository pautaRepository;
    public void validarDescricaoPauta(Pauta pauta){
        if (pauta.getDescricao().isEmpty()) {
            throw new BadRequestException("É necessário inserir uma descrição na pauta.");
        }
    }
}
