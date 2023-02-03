package com.attornatus.pessoaapi.service;

import com.attornatus.pessoaapi.dto.enderecodto.EnderecoCreateDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoDTO;
import com.attornatus.pessoaapi.dto.enderecodto.EnderecoPessoaDTO;
import com.attornatus.pessoaapi.dto.paginacaodto.PageDTO;
import com.attornatus.pessoaapi.entities.EnderecoEntity;
import com.attornatus.pessoaapi.entities.PessoaEntity;
import com.attornatus.pessoaapi.enums.TipoEndereco;
import com.attornatus.pessoaapi.exception.RegraDeNegocioException;
import com.attornatus.pessoaapi.factory.EnderecoFactory;
import com.attornatus.pessoaapi.factory.PessoaFactory;
import com.attornatus.pessoaapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EnderecoServiceTest {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private PessoaService pessoaService;

    @Mock
    private EnderecoRepository enderecoRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(enderecoService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarCadastrarEnderecoComSucesso() throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();
        PessoaEntity pessoaEntity = PessoaFactory.getPessoaEntity();

        when(pessoaService.buscarPessoaPorId(anyInt())).thenReturn(pessoaEntity);
        when(enderecoRepository.save(any())).thenReturn(enderecoEntity);

        EnderecoDTO enderecoDTO = enderecoService.cadastrarEndereco(EnderecoFactory.getEnderecoCreateDTO());

        assertNotNull(enderecoDTO);
        assertEquals(enderecoEntity.getIdEndereco(), enderecoDTO.getIdEndereco());
        assertEquals(enderecoEntity.getCep(), enderecoDTO.getCep());
    }

    @Test(expected = RegraDeNegocioException.class)
    public void deveTestarCadastrarEnderecoComExcecao() throws RegraDeNegocioException {
        EnderecoEntity enderecoEntity = EnderecoFactory.getEnderecoEntity();
        PessoaEntity pessoaEntity = PessoaFactory.getPessoaEntity();
        EnderecoCreateDTO enderecoCreateDTO = EnderecoFactory.getEnderecoCreateDTO();
        enderecoCreateDTO.setTipoEndereco(TipoEndereco.PRINCIPAL);

        when(pessoaService.buscarPessoaPorId(anyInt())).thenReturn(pessoaEntity);

        EnderecoDTO enderecoDTO = enderecoService.cadastrarEndereco(enderecoCreateDTO);

        assertNotNull(enderecoDTO);
    }

    @Test
    public void deveTestarListarEnderecosPaginadoComSucesso () throws RegraDeNegocioException {
        int numeroPagina = 0;
        int tamanho = 10;
        int idEndereco = 1;
        int idPessoa = 1;
        String nome = "Neil Peart";
        String logradour = "Av. Ipiranga";
        String cep = "98625-223";
        String cidade = "Porto Alegre";

        EnderecoPessoaDTO enderecoPessoaDTO = EnderecoFactory.getEnderecoPessoaDTO();
        PageImpl<EnderecoPessoaDTO> enderecosPessoaDTO = new PageImpl<>(List.of(enderecoPessoaDTO), PageRequest.of(numeroPagina, tamanho), 0);

        when(enderecoRepository.listarEnderecosPaginado(anyInt(), anyInt(), any(), any(), any(), any(), any(),any())).thenReturn(enderecosPessoaDTO);

        PageDTO<EnderecoPessoaDTO> enderecoPessoaDTOPageDTO = enderecoService.listarEnderecosPaginado(idEndereco, idPessoa, nome, logradour, cep, cidade, TipoEndereco.OUTRO, numeroPagina, tamanho);

        assertNotNull(enderecoPessoaDTOPageDTO);
        assertEquals(1, enderecoPessoaDTOPageDTO.getQuantidadePaginas());
    }

    @Test(expected = RegraDeNegocioException.class)
    public void deveTestarListarEnderecosPaginadoComExcecaoPaginaTamanho() throws RegraDeNegocioException {
        int numeroPagina = -1;
        int tamanho = -1;
        int idEndereco = 1;
        int idPessoa = 1;
        String nome = "Neil Peart";
        String logradour = "Av. Ipiranga";
        String cep = "98625-223";
        String cidade = "Porto Alegre";

        enderecoService.listarEnderecosPaginado(idEndereco, idPessoa, nome, logradour, cep, cidade, TipoEndereco.OUTRO, numeroPagina, tamanho);

    }

//    @Test(expected = RegraDeNegocioException.class)
//    public void deveTestarListarEnderecosPaginadoComExcecaoIsEmpty() throws RegraDeNegocioException {
//        int numeroPagina = 0;
//        int tamanho = 10;
//        Integer idEndereco = null;
//        Integer idPessoa = null;
//        String nome = null;
//        String logradour = null;
//        String cep = null;
//        String cidade = null;
//
////        List<EnderecoPessoaDTO> listaVazia = new ArrayList<>();
////
////        PageDTO<EnderecoPessoaDTO> enderecoPessoaDTOPageDTO = new PageDTO<>(0L, 0, 0, tamanho, listaVazia);
////
////        PageDTO<EnderecoPessoaDTO> pageDTO = enderecoService.listarEnderecosPaginado(idEndereco, idPessoa, nome, logradour, cep, cidade, TipoEndereco.OUTRO, numeroPagina, tamanho);
////
////        assertNull(pageDTO);
////        assertEquals(pageDTO, enderecoPessoaDTOPageDTO);
//
//        when(enderecoRepository.listarEnderecosPaginado(anyInt(), anyInt(), any(), any(), any(), any(), any(),any())).thenReturn(Page.empty());
//
//        PageDTO<EnderecoPessoaDTO> enderecoPessoaDTO = enderecoService.listarEnderecosPaginado(idEndereco, idPessoa, nome, logradour, cep, cidade, TipoEndereco.OUTRO, numeroPagina, tamanho);
//
//        verify(enderecoRepository, times(1)).listarEnderecosPaginado(anyInt(), anyInt(), any(), any(), any(), any(), any(),any());
//    }
}
