import React, { useState } from 'react';
import axios from 'axios';
import './ExpensePage.css';

const ExpensePage = () => {
    // Hardcoded list of friends for now
    const [friends, setFriends] = useState([
        { id: 3, username: 'mchen', firstName: 'Mei', lastName: 'Chen', email: 'mei.chen@example.com', phoneNumber: '111-222-3333' },
        { id: 2, username: 'cjernandez', firstName: 'Carlos', lastName: 'Hernandez', email: 'carlos.hernandez@example.com', phoneNumber: '098-765-4321' },
        { id: 7, username: 'Lrossi', firstName: 'Luca', lastName: 'Rossi', email: 'luca.rossi@example.com', phoneNumber: '555-666-7777' }
    ]);

    const [searchTerm, setSearchTerm] = useState('');
    const [selectedFriends, setSelectedFriends] = useState([]);
    const [generalAmount, setGeneralAmount] = useState('');
    const [amounts, setAmounts] = useState({});
    const [splitType, setSplitType] = useState('SPLIT_EQUALLY');
    const [description, setDescription] = useState('');

    // Handle friend search input change
    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value);
    };

    // Handle selecting a friend
    const handleSelectFriend = (friend) => {
        if (!selectedFriends.includes(friend)) {
            setSelectedFriends([...selectedFriends, friend]);
            setSearchTerm(''); // Clear search input after selection
        }
    };

    // Handle removing a friend
    const handleRemoveFriend = (friend) => {
        setSelectedFriends(selectedFriends.filter(f => f.id !== friend.id));
        if (splitType === 'EXACT_AMOUNT') {
            setAmounts(prevAmounts => {
                const newAmounts = { ...prevAmounts };
                delete newAmounts[friend.id];
                return newAmounts;
            });
        }
    };

    // Handle change in amount input for friends
    const handleAmountChange = (friendId, value) => {
        setAmounts(prevAmounts => ({ ...prevAmounts, [friendId]: value }));
    };

    // Handle change in general amount input
    const handleGeneralAmountChange = (event) => {
        setGeneralAmount(event.target.value);
    };

    // Handle form submission
    const handleSubmit = async (event) => {
        event.preventDefault();

        // Prepare the data to send
        const expenseData = {
            initatorUser: 'cthomson', // Hardcoded initiator ID for now
            description,
            amount: generalAmount, // Changed from generalAmount to totalAmount
            splitType,
            // friends: selectedFriends.map(friend => ({
            //     ...friend,
            //     amount: splitType === 'EXACT_AMOUNT' ? amounts[friend.id] || 0 : undefined
            // }))
            friends: ["apple", "banana"],
        };

        // Send data to the backend
        try {
            // Uncomment this block when backend is available
            console.log('Expense data:', expenseData); // For now, log the data to the console
            await axios.post('http://localhost:8081/api/addExpense', expenseData);
        } catch (error) {
            console.error('Error submitting expense:', error);
        }
    };

    // Filter friends based on search term
    const filteredFriends = friends.filter(friend =>
        friend.firstName.toLowerCase().includes(searchTerm.toLowerCase()) ||
        friend.lastName.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="expense-container">
            <h2>Add Expense</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Split Type</label>
                    <select value={splitType} onChange={(e) => setSplitType(e.target.value)}>
                        <option value="SPLIT_EQUALLY">Split Equally</option>
                        <option value="EXACT_AMOUNT">Exact Amount</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Total Amount</label>
                    <input
                        type="number"
                        placeholder="Enter total amount"
                        value={generalAmount}
                        onChange={handleGeneralAmountChange}
                    />
                </div>
                <div className="form-group">
                    <label>Friends</label>
                    <div className="friend-search">
                        <input
                            type="text"
                            placeholder="Search friends..."
                            value={searchTerm}
                            onChange={handleSearchChange}
                        />
                        {searchTerm && (
                            <ul className="friend-dropdown">
                                {filteredFriends.map(friend => (
                                    <li key={friend.id} onClick={() => handleSelectFriend(friend)}>
                                        {friend.firstName} {friend.lastName}
                                    </li>
                                ))}
                            </ul>
                        )}
                    </div>
                    <div className="selected-friends">
                        {selectedFriends.map(friend => (
                            <div key={friend.id} className="selected-friend">
                                {friend.firstName} {friend.lastName}
                                <button type="button" onClick={() => handleRemoveFriend(friend)}>x</button>
                                {splitType === 'EXACT_AMOUNT' && (
                                    <div className="amount-group">
                                        <input
                                            type="number"
                                            placeholder="Enter amount"
                                            value={amounts[friend.id] || ''}
                                            onChange={(e) => handleAmountChange(friend.id, e.target.value)}
                                        />
                                    </div>
                                )}
                            </div>
                        ))}
                    </div>
                </div>
                <div className="form-group">
                    <label>Description</label>
                    <textarea
                        placeholder="Enter description"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    />
                </div>
                <button type="submit" className="submit-btn">Add Expense</button>
            </form>
        </div>
    );
};

export default ExpensePage;
