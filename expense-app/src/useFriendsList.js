import React, { useEffect, useState } from 'react';
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const mockFriends = [
      { id: 3, username: 'mchen', firstName: 'Mei', lastName: 'Chen', email: 'mei.chen@example.com', phoneNumber: '111-222-3333' },
      { id: 2, username: 'cjernandez', firstName: 'Carlos', lastName: 'Hernandez', email: 'carlos.hernandez@example.com', phoneNumber: '098-765-4321' },
      { id: 7, username: 'Lrossi', firstName: 'Luca', lastName: 'Rossi', email: 'luca.rossi@example.com', phoneNumber: '555-666-7777' }
  ];

const useFriendsList = () => {
  //const [users, setUsers] = useState([]);

  //mock will be overriding when we recieve data from backend
  const [users, setUsers] = useState(mockFriends);

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
          setUsers(userList);
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

  return users;
};
export default useFriendsList;
