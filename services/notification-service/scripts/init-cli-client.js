import { CliClient } from '../src/services/cli-client.js';

/**
 * Create just separate client alone (as many as you would like - [0-1 000 000 000 000])
 * 
 * WARN: First notification service needs to be initialized
 */

// Initialize CLI client
const id = Math.floor(Math.random() * 1_000_000_000_000);
new CliClient(id);