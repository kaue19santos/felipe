package br.com.pdcase.pagamentos1.controller;

import br.com.pdcase.pagamentos1.dto.PagamentoDto;
import br.com.pdcase.pagamentos1.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/pagamentos1")
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService service;

    @GetMapping
    public List<PagamentoDto> listar(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> BuscarPorID(@PathVariable @NotNull Long id) {
        PagamentoDto dto = service.getByID(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrar (@RequestBody @Valid PagamentoDto dto,
                                                   UriComponentsBuilder uriBuilder){
        PagamentoDto pagamento = service.createPayment(dto);
        var uri = uriBuilder.path("/pagamentos1/{id}").buildAndExpand(pagamento.getId()).toUri();

        return ResponseEntity.created(uri).body(pagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> atualizarRegistro(@PathVariable @NotNull Long id,
                                                          @RequestBody @Valid PagamentoDto dto){
        PagamentoDto pagamentoAtualizado = service.updatePayment(id, dto);
        return ResponseEntity.ok(pagamentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PagamentoDto> deletarRegistro(@PathVariable @NotNull Long id) {
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
