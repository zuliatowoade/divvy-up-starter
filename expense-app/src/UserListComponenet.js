import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';
//const useState users = {}
let userAll = [];
const UserListComponent = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    // Create WebSocket connection using SockJS and STOMP
    const client = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8081/ws-users'), // WebSocket endpoint for Spring Boot
      reconnectDelay: 5000,  // Reconnect in 5 seconds if the connection is lost
    });

    // On successful connection
    client.onConnect = () => {
      console.log('Connected to WebSocket user');

      // Subscribe to the topic where the server sends user data
      client.subscribe('/topic/users', (message) => {
        if (message.body) {
          console.log(message.body)
          const userList = JSON.parse(message.body); 
          console.log(userList) // Parse the received message
          userAll = setUsers(userList);
          console.log("notification" + userAll) // Parse the received message
        }
      });

      // Send a request to fetch the list of users
      client.publish({
        destination: '/app/getUsers',
        body: '',
      });
    };

    // On connection error
    client.onStompError = (frame) => {
      console.error('WebSocket error: ', frame);
    };

    // Activate the STOMP client to establish a WebSocket connection
    client.activate();

    // Cleanup: Deactivate the WebSocket connection when the component unmounts
    return () => {
      client.deactivate();
    };
  }, []);
};
export default UserListComponent;
export {userAll};
