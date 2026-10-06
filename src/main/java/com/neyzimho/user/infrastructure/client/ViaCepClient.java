package com.neyzimho.user.infrastructure.client;

import com.neyzimho.user.bussiness.dto.ViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "via-cep", url = "${viacep.api.url}")
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json")
    ViaCepDTO searchAddressData(@PathVariable("cep")String cep);


}
