import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const NotificationComponent = () => {
  const [notification, setNotification] = useState('');

  useEffect(() => {
    // Create STOMP client using SockJS to connect to the WebSocket endpoint
    const client = new Client({
      // Create WebSocket connection using SockJS
      webSocketFactory: () => new SockJS('http://localhost:8082/ws-notifications'),
      // Called when the connection is established
      onConnect: () => {
        console.log('Connected to WebSocket');

        // Subscribe to the topic where the server sends messages
        client.subscribe('/topic/notifications', (message) => {
          if (message.body) {
            setNotification(message.body); // Set the received message in state
          }
        });
      },
      // Called on connection error or disconnection
      onStompError: (frame) => {
        console.error('WebSocket Error: ', frame);
      },
      debug: (str) => {
        console.log(str); // Optional: Print debugging info
      },
    });

    // Activate the STOMP client
    client.activate();

    // Cleanup the connection when component unmounts
    return () => {
      client.deactivate();
    };
  }, []);

  return (
    <div>
      <h3>Notifications</h3>
      {notification && <p>{notification}</p>}
    </div>
  );
};

export default NotificationComponent;
