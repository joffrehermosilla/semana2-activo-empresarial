package nttdata.bootcamp.microservicios.activo.empresarial.controller;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/update-business-asset/{id}")
	public ResponseEntity<Mono<?>> updateBusinessAsset(@PathVariable String id,
			@Valid @RequestBody BusinessAsset businessAsset) {
		Mono.just(businessAsset).doOnNext(t -> {
			businessAsset.setId(id);
			t.setCreateAt(new Date());
		}).onErrorReturn(businessAsset).onErrorResume(e -> Mono.just(businessAsset))
			.onErrorMap(f -> new InterruptedException(f.getMessage())).subscribe(x -> LOGGER.info(x.toString()));
		Mono<BusinessAsset> newbusinessAsset = service.saves(businessAsset);
		if (newbusinessAsset!= null) {
		return new ResponseEntity<>(newbusinessAsset, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(Mono.just(new BusinessAsset()), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@DeleteMapping("/eliminar-Business-Asset/{id}")
	public ResponseEntity<Mono<Void>> deleteBusinessAsset(@PathVariable String id) {
	BusinessAsset businessAsset = new BusinessAsset();
	businessAsset.setId(id);
	Mono<BusinessAsset> newBusinessAsset = service.findById(id);
	newBusinessAsset.subscribe();
	Mono<Void> test = service.delete(businessAsset);
	test.subscribe();
	return ResponseEntity.noContent().build();
	}
}
