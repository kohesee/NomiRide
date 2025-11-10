import axios from 'axios';

const API_BASE = 'http://10.0.2.2:8080/api';

export const createRide = (data) => axios.post(`${API_BASE}/rides`, data).then(r => r.data);
export const getRide = (id) => axios.get(`${API_BASE}/rides/${id}`).then(r => r.data);
export const completeRide = (id) => axios.patch(`${API_BASE}/rides/${id}/complete`).then(r => r.data);
export const getRideHistory = (riderId) => axios.get(`${API_BASE}/rides/history/${riderId}`).then(r => r.data);
export const getAvailableDrivers = () => axios.get(`${API_BASE}/drivers/available`).then(r => r.data);
export const getRider = (riderId) => axios.get(`${API_BASE}/riders/${riderId}`).then(r => r.data);
export const addToWallet = (riderId, amount) => axios.patch(`${API_BASE}/riders/${riderId}/wallet`, { amount }).then(r => r.data);

export default {
  createRide, getRide, completeRide, getRideHistory, getAvailableDrivers, getRider, addToWallet
};
