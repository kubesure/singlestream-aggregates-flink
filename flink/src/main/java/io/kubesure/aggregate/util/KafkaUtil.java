package io.kubesure.aggregate.util;

import java.util.Properties;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.producer.KafkaProducer;

import io.kubesure.aggregate.job.EventTimeAggregateJob;

public class KafkaUtil {

	public static FlinkKafkaProducer<String> newFlinkKafkaProducer(String topic, ParameterTool parameterTool) {
		// TODO: replace depricated constructor
		FlinkKafkaProducer<String> kafkaProducer = new FlinkKafkaProducer<String>(
			    topic,
				new SimpleStringSchema(),
				parameterTool.getProperties());
		kafkaProducer.setWriteTimestampToKafka(true);
		return kafkaProducer;
	}

	public static KafkaProducer<String, String> newKakfaProducer(ParameterTool parameterTool) {
		Properties properties = EventTimeAggregateJob.parameterTool.getProperties();
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		return producer;
	}


    
}