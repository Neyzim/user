package com.neyzimho.user.bussiness;

import com.neyzimho.user.bussiness.dto.ViaCepDTO;
import com.neyzimho.user.infrastructure.client.ViaCepClient;
import com.neyzimho.user.infrastructure.exception.CepFormatException;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Getter
@Setter
public class ViaCepService {

    private final ViaCepClient viaCepClient;

    public ViaCepDTO searchAddressData(String cep){
        return viaCepClient.searchAddressData(validateCepFormat(cep));
    }

    private String validateCepFormat(String cep){
        String formatedCep= cep.replace(" ", "")
                .replace("-", "");

        if(!formatedCep.matches("[0-9]{8}")){
            throw new CepFormatException("Formato do CEP inválido");
        }
        return formatedCep;
    }
}
