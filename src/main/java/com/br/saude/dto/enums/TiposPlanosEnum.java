package com.br.saude.dto.enums;

public enum TiposPlanosEnum {
	
	PREMIUM(01, "PREM"),
	BASICO(02, "BASIC"),
	COMPLETO(03, "TOP");
	
	public Integer getCodigo() {
		return codigo;
	}

	public String getSigla() {
		return sigla;
	}

	private Integer codigo;
	private String sigla;
	
	TiposPlanosEnum(Integer codigo, String sigla) {
		this.codigo = codigo;
		this.sigla = sigla;
	}
	
	public static TiposPlanosEnum retornaEnum(String sigla) {
		
		for (TiposPlanosEnum tpPlanos : TiposPlanosEnum.values()) {
			if (tpPlanos.getSigla().equalsIgnoreCase(sigla)) {
				return tpPlanos;
			}
		}
		throw new IllegalArgumentException("Tipo de plano não encontrado");
	}

}
