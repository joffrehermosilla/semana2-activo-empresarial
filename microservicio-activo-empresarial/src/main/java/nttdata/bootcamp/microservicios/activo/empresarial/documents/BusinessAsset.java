package nttdata.bootcamp.microservicios.activo.empresarial.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "active-account-business-asset")
public class BusinessAsset {
	@Id
	private String id;

	// cantidad de credito que tiene la cuenta empresarial
	private Double allowedperclient;

	private boolean enabledtouse;

	private int paymentdays;

	private Double currentbalance;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createAt;

}
