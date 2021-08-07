import axios from 'axios';
import store from '../store/index'

export default{
    
    list(){
        return axios.get('/potholes');
    },

    deletePothole(id) {
        return axios.delete(`/potholes/${id}`)
    },

    createReport() {
        console.log(store.state.newReport);
        return axios.post('/potholes', store.state.newReport)
    },

    schedule(id) {
        return axios.post('/schedule', id);
    },

    getSchedule() {
        return axios.get('/schedule');
    },

    updateSchedule(schedule) {
        console.log('test put');
        return axios.put('/schedule', schedule);
    },

    updateSeverity() {
        return  axios.put('/potholes/');
    },

    uploadImage(id) {
        return axios.put(`/potholes/${id}`, '../../assets/pothole_test.jpg');
    }
}
