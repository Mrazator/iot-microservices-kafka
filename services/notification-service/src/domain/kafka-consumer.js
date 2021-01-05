import { KafkaClient, Consumer } from 'kafka-node'

import { LOG_TYPES } from './log-types.js'

export class KafkaConsumer {
    constructor(host = 'localhost:9092') {
        this.client = new KafkaClient({ 
            kafkaHost: host
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
            log(`${pre} Received message ${message}`, LOG_TYPES.INFO)
        })

        log(`${pre} Topic initialized`, LOG_TYPES.INFO)
    }

    // TODO: add unsubscribe
}