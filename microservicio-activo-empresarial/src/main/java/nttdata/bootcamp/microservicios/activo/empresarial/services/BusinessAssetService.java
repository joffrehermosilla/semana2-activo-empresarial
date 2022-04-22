package nttdata.bootcamp.microservicios.activo.empresarial.services;

import nttdata.bootcamp.microservicios.activo.empresarial.documents.BusinessAsset;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BusinessAssetService {

	public Mono<BusinessAsset> findById(String id);

	public Flux<BusinessAsset> findAlls();

	public Mono<BusinessAsset> saves(BusinessAsset document);

	public Mono<Void> delete(BusinessAsset document);
}
