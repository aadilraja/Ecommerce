import React, { useState } from 'react';
import axios from 'axios';

function RegisterForm() {
  const [formData, setFormData] = useState({
    userName: '',
    userPassword: '',
    userMatchingPassword: '',
    userEmail: ''
  });

  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    setFormData(prev => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage('');
    try {
      const response = await axios.post('http://localhost:8080/api/auth/registerUser', formData);
      setMessage('Registration successful!');
    } catch (error) {
      if (error.response) {
        setMessage(error.response.data.message || 'Registration failed.');
      } else {
        setMessage('Server not reachable.');
      }
    }
  };

  return (
    <div style={{ maxWidth: '400px', margin: '0 auto' }}>
      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Username:
          <input type="text" name="userName" value={formData.userName} onChange={handleChange} required />
        </label><br /><br />
        
        <label>
          Password:
          <input type="password" name="userPassword" value={formData.userPassword} onChange={handleChange} required />
        </label><br /><br />

        <label>
          Confirm Password:
          <input type="password" name="userMatchingPassword" value={formData.userMatchingPassword} onChange={handleChange} required />
        </label><br /><br />

        <label>
          Email:
          <input type="email" name="userEmail" value={formData.userEmail} onChange={handleChange} required />
        </label><br /><br />

        <button type="submit">Register</button>
      </form>
      <p>{message}</p>
    </div>
  );
}

export default RegisterForm;
