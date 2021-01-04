import log4js from 'log4js'
// import kafka from 'kafka-node'

import { KafkaConsumer } from './src/consumer.js'
// Log4js necessary configuration
log4js.getLogger().level = 'debug'

const dataGeneratedConsumer = new KafkaConsumer()
const dataAnalyzedConsumer = new KafkaConsumer()
const dataProcessedConsumer = new KafkaConsumer()
const dataArchivedConsumer = new KafkaConsumer()

dataGeneratedConsumer.subscribe('data-generated')
dataAnalyzedConsumer.subscribe('data-analyzed')
dataProcessedConsumer.subscribe('data-processed')
dataArchivedConsumer.subscribe('data-generated')

// var Producer = kafka.Producer,
//     client = new kafka.KafkaClient({ kafkaHost: 'localhost:9092' }),
//     producer = new Producer(client),
//     payloads = [
//         { 
//             topic: 'data-generated', 
//             messages: ['test message'] 
//         }
//     ];

//     client.on('ready', function (){
//         console.log('client ready');
//     })  

//     client.on('error', function (err){
//         console.log('client error: ' + err);
//     })  

//     producer.on('ready', function () {
//         console.log('ready')
//         producer.send(payloads, function (err, data) {
//             console.log('send: ' + data);        
//             process.exit();
//         });
//     });

//     producer.on('error', function (err) {
//         console.log('error: ' + err);
//         process.exit();
//     });
