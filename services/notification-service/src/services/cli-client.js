import log4js from 'log4js'
import Websocket from 'ws'

import { LOG_TYPES } from '../domain/log-types.js'

export class CliClient {
    constructor(id) {
        this.logger =  log4js.getLogger()
        this.logger.level = 'debug'

        this.id = id
        this.pre = `[#${id} CLI]`
        this.port = 8080
        this.ws = new Websocket(`ws://localhost:${this.port}`)

        this._addListeners.call(this)
    }

    _addListeners() {
        this.ws.addEventListener('open', () => {
            this.logger.info(`${this.pre} Client initialized, listening on ws port ${this.port}`)
        })

        this.ws.addEventListener('message', ({ data }) => {
            const { message, type } = JSON.parse(data)

            const formattedMessage = `${this.pre} ${message}`

            switch (type) {
                case LOG_TYPES.ERROR:
                    this.logger.error(formattedMessage)
                    break
                case LOG_TYPES.WARN:
                    this.logger.warn(formattedMessage)
                    break
                default:
                    this.logger.info(formattedMessage)
            }
        });
    }
}