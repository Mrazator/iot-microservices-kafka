import { CliClient } from './src/cli.js';
import { NotificationService } from './src/services/notification-service.js'

/**
 * Notification service listens to configured kafka topics,
 * and if anything happens, it sends notifications to all connected (CLI) clients via websocket
 */

const notification = new NotificationService();

// Trigger notification service initialization right away
notification.init();

// Initialize first simple cli client
const cli1 = new CliClient(1);