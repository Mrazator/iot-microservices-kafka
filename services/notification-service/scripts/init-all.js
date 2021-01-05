import { CliClient } from '../src/services/cli-client.js';
import { NotificationService } from '../src/services/notification-service.js'

/**
 * Notification service listens to configured kafka topics,
 * and if anything happens, it sends notifications to all connected (CLI) clients via websocket
 */

const notification = new NotificationService();

// Trigger notification service initialization right away
notification.init();

// Initialize CLI client(s)
new CliClient(1);
// new CliClient(2);