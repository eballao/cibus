package br.com.cibus;


import br.com.cibus.tipodecozinha.RestaurantesPorTipoDeCozinha;
import br.com.cibus.tipodecozinha.TipoDeCozinhaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TipoDeCozinhaRepositoryTest {

    @Autowired
    TipoDeCozinhaRepository tipoDeCozinhaRepository;

    @Test
    void deveContarTotalDeRestaurantes() {
        List<RestaurantesPorTipoDeCozinha> restaurantesPorTipoDeCozinhas = tipoDeCozinhaRepository.contaQuantidadeRestaurantesPorTipoDeCozinha();

        assertThat(restaurantesPorTipoDeCozinhas).hasSize(4);

        assertThat(restaurantesPorTipoDeCozinhas)
                .extracting(RestaurantesPorTipoDeCozinha::getNomeDoTipoDeCozinha, RestaurantesPorTipoDeCozinha::getQuantidadeDeRestaurantes)
                .contains(tuple("Árabe", 3l), tuple("Baiana", 2l), tuple("Chinesa", 3l), tuple("Italiana", 4l));

    }
}
