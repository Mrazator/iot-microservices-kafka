import log4js from 'log4js'
import { KafkaClient, Consumer } from 'kafka-node'

export class KafkaConsumer {
    constructor(host = 'localhost:9092') {
        this.client = new KafkaClient({ 
            kafkaHost: host
        })

        this.consumer = null
        this.logger = log4js.getLogger()

        this.subscribe = this.subscribe.bind(this)
    }

    subscribe(topic) {
        const consumer = new Consumer(
            this.client,
            [{ topic }]
        );

        consumer.on('error', (err) => {
            this.logger.error('Error in the consumer', err)
        })

        consumer.on('offsetOutOfRange', (err) => {
            this.logger.error('OffsetOutOfRange in the consumer', err)
        })

        consumer.on('message', (message) => {
            this.logger.info('Received message from consumer, topic', topic, ', message', message)
        })

        this.consumer = consumer;

        this.logger.info('Successfully subscribed to topic', topic)
    }
}