package br.com.pdcase.pagamentos1.repository;

import br.com.pdcase.pagamentos1.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentosRepository extends JpaRepository<Pagamento, Long> {

}
