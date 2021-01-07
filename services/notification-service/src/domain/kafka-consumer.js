import log4js from 'log4js'
import { KafkaClient, Consumer } from 'kafka-node'

import { LOG_TYPES } from './log-types.js'

export class KafkaConsumer {
    // TODO: this localhost should be more dynamic (env variable?)
    constructor(host = 'localhost:9092') {
        this.logger =  log4js.getLogger()
        this.logger.level = 'debug'

        this.host = process.env.KAFKA_HOST || host
        this.client = new KafkaClient({ 
            kafkaHost: this.host
        })

        this.subscribe = this.subscribe.bind(this)
    }

    subscribe(topic, log) {
        const consumer = new Consumer(
            this.client,
            [{ topic }]
        );

        const pre = `[${topic}]`;

        consumer.on('error', (err) => {
            log(`${pre} ${err}`, LOG_TYPES.ERROR)
        })
        
        consumer.on('offsetOutOfRange', (err) => {
            log(`${pre} ${err}`, LOG_TYPES.ERROR)
        })
        
        consumer.on('message', (message) => {
            const msg = process.env.SHOW_FULL_MESSAGE ? JSON.stringify(message, undefined, 4) : '';

            log(`${pre} Received message ${msg}`, LOG_TYPES.INFO)
        })

        this.logger.debug(`Client subscribed to kafka topic ${topic} on host ${this.host}`)
    }

    // TODO: add unsubscribe
}