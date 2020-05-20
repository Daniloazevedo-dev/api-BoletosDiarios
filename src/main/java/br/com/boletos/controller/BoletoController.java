package br.com.boletos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.boletos.model.Boleto;
import br.com.boletos.repository.BoletoRepository;

@RestController
@RequestMapping(value = "/boleto")
public class BoletoController {
	
	@Autowired
	private BoletoRepository boletoRepository;
	
	@RequestMapping(produces = "application/json")
	public Boleto save(@RequestBody Boleto boleto) throws ParseException{
		
		return boletoRepository.save(boleto);
	}
	
	@GetMapping(produces = "application/json") 
	public List<Boleto> findAll(){
		
		return (List<Boleto>) boletoRepository.findAll();
	}
	
	@GetMapping(value = "/diario", produces = "application/json") 
	public List<Boleto> boletosDiario(){
		
		List<Boleto> boletos = (List<Boleto>) boletoRepository.findAll();
		List<Boleto> boletosDiarios = new ArrayList<Boleto>();
		
		for (Boleto boleto : boletos) {
			Date data = new Date(System.currentTimeMillis());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String dataFormatada = format.format(data);
		
			if(boleto.getVencimento().equals(dataFormatada)) {
				boletosDiarios.add(boleto);
			}
		}
		
		return boletosDiarios;
	}
	
	@DeleteMapping(value ="/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {
		
		boletoRepository.deleteById(id);
		
		return "Boleto deletado com sucesso.";
	}
	
	@PutMapping(produces = "application/json")
	public Boleto update(@RequestBody Boleto boleto){
		
		return boletoRepository.save(boleto);
	}

}
