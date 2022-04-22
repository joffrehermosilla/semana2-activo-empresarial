package nttdata.bootcamp.microservicios.activo.empresarial.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nttdata.bootcamp.microservicios.activo.empresarial.documents.BusinessAsset;
import nttdata.bootcamp.microservicios.activo.empresarial.repository.BusinessAssetRepository;
import nttdata.bootcamp.microservicios.activo.empresarial.services.BusinessAssetService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BusinessAssetImpl implements BusinessAssetService {

	@Autowired
	BusinessAssetRepository repository;

	@Override
	public Mono<BusinessAsset> findById(String id) {

		return repository.findById(id);
	}

	@Override
	public Flux<BusinessAsset> findAlls() {

		return repository.findAll();
	}

	@Override
	public Mono<BusinessAsset> saves(BusinessAsset document) {

		return repository.save(document);
	}

	@Override
	public Mono<Void> delete(BusinessAsset document) {

		return repository.delete(document);
	}

}
