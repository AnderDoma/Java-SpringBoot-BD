package br.com.saude.dto.enums;

public enum AtendimentosEnum {
	
	EMERGENCIA(01, "EMER"),
	CONSULTA(02, "CONSU"),
	OUTROS(03, "OUTROS");
	
	public Integer getCodigo() {
		return codigo;
	}

	public String getSigla() {
		return sigla;
	}

	private Integer codigo;
	private String sigla;
	
	AtendimentosEnum(Integer codigo, String sigla) {
		this.codigo = codigo;
		this.sigla = sigla;
	}
	
	public static AtendimentosEnum retornaEnum(String sigla) {
		
		for (AtendimentosEnum tpPlanos : AtendimentosEnum.values()) {
			if (tpPlanos.getSigla().equalsIgnoreCase(sigla)) {
				return tpPlanos;
			}
		}
		throw new IllegalArgumentException("Tipo de plano não encontrado");
	}

}
