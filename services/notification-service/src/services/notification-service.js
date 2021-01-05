import ws from 'ws'
import express from 'express'
import log4js from 'log4js'

import { KafkaConsumer } from '../domain/kafka-consumer.js' // TODO: absolute path

export class NotificationService
{
    constructor() {
        this.logger =  log4js.getLogger()
        this.logger.level = 'debug'

        // TODO: Constant for now but could be more dynamic (more instances of this service)
        this.port = 8080

        this._subscribeAll = this._subscribeAll.bind(this)
        this._notifyClients = this._notifyClients.bind(this)
        this.init = this.init.bind(this)
    }
    
    init() {
        const app = express()

        // TODO: subscribe to this web socket more properly with web app (not just simple cli)
        this.server = new ws.Server({ 
            server: app.listen(this.port) 
        })

        this.server.on('connection', () => {
            this._subscribeAll(this._notifyClients)
        })
    }

    _notifyClients(message, type) {
        this.server.clients.forEach(client => {
            client.send(JSON.stringify({
                message,
                type
            }))
        })
    }

    _subscribeAll(cb) {
        const dataGeneratedConsumer = new KafkaConsumer()
        const dataAnalyzedConsumer = new KafkaConsumer()
        const dataProcessedConsumer = new KafkaConsumer()
        const dataArchivedConsumer = new KafkaConsumer()

        dataGeneratedConsumer.subscribe('data-generated', cb)
        dataAnalyzedConsumer.subscribe('data-analyzed', cb)
        dataProcessedConsumer.subscribe('data-processed', cb)
        dataArchivedConsumer.subscribe('data-archived', cb)
    }
}
