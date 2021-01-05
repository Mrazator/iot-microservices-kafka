import { NotificationService } from '../src/services/notification-service.js'

/**
 * Create just notification service alone and listen to kafka topics (without connected CLI)
 */

const notification = new NotificationService();

// Trigger notification service initialization right away
notification.init();