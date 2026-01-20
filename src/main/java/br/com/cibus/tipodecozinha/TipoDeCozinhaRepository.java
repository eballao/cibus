package br.com.cibus.tipodecozinha;

import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface TipoDeCozinhaRepository extends JpaRepository<TipoDeCozinha, Long> {

    @Query(nativeQuery = true, value = """
            select tc.nome as nomeDoTipoDeCozinha,
                count(r.id) as quantidadeDeRestaurantes
            from tipo_de_cozinha tc
                left join restaurante r
                    on tc.id = r.tipo_de_cozinha_id
                group by tc.id
                order by tc.nome
            """ )
    List<RestaurantesPorTipoDeCozinha> contaQuantidadeRestaurantesPorTipoDeCozinha();

}