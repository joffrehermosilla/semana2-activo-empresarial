package nttdata.bootcamp.microservicios.activo.empresarial.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import nttdata.bootcamp.microservicios.activo.empresarial.documents.BusinessAsset;



@Repository
public interface BusinessAssetRepository  extends ReactiveMongoRepository<BusinessAsset, String>{

}
