package com.irs.register.register.infra.messaging.config;

import java.util.List;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(
    prefix = "kafka"
)
@Data
@NoArgsConstructor
public class KafkaProperties {
	
	//configurando a nossa aplicação para se conectar com o Kafkae no Schema Registry
	
	private List<String> bootstrapServers;
	private String acksConfig;
	private String retriesConfig;
	private Class<?> keySerializer = StringSerializer.class;
	private Class<?> valueSerializer = KafkaAvroSerializer.class;
	private String schemaRegistryUrl;

}