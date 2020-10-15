package oporto.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import oporto.dominio.Agencia;
import oporto.dominio.Banco;
import oporto.dominio.Cliente;
import oporto.util.Util;

public class Cadastro {
	private Banco banco;
	private Agencia agencia = new Agencia();
	private Cliente cliente;
	private int codigo;
	List<Banco> bancos = new ArrayList<Banco>();
	List<Agencia> agencias = new ArrayList<Agencia>();
	Scanner entrada = new Scanner(System.in);

	public void cadastrarBanco() {
		Util.saidaTexto("Entre com os dados do Banco\n");
		String dados = entrada.nextLine();
		banco = new Banco();
		banco.setNome(dados);
		bancos.add(banco);
	}

	public void listarBancos() {
		if (bancos.isEmpty()) {
			Util.saidaTexto("Oh não, nenhum Banco Cadastrado");
		} else {
			Util.saidaTexto("Relatório de Bancos Cadastrados\n");
		}
		for (Banco banco : bancos) {
			Util.saidaTexto(bancos.indexOf(banco) + "-" + banco.getNome());
		}
	}

	public void mostrarBuscarBanco(int codigo) {
		try {
			Util.saidaTexto(codigo + "-" + bancos.get(codigo).getNome());
		} catch (IndexOutOfBoundsException e) {
			Util.saidaTexto("Foi buscado um banco inesistente.");
		}
	}

	public Banco buscaPorCodigoBanco(int codigo) {
		Banco codigoBanco = bancos.get(codigo);
		return codigoBanco;
	}	

	public Banco vincularAgenciaAoBanco() {
		Util.saidaTexto("Entre com o código do banco");
		codigo = entrada.nextInt();
		banco = buscaPorCodigoBanco(codigo);
		//Limpar Buffer
		if(entrada.hasNextLine()) {
			entrada.nextLine();
		}
		return banco;
	}

	public void cadastroAgencia() {
		if (bancos.size() > 0) {			
			Util.saidaTexto("Entre com a identificação da Agência");
			String identificacaoAgencia = entrada.nextLine();
			agencia = new Agencia();
			agencia.setIdentificacaoAgencia(identificacaoAgencia);			
			Util.saidaTexto("Para vincular a agência ao banco agora. Digite 0, ou qualquer outro número para sair.");
			codigo = entrada.nextInt();
			banco = vincularAgenciaAoBanco();

			agencia.setBanco(banco);
			agencias.add(agencia);
		} else {
			Util.saidaTexto("É necessário ter um banco cadastrado para cadastrar agências");
		}

	}

	public void listarAgencia() {
		if (agencias.isEmpty()) {
			Util.saidaTexto("Não há nenhuma agência cadastrada.");
		} else {
			Util.saidaTexto("Relatório das Agências Cadastradas.");
		}
		for (Agencia agencia : agencias) {
			Util.saidaTexto(agencia.getBanco().getNome() + "-" + agencias.indexOf(agencia) + "-"
					+ agencia.getIdentificacaoAgencia());
		}
	}

}