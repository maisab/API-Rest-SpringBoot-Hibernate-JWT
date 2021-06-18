package com.maisab.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.maisab.cursomc.domain.Categoria;
import com.maisab.cursomc.domain.Cidade;
import com.maisab.cursomc.domain.Cliente;
import com.maisab.cursomc.domain.Endereco;
import com.maisab.cursomc.domain.Estado;
import com.maisab.cursomc.domain.Produto;
import com.maisab.cursomc.domain.enums.TipoCliente;
import com.maisab.cursomc.repositories.CategoriaRepository;
import com.maisab.cursomc.repositories.CidadeRepository;
import com.maisab.cursomc.repositories.ClienteRepository;
import com.maisab.cursomc.repositories.EnderecoRepository;
import com.maisab.cursomc.repositories.EstadoRepository;
import com.maisab.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.10);
		Produto p2 = new Produto(null, "Impressora", 800.05);
		Produto p3 = new Produto(null, "Mouse", 80.12);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade (null, "Uberlândia", est1);
		Cidade c2 = new Cidade (null, "São Paulo", est2);
		Cidade c3 = new Cidade (null, "Campinas", est2);
		
		Cliente cliente1 = new Cliente(null,  "Maria", "maria@maria.com", "00000", TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("2233122", "336545"));
		
		Endereco e1 = new Endereco(null, "rua das flores", "300", "apt 01", "Jardim", "30303", c1, cliente1);
		Endereco e2 = new Endereco(null, "avenida", "100", "", "centro", "20522", c2, cliente1);

		cliente1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		categRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
