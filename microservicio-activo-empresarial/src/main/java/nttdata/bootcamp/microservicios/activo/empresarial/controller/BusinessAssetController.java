package nttdata.bootcamp.microservicios.activo.empresarial.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nttdata.bootcamp.microservicios.activo.empresarial.documents.BusinessAsset;
import nttdata.bootcamp.microservicios.activo.empresarial.services.BusinessAssetService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BusinessAssetController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessAssetController.class);
	@Autowired
	private BusinessAssetService service;

	@GetMapping("/all")
	public Flux<BusinessAsset> searchAll() {
		Flux<BusinessAsset> business = service.findAlls();
		LOGGER.info("BUSINESS ASSET registered: " + business);
		return business;
	}

	@GetMapping("/id/{id}")
	public Mono<BusinessAsset> searchById(@PathVariable String id) {
		LOGGER.info("Busines Asset id: " + service.findById(id) + " code : " + id);
		return service.findById(id);
	}

	@PostMapping("/create-business-asset")
	public Mono<BusinessAsset> createBusinessAsset(@Valid @RequestBody BusinessAsset businessAsset) {
		LOGGER.info("BUSINESS ASSET create: " + service.saves(businessAsset));
		return service.saves(businessAsset);
	}
}
