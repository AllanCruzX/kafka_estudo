package com.irs.register.register.application.controller.taxpayer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.register.register.infra.messaging.MessagingPort;
import com.irs.register.register.shared.dto.CommonDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaxpayerService implements MessagingPort<TaxPayer> {
	
	//TaxPayer -> Arvo interpretado em classe Java
	
	//Criar o produtor

	@Autowired
	@Qualifier("taxpayerProducer")
	private KafkaProducer<String, TaxPayer> producer;

	@Override
	public String topic() {
		//nome do tópico no Kafka
		
		return "taxpayer-avro";
	}
		
	@Override
	public ProducerRecord<String, TaxPayer> createProducerRecord(TaxPayer taxPayer) {

		return new ProducerRecord<String, TaxPayer>(this.topic(), taxPayer);
		
	}

	@Override
	public void send(CommonDTO taxpayerDTO) {

		// Recebe o DTO e converte para o formato ARVO
		TaxPayer taxPayer = TaxPayer.newBuilder()
				.setName(((TaxpayerDTO) taxpayerDTO)
						.getName())
				.setDocument(((TaxpayerDTO) taxpayerDTO)
						.getDocument())
				.setSituation(false)
				.build();
		
//é nesse método que fazemos o envio da mensagem para o Kafka, podemos ver que o método send do KafkaProducer recebe o nosso TaxPayer mas também executa uma função de callback onde fazemos uma simples verificação de sucesso ou erro e logamos o resultado. Após isso “atualizamos” a transação e fechamos.		
		
		producer.send(this.createProducerRecord(taxPayer), (rm, ex) -> {
			if (ex == null) {
				log.info("Data sent with success!!!");
			} else {
				log.error("Fail to send message", ex);
			}
		});

		producer.flush();
		producer.close();

	}

}
