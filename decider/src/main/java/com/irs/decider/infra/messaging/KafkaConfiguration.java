package com.irs.decider.infra.messaging;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;
import static io.confluent.kafka.serializers.KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG;
import static org.apache.kafka.clients.admin.AdminClientConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.APPLICATION_ID_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG;
import static org.apache.kafka.streams.StreamsConfig.PROCESSING_GUARANTEE_CONFIG;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration implements MessageConfiguration {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Override
	public Properties configureProperties() {
		
//		APPLICATION_ID_CONFIG: Um identificador único do processo no cluster do Kafka.
//		GROUP_ID_CONFIG: O identificador do grupo de consumidores.
//		BOOTSTRAP_SERVERS_CONFIG: Pode ser uma lista de url’s de conexão com o cluster do Kafka.
//		SCHEMA_REGISTRY_URL_CONFIG: Url de conexão do Schema Registry.
//		DEFAULT_KEY_SERDE_CLASS_CONFIG: Definição do Serializador/Deserializador padrão para as chaves das mensagens.
//		DEFAULT_VALUE_SERDE_CLASS_CONFIG: Definição do Serializador/Deserializador padrão para as mensagens.
//		SPECIFIC_AVRO_READER_CONFIG: Indica que será usado uma classe específica para ler a mensagem Avro

		Properties properties = new Properties();

		properties.put(APPLICATION_ID_CONFIG, kafkaProperties.getApplicationId());
		properties.put(GROUP_ID_CONFIG, kafkaProperties.getGroupId());
		properties.put(BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		properties.put(CLIENT_ID_CONFIG, kafkaProperties.getClientId());

		properties.put(PROCESSING_GUARANTEE_CONFIG, kafkaProperties.getProcessingGuaranteeConfig());
		properties.put(AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getOffsetReset());
		properties.put(DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, kafkaProperties.getTimeStampExtarctor());

		properties.put(SCHEMA_REGISTRY_URL_CONFIG, kafkaProperties.getSchemaRegistryUrl());
		properties.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, kafkaProperties.getDefaultKeySerde());
		properties.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, kafkaProperties.getDefaultValueSerde());
		properties.put(SPECIFIC_AVRO_READER_CONFIG, kafkaProperties.isSpecficAvroReader());

		return properties;
	}

}
