package com.attornatus.pessoaapi.service;

import com.attornatus.pessoaapi.dto.paginacaodto.PageDTO;
import com.attornatus.pessoaapi.dto.pessoadto.PessoaDTO;
import com.attornatus.pessoaapi.entities.PessoaEntity;
import com.attornatus.pessoaapi.exception.RegraDeNegocioException;
import com.attornatus.pessoaapi.factory.PessoaFactory;
import com.attornatus.pessoaapi.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    public void deveTestarCadastrarPessoaComSucesso () {
        PessoaEntity pessoaEntity = PessoaFactory.getPessoaEntity();

        when(pessoaRepository.save(any())).thenReturn(pessoaEntity);

        PessoaDTO pessoaDTO = pessoaService.cadastrarPessoa(PessoaFactory.getPessoaCreateDTO());

        assertNotNull(pessoaDTO);
        assertEquals(pessoaEntity.getNome(), pessoaDTO.getNome());
    }

    @Test
    public void deveEditarPessoaPessoaComSucesso () throws RegraDeNegocioException {
        PessoaEntity pessoaEntity = PessoaFactory.getPessoaEntity();

        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoaEntity));
        when(pessoaRepository.save(any())).thenReturn(pessoaEntity);

        PessoaDTO pessoaDTO = pessoaService.editarPessoa(PessoaFactory.getPessoaEntity().getIdPessoa(), PessoaFactory.getPessoaCreateDTO());

        assertNotNull(pessoaDTO);
        assertEquals(pessoaEntity.getNome(), pessoaDTO.getNome());
    }

    @Test
    public void deveTesterConsultarUmaPessoaComSucesso () throws RegraDeNegocioException {

        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(PessoaFactory.getPessoaEntity()));

        PessoaDTO pessoaDTO = pessoaService.consultarUmaPessoa(PessoaFactory.getPessoaEntity().getIdPessoa());

        assertNotNull(pessoaDTO);
        assertEquals(1, pessoaDTO.getIdPessoa());
    }

    @Test
    public void deveTestarListarPessoasComPaginacaoComSucesso () throws RegraDeNegocioException {
        int numeroPagina = 0;
        int tamanho = 10;
        int idPessoa = 1;
        String nomePessoa = "Neil Peart";
        LocalDate dataNascimento = LocalDate.of(1990, 02, 01);


        PessoaEntity pessoaEntity = PessoaFactory.getPessoaEntity();
        PessoaDTO pessoaDTO = PessoaFactory.getPessoaDTO();
        Page<PessoaEntity> pessoaEntities = new PageImpl<>(List.of(pessoaEntity), PageRequest.of(numeroPagina, tamanho), 0);
        PageImpl<PessoaDTO> pessoasDTO = new PageImpl<>(List.of(pessoaDTO), PageRequest.of(numeroPagina, tamanho), 0);

        when(pessoaRepository.listarPessoasComPaginacao(anyInt(), any(), any(), any())).thenReturn(pessoaEntities);

        PageDTO<PessoaDTO> pessoaDTOS = pessoaService.listarPessoasComPaginacao(idPessoa, nomePessoa, dataNascimento, numeroPagina, tamanho);

        assertNotNull(pessoaDTOS);
        assertEquals(1, pessoaDTOS.getQuantidadePaginas());
    }

    @Test(expected = RegraDeNegocioException.class)
    public void deveTestarListarPessoasComPaginacaoComExcecao () throws RegraDeNegocioException {
        int numeroPagina = -1;
        int tamanho = -1;
        int idPessoa = 1;
        String nomePessoa = "Neil Peart";
        LocalDate dataNascimento = LocalDate.of(1990, 02, 01);

        pessoaService.listarPessoasComPaginacao(idPessoa, nomePessoa, dataNascimento, numeroPagina, tamanho);
    }

    @Test(expected = RegraDeNegocioException.class)
    public void deveTestarListarPessoasComPaginadoComExcecaoIsEmpty() throws RegraDeNegocioException {

        int numeroPagina = 0;
        int tamanho = 10;
        PageRequest pageRequest = PageRequest.of(numeroPagina, tamanho);

        when(pessoaRepository.listarPessoasComPaginacao(null, null, null, pageRequest)).thenReturn(Page.empty());

        pessoaService.listarPessoasComPaginacao(null, null, null, numeroPagina, tamanho);
    }

    @Test
    public void deveTesterBuscarPessoaPorIdComSucesso () throws RegraDeNegocioException {

        when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(PessoaFactory.getPessoaEntity()));

        PessoaEntity pessoaEntity = pessoaService.buscarPessoaPorId(PessoaFactory.getPessoaEntity().getIdPessoa());

        assertNotNull(pessoaEntity);
        assertEquals(1, pessoaEntity.getIdPessoa());
    }
}
