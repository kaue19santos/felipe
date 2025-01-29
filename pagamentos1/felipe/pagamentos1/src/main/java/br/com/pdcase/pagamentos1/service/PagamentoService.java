package br.com.pdcase.pagamentos1.service;

import br.com.pdcase.pagamentos1.dto.PagamentoDto;
import br.com.pdcase.pagamentos1.model.Pagamento;
import br.com.pdcase.pagamentos1.model.Status;
import br.com.pdcase.pagamentos1.repository.PagamentosRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentosRepository repository;

    private final ModelMapper modelMapper;

    public List<PagamentoDto> getAll() {
        return repository
                .findAll()
                .stream()
                .map(p -> modelMapper.map(p, PagamentoDto.class))
                .collect(Collectors.toList());
    }

    public PagamentoDto getByID(Long id){
        Optional<Pagamento> optionalpagamento = repository.findById(id);
        Pagamento pagamento = optionalpagamento.orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto createPayment(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto updatePayment(Long id, PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}
